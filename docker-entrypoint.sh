#!/bin/bash
set -e

echo "starting mysql"

service mysql start

#echo "running sql script"

#mysql -u root –pstrangehat < /home/metastore/metastoredb.sql

exec "$@"