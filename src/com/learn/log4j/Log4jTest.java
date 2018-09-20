package com.learn.log4j;

import org.apache.log4j.Logger;

public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.info("Log info");
        logger.error("Log error");
        logger.fatal("Log fatal");
        logger.warn("Log warn");
        logger.debug("Log debug");
    }

}
