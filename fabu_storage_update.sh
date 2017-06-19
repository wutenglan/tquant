#!/bin/sh
DIR="$( cd "$( dirname "$0"  )" && pwd  )"

#APP HOME
#JAVA_HOME=/home/ewallet/autoDeploy/software/jdk-oracle-1.7.0_64
SERVER=$DIR/tquant-storage/target
LOG_HOME=/mnt/logs/tquant
#JVM启动参数 
JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:PermSize=512M -XX:MaxPermSize=512M -Xloggc:/mnt/logs/tquant/storage_gc.log"

  
case "$1" in  

  start)  
    cd $SERVER
    nohup java $JAVA_OPTS -jar tquant-storage.jar > $LOG_HOME/storage_nohup.log 2>&1 &  
    echo $! > $SERVER/run.pid  
    ;;  
  
  stop)  
    kill `cat $SERVER/run.pid`  
    rm -rf $SERVER/run.pid
    ;;  
  
  restart)  
    $0 stop  
    sleep 1  
    $0 start  
    ;;  

  update)  
    cd $DIR
    svn update
	  mvn clean
	  mvn package -Dmaven.test.skip=true -Ponline
    ;; 
  
  *)  
    echo "Usage: run.sh {start|stop|restart|update}"  
    ;;  
  
esac 
exit 0

