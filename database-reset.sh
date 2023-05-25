#!/bin/sh
# (Re)creates the necessary database roles and databases

set -euo pipefail

PSQL="psql --username postgres --quiet --output /dev/null"
DB=axon
ROLE=axon

$PSQL -c "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = '$DB' AND pid <> pg_backend_pid()"

$PSQL -c "DROP DATABASE IF EXISTS $DB"
$PSQL -c "DROP ROLE IF EXISTS $ROLE"

$PSQL -c "CREATE ROLE $ROLE WITH LOGIN"
$PSQL -c "CREATE DATABASE $DB WITH OWNER = $ROLE"

$PSQL -c "ALTER ROLE $ROLE IN DATABASE $DB SET SEARCH_PATH = 'public'"
