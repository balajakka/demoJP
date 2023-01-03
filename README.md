# demoJP

<ins>To Run the project:</ins>

git clone using given url with ssh: git clone git@github.com:balajakka/demoJP.git 

git clone using given url with https: git clone https://github.com/balajakka/demoJP.git

goto TestRunner(see path) and click Run: /demoJP/src/test/java/com/example/demoJP/TestRunner.java

<ins>To access reports of test execution please see below paths:</ins>

extent report: /demoJP/Reports/demoJP.html

html report: /demoJP/target/cucumber-htmlreport.html

json report: /demoJP/target/cucumber-report.json (this file can be used in jenkins build to display cucumber json report using plugin)

<ins>Test Details:</ins>

@javaServiceTest: This tagged tests are written just to demonstrate if end points are used in any rest service.

@sanity: This tagged tests are written to ensure all end points resources are working

@apiEndPoint: This tagged tests are written for test coverage of given endpoints(since i was not provided with swagger definition, i have assumed to requirements, they may not be actual requirements)

@wireMockTest: This tagged tests are written to demonstrate wiremock implemenation in case we are using any third party or upstream or downstream apis(to mock).


<ins>Running tests using tags:</ins>

Replace & uncomment below line in Test Runner to run individual tag tests.

//        tags    = "@wireMockTest",

