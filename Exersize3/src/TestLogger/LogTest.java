package TestLogger;

import java.util.logging.*;

public class LogTest {
    public static void main(String[] argv){
        Logger logger = Logger.getLogger("EuclidsTheorem.TestException");

        logger.severe("Log1");
        logger.warning("Log2");
        logger.info("Log3");
        logger.config("Log4");
        logger.fine("Log5");
        logger.finer("Log6");
        logger.finest("Log7");
    }
}