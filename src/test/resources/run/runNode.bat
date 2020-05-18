SET RES=E:\Dev\tdd-worksheets\las3007\lesson10\src\test\resources
SET JAR=%RES%\selenium-standalone-server\selenium-server-standalone-3.141.59.jar

java ^
  -Dwebdriver.gecko.driver="%RES%\drivers\geckodriver.exe" ^
  -Dwebdriver.chrome.driver="%RES%\drivers\chrome81driver.exe" ^
  -jar %JAR% ^
	-role node ^
	-hub "http://localhost:4444/grid/register" ^
	-browser "browserName=firefox,version=76,maxInstances=3,platform=WINDOWS" ^
	-browser "browserName=chrome,version=81,maxInstances=3,platform=WINDOWS"

PAUSE