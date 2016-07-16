#!/bin/sh

mvn clean package 
build_result=$?
rm -rf dist
mkdir dist

if [ "$build_result" == "0" ]; then
  cp target/checker-fenumext-*.jar dist/
  cp fenumext-intdef-check.sh dist/
  cp fenumext-stringdef-check.sh dist/
fi
