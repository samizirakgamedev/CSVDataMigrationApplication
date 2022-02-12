package csvprojectteamone.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogClass {
    private static final Logger logger = LogManager.getLogger("CSV-DM Logger");
    public static void logInfo(String message){
        logger.info(message);
    }
    public static void logError(String message){
        logger.error(message);
    }
    public static void logWarn(String message){
        logger.warn(message);
    }
}