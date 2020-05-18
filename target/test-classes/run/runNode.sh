#!/bin/sh

RES=/home/student/tdd-worksheets/las3007/lesson10/src/test/resources
JAR=$RES/selenium-standalone-server/selenium-server-standalone-3.141.59.jar

java \
  -Dwebdriver.gecko.driver="$RES/drivers/geckodriver" \
  -Dwebdriver.chrome.driver="$RES/drivers/chrome81driver" \
  -jar $JAR -role node -nodeConfig "$RES/run/nodeconfig.json"
  
sleep 2m