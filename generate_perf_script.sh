#!/bin/sh
SCRIPT_DIR="$(cd $(dirname $0);pwd)"
if [ -z "$1" ]
then
	echo "Usage: $0 fullTargetClassName jarFileLocation scriptLocation"
	exit 1
fi
if [ -z $JAVA_HOME ];then
	echo "NO JAVA_HOME SET"
	exit 1
fi
if [ -z $BTRACE_HOME ];then
	BTRACE_HOME=$SCRIPT_DIR/../	
	if [ ! -d $BTRACE_HOME/build ]
	then
		echo "No BTRACE_HOME set!"
		exit 1
	fi
fi
class_path=''
for file in $(ls $BTRACE_HOME/build)
do
	class_path=$class_path:$BTRACE_HOME/build/$file
done

$JAVA_HOME/bin/java -cp $class_path -jar $BTRACE_HOME/build/perf-tool.jar $*

