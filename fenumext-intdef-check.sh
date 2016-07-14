#!/bin/sh

myDir="`dirname $0`"

if [ "$CHECKERFRAMEWORK" = "" ]; then
  echo "Please set CHECKERFRAMEWORK environment variable first!";
  exit 1;
fi

cd $myDir

javac -AprintErrorStack -XprintProcessorInfo -cp ./checker-fenumext-0.0.1-SNAPSHOT.jar:$CHECKERFRAMEWORK/checker/dist/checker.jar -processor org.checkerframework.checker.intdef.IntDefChecker "$@"
