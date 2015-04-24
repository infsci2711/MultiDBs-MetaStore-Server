#!/bin/bash
set -e

echo "starting mysql"

service mysql start

echo "running sql script"

mysql -uroot -p$MYSQL_PASSWORD < /home/metastore/metastoredb.sql

exec "$@"