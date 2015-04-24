#!/bin/bash
set -e

echo "installing mysql"

echo mysql-server mysql-server/root_password password $MYSQL_PASSWORD | debconf-set-selections
echo mysql-server mysql-server/root_password_again password $MYSQL_PASSWORD | debconf-set-selections

apt-get install -y mysql-server

echo "starting mysql"

service mysql start

echo "running sql script"

mysql -uroot -p$MYSQL_PASSWORD < /home/metastore/metastoredb.sql

echo "creating folder for servers source code"

cd /opt
mkdir -p project
cd project

echo "cloning two git repos"

git clone https://github.com/infsci2711/MultiDBs-Utils.git
git clone https://github.com/infsci2711/MultiDBs-MetaStore-Server

echo "building utils project"

cd /opt/project/MultiDBs-Utils
mvn install

echo "building server project"

cd /opt/project/MultiDBs-MetaStore-Server
mvn install

echo "creating folder for deployed code"

cd /opt/project
mkdir -p deployed

echo "copying jar and config file to deploed folder"

cp /opt/project/MultiDBs-MetaStore-Server/MultiDBsMetaStoreServerAPI/target/multidbsmetastoreserverapi-0.1-SNAPSHOT.jar /opt/project/deployed
cp /opt/project/MultiDBs-MetaStore-Server/MultiDBsMetaStoreServerAPI/src/main/resources/config.properties /opt/project/deployed

echo "starting java server"

nohup java -jar  /opt/project/deployed/multidbsmetastoreserverapi-0.1-SNAPSHOT.jar /opt/project/deployed/config.properties > /opt/project/deployed/log.out 2> /opt/project/deployed/error.log < /dev/null &

exec "$@"