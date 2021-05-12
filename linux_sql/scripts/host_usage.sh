#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# prints usage
usage() {
  echo -e "Usage: ${0} psql_host psql_port db_name psql_user psql_password"
}
# prints error message (1st positional argument) and exits with 1
error_exit() {
  # if error message is not specified, prints default
  echo -e "${0}: ${1:-"Unknown Error"}"
  exit 1
}

# program logic
#check for the number of arguments
if [ $# -ne 5 ]; then
  usage
  error_exit "Invalid arguments"
fi
# parsing data:
hostname=$(hostname -f)
timestamp=$(date +"%Y-%m-%d %H:%M:%S") #UTC time zone
memory_free=$(vmstat --unit=M | tail -1 | awk '{print $4}')              #in MB
cpu_idle=$(vmstat | tail -1 | awk '{print $15}')                   #in percentage
cpu_kernel=$(vmstat | tail -1 | awk '{print $14}')                   #in percentage
disk_io=$(vmstat -d | tail -1 | awk '{print ($2 + $6)}')                     #number of disk I/O
disk_available=$(df -BM --output='avail' / | tail -1 | awk '{print int($1)}') #in MB. root directory avaiable disk

#making an insert statement through a subquery to the host_id
insert_statement="INSERT INTO host_usage(
    timestamp,
    host_id,
    memory_free,
    cpu_idle,
    cpu_kernel,
    disk_io,
    disk_available
    )
  SELECT
    '$timestamp',
    id,
    $memory_free,
    $cpu_idle,
    $cpu_kernel,
    $disk_io,
    $disk_available
  FROM
    host_info
  WHERE hostname = '$hostname';"

# inserting data
PGPASSWORD=$psql_password psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$insert_statement"

insert_success=$?
if [ $insert_success -eq 0 ];then
  echo "$timestamp: Successfully added usage data for $hostname"
  exit 0
else
  error_exit "Error adding host usage data with the provided arguments"
fi