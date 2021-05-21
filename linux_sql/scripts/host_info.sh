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
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print int($3)}' | xargs)
total_mem=$(egrep "MemTotal" /proc/meminfo | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %H:%M:%S") #current timestamp in `2019-11-26 14:40:19` format

insert_statement="INSERT INTO host_info (
    hostname,
    cpu_number,
    cpu_architecture,
    cpu_model,
    cpu_mhz,
    l2_cache,
    total_mem,
    timestamp
  )
  VALUES(
    '$hostname',
    $cpu_number,
    '$cpu_architecture',
    '$cpu_model',
    $cpu_mhz,
    $l2_cache,
    $total_mem,
    '$timestamp'
  );"
#executing insert statement and setting the variable
echo $insert_statement

PGPASSWORD=$psql_password psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$insert_statement"

insert_success=$?
if [ $insert_success -eq 0 ];then
  echo "Successfully added host info to the database"
  exit 0
else
  error_exit "Error adding host info with the provided arguments"
fi