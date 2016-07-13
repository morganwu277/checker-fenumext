#!/bin/sh

myDir="`dirname $0`"

if [ "$CHECKERFRAMEWORK" = "" ]; then
  echo "Please set CHECKERFRAMEWORK environment variable first!";
  exixt 1;
fi

javac -AprintErrorStack -XprintProcessorInfo -processor org.checkerframework.checker.stringdef.StringDefChecker -classpath "$myDir/dist/checker-fenumext-0.0.1-SNAPSHOT.jar" "$@"
