#RES=/home/student/eclipse-workspace/Assignment/src/test/resources
JAR=/home/student/Documents/selenium-server-standalone-3.141.59.jar

java -Dwebdriver.gecko.driver="/home/student/eclipse-workspace/Assignment/src/test/resources/drivers/geckodriver" -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig "/home/student/eclipse-workspace/Assignment/src/test/resources/run/nodeconfig.json"
  
 #-Dwebdriver.chrome.driver="/home/student/eclipse-workspace/Assignment/src/test/resources/drivers/chromedriver" 
 
sleep 2m