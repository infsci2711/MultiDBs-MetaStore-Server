#!/bin/bash
set -e

echo "starting mysql"

service mysql start

#echo "running sql script"

mysql -uroot -pstrangehat < /home/metastore/metastoredb.sql

exec "$@"