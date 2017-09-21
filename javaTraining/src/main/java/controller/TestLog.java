package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    static final Logger LOGGER = LoggerFactory.getLogger(TestLog.class);

    /** Main.
     * @param args tableau */
    public static void main(String[] args) {
        LOGGER.info("Msg #1");
        LOGGER.warn("Msg #2");
        LOGGER.error("Msg #3");
        LOGGER.debug("Msg #4");

    }

}
