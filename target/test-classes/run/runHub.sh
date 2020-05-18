#!/bin/sh

RES=/home/student/tdd-worksheets/las3007/lesson10/src/test/resources
JAR=$RES/selenium-standalone-server/selenium-server-standalone-3.141.59.jar

java -jar $JAR -role hub -host localhost -port 4444

sleep 2m