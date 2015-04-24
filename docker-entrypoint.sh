#!/bin/bash
set -e

echo "starting mysql"

service mysql start

echo "running sql script"

mysql -uroot -p$MYSQL_PASSWORD < /home/metastore/metastoredb.sql

echo "creating folder for servers source code"

cd /opt
mkdir project
cd project

echo "cloning two git repos"

git clone https://github.com/infsci2711/MultiDBs-Utils.git
git clone https://github.com/infsci2711/MultiDBs-MetaStore-Server

echo "building utils project"

cd MultiDBs-Utils
mvn install

echo "building server project"

cd ../MultiDBs-MetaStore-Server
mvn install

exec "$@"