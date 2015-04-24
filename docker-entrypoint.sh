#!/bin/bash
set -e

echo "starting mysql"

service mysql start

echo "running sql script"

mysql -u root –pstrangehat < metastoredb.sql

exec "$@"