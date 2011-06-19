#!/bin/sh

if [ -z "$BTRACE_HOME" ]
then
	echo "NO BTRACE_HOME SET"
	exit 1
fi

mvninstall 
mvn dependency:copy-dependencies
cp target/perf-tool.jar  target/dependency/asm*.jar $BTRACE_HOME/build/
cp  generate_perf_script.sh $BTRACE_HOME/bin/
