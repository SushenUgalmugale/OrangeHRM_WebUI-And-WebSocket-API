# OrangeHRM_WebUI-And-WebSocket-API
Orange HRM For UI and web socket API Autoimation project
\# **OrangeHRM_Project**

This is a maven based Selenium-java Automation framework using Junit,
Cucumber, TestNG for testing OrangeHRM's 'Web Automation & Websocket API
Automation'.

1.  OrangeHRM's 'Web Automation

\## Test Run Reports:

a)To do execution, run the TestRunner file.

Runner_File_Path:src/main/java/runners/TestRunner.java

![image](https://github.com/SushenUgalmugale/OrangeHRM_WebUI-And-WebSocket-API/assets/160130704/c8a92444-3218-4d49-a40c-b37a7bbf1130)


> b)To generate the Reports, Execute the maven command after TestRunner
> execution.

Maven Command : mvn verify --DskipTests

It will generate the html reports under below path.

Report_Path: target/cucumber-report-html/cucumber-html-reports/js

Screaanshots_Path: target/screenshots

2.  OrangeHRM's Websocket API Automation'

\## WebSocket Test Run Results:

> a)To generate the result need to run WebsocketSpreadTest.java test
> file
>
> Test_File_Path: src/test/java/websockets/tests.publicMessages/
> WebsocketSpreadTest.java
>
![image](https://github.com/SushenUgalmugale/OrangeHRM_WebUI-And-WebSocket-API/assets/160130704/700734ba-68f0-4264-ba96-010fda7bfea6)


\## Dependencies

Maven will take care of all the project dependencies.

\`\`\`

\## Future Improvements

\* Testcases implementation

\* Parallel execution (Multithreading)

\* Allure report
