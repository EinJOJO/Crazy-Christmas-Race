import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


/**
 * @author Johannes.
 */
public class Logger {

    private static Logger instance;
    private boolean printLogs;
    BufferedWriter writer;
    ArrayList<String> stash;

    public Logger() {
        instance = this;
        printLogs = false;
        stash = new ArrayList<>();
        try {
            File file = new File("logs\\logs-" + String.valueOf(System.currentTimeMillis()).substring(6) + ".txt");
            if (!file.canWrite()) {
                warn("No log file could be created");
                return;
            }
            this.writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setPrintLogs(boolean printLogs) {
        this.printLogs = printLogs;
    }

    public boolean isPrintLogs() {
        return printLogs;
    }

    public void info(String string) {
        String prefix = "[INFO] ";
        print(prefix + string);
    }

    public void info(Object object) {
        info(Objects.toString(object, "Object is null"));
    }


    private String stackTrace(StackTraceElement[] s)  {
        String string = "";
        for( StackTraceElement ste : s) {
            string += "\n" + ste;
        }

        return string;
    }

    public void warn(StackTraceElement[] e) {
        warn(stackTrace(e));
    }

    public void warn (String string) {
        print("[WARNING] " + string);
    }

    public static Logger getInstance() {
        if (instance == null) {
            return new Logger();
        }
        return instance;
    }

    private void print(String string)  {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString =  "[" + dtf.format(LocalTime.now()) + "] ";

        String logMessage = timeString + string;
        if (isPrintLogs()) {
            if (!stash.isEmpty()) {
                stash.forEach((message) -> {
                    System.out.println(message);
                });
                stash.clear();
            }
            System.out.println(logMessage);
        } else {
            stash.add(logMessage);
        }
        if (writer != null) {
            try {
                writer.newLine();
                writer.write(logMessage);
                writer.flush();
            } catch (IOException e) {
                System.out.println("Could not write into logging file.");
                e.printStackTrace();
            }
        }
    }
}
