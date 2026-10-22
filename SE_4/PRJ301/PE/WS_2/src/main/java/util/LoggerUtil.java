package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {
    private static final String LOGS_DIR = "src/main/resources/logs";
    private static final String LOG_FILE = "log.dat";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        try {
            // Get the absolute path using classpath
            String classPath = LoggerUtil.class.getClassLoader().getResource(".").getPath();
            String projectPath = classPath.substring(0, classPath.indexOf("target"));
            
            // Create logs directory if it doesn't exist
            File logsDir = new File(projectPath, LOGS_DIR);
            if (!logsDir.exists()) {
                logsDir.mkdirs();
            }

            String logEntry = createLogEntry(message);
            File logFile = new File(logsDir, LOG_FILE);
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(logEntry);
            }
        } catch (Exception e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private static String createLogEntry(String message) {
        String timestamp = DATE_FORMAT.format(new Date());
        String hostname = System.getenv("COMPUTERNAME");
        String osName = System.getProperty("os.name");
        String osUsername = System.getProperty("user.name");
        
        return String.format("[%s] Host: %s, User: %s, OS: %s - %s%n",
                timestamp, hostname, osUsername, osName, message);
    }
}
