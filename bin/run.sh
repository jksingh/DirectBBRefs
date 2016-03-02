#!/bin/bash

HERE=`dirname $0`
MOD_HOME=`cd $HERE/..; pwd`
echo "MOD_HOME: $MOD_HOME"
cd $MOD_HOME

javac src/Main.java

java -cp src/ -Xmx4096m -XX:MaxDirectMemorySize=6g -XX:+UseCompressedOops -XX:+UseFastAccessorMethods -XX:+AggressiveOpts -server -Xloggc:gc.log -XX:GCLogFileSize=10M -XX:NumberOfGCLogFiles=10 -XX:+UseGCLogFileRotation -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -verbose:gc -XX:+PrintReferenceGC -XX:+PrintGCDateStamps -XX:+ParallelRefProcEnabled -XX:+UseG1GC Main
