#!/bin/bash

set -e
set -u

if $INCLUDE_TEST_DATABASE
then
  echo Creating test user and database.
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER test WITH PASSWORD 'test';
    CREATE DATABASE test;
    GRANT ALL PRIVILEGES ON DATABASE test TO test;
EOSQL
else
  echo Skipping test user and database.
fi


