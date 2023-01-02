package com.example.demoJP.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class Hooks {

    public static Logger log = LogManager.getLogger(Hooks.class.getName());
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static WireMockServer server = new WireMockServer(PORT);



    @Before
    public void beforeScenario(Scenario scenario){


        server.start();//this will start wiremock server(we can also start wiremock server as image using docker
        WireMock.configureFor(HOST,PORT); // this will set the host and port;


        log.info("******************************************************************************************************************************************");
        log.info("******************************************************************************************************************************************");
        log.info("Executing scenario:"+ scenario.getName());
        log.info("*******************************************************************************************************************************************");
        log.info("*******************************************************************************************************************************************");

    }

    @After
    public void afterScenario(Scenario scenario){

        server.stop();//this will stop wiremock server
        log.info("******************************************************************************************************************************************");
        log.info("******************************************************************************************************************************************");
        log.info("End of  scenario:"+ scenario.getName());
        log.info("*******************************************************************************************************************************************");
        log.info("*******************************************************************************************************************************************");


    }
}

