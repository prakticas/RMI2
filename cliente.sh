USER="a785370"
SERVERLOG="serverA.log"
BROKERLOG="broker.log"
WORKINGDIR="/home/a785370/broker"
JAVAC="/usr/local/java/bin/javac"
JAVA="/usr/local/java/bin/java"

SERVER=155.210.154.202

ssh -n ${USER}@${SERVER} "cd ${WORKINGDIR};${JAVA} -cp  ${WORKINGDIR}/build ClientC"
