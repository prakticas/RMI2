#!/bin/bash


USER="a785370"
SERVERLOG="serverA.log"
BROKERLOG="broker.log"
WORKINGDIR="/home/a785370/broker"
JAVAC="/usr/local/java/bin/javac"
JAVA="/usr/local/java/bin/java"

servers=(155.210.154.202 155.210.154.203 155.210.154.204)


scp   -r configuration ${USER}@central.cps.unizar.es:${WORKINGDIR}
#ssh -n ${USER}@${servers[0]} "chmod ${WORKINGDIR}/configuration/java.policy g+w"
scp   -r src ${USER}@central.cps.unizar.es:${WORKINGDIR}

#eliminar instancias antiguas"
for s in ${servers[@]}; do
 ssh -n ${USER}@${s} "killall /usr/local/java/bin/java" 2>/dev/null 1>&2 
done
ssh -n ${USER}@central.cps.unizar.es "find ${WORKINGDIR}/src -iname \"*.java\" > ${WORKINGDIR}/sources.txt ;${JAVAC} @${WORKINGDIR}/sources.txt -d ${WORKINGDIR}/build"


ssh -n ${USER}@${servers[0]} "cd ${WORKINGDIR}/build;${JAVA} -cp  ${WORKINGDIR}/build BrokerImpl"
#echo ${USER}@${servers[1]}
#echo ${USER}@${servers[2]}BrokerImpl