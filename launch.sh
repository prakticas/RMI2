#!/bin/bash
USER="a785370"
SERVERLOG="serverA.log"
BROKERLOG="broker.log"
WORKINGDIR="/home/${USER}/broker"
JAVAC="/usr/local/java/bin/javac"
JAVA="/usr/local/java/bin/java"
servers=(155.210.154.202 155.210.154.203 155.210.154.204)
IP=${servers[0]}

if [ $# -eq 0 ];then
     echo "inidique el progrma a lanzar"
    exit 85
fi

if [ $# -eq 2 ];then
     IP=$2
fi

ssh ${USER}@${IP} "cd ${WORKINGDIR}/build;${JAVA} -cp  ${WORKINGDIR}/build $1"