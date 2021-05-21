#!/bin/bash

# arguments
command_name=$1
username=$2
password=$3
docker container inspect jrvs-psql >/dev/null 2>&1
container_exists=$?

# prints usage
usage() {
  echo -e "Usage: ${0} start|stop|create [db_username][db_password]"
}
# prints error message (1st positional argument) and exits with 1
error_exit() {
  # if error message is not specified, prints default
  echo -e "${0}: ${1:-"Unknown Error"}"
  exit 1
}
# creates container if it is not created yet
create_function() {
  # function inputs
  db_username=$1
  db_password=$2
  # check if container already exists
  if [ $container_exists -eq 0 ]; then
    error_exit "Container is already created"
  fi
  # check if name and passowrd are given as positional arguments
  if [ -z "$db_username" ] || [ -z "$db_password" ]; then
    error_exit "Name or password are not provided"
  fi
  # creates docker volume for database only if it doesn't exist
  docker volume inspect pgdata >/dev/null 2>&1 || docker volume create pgdata >/dev/null 2>&1
  # creating a container named jrvs-psql
  docker run --name jrvs-psql \
    -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} \
    -d \
    -v pgdata:/var/lib/postgresql/data \
    -p 5432:5432 postgres
  # returns the success/failure of the last command
  is_container_created=$?
  if [ $is_container_created -ne 0 ]; then
    usage
    error_exit "Couldn't create the container"
  fi
  exit $is_container_created
}

start_function() {
  # if container doesn't exist, return with error message
  if [ $container_exists -ne 0 ]; then
    error_exit "Container doesn't exist"
  fi
  docker start jrvs-psql
  exit $?
}

stop_function() {
  # if container doesn't exist, return with error message
  if [ $container_exists -ne 0 ]; then
    error_exit "Container doesn't exist"
  fi
  docker stop jrvs-psql
  exit $?
}

# program logic
# checks and starts docker service if it is not running
sudo systemctl status docker > /dev/null 2>&1 || sudo systemctl start docker > /dev/null 2>&1
# checks inputs
case "$1" in
  create )
    create_function "$2" "$3" ;;
  start )
    start_function ;;
  stop )
    stop_function ;;
  * )
    usage
    error_exit "Invalid arguments" ;;
esac