package com.example.demoJP;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.example.demoJP",
//        tags    = "@apiEndPoint ",
        plugin = {"pretty", "html:target/cucumber-htmlreport.html","json:target/cucumber-report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner {
}
