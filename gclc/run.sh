#!/usr/bin/env bash
mkdir build 2> /dev/null
scala -cp gclc.jar Compiler "$1" > "$1.scala" &&
mv "$1.scala" build &&
scalac -cp gclc.jar -d "build/" "build/$1.scala" &&
scala -cp gclc.jar:./build $1

rm "$1.scala" 2> /dev/null