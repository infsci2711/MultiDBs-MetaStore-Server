#!/bin/bash
set -e

echo "starting mysql"

service mysql start

#echo "running sql script"

mysql -u$MYSQL_USER -p$MYSQL_PASSWORD < /home/metastore/metastoredb.sql

exec "$@"