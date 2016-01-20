package road.rage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logs system event to a dedicated log file.
 * @author Richard Dong
 */
public class EventLogger 
{
    private static final Logger logEvent = Logger.getLogger("EventLogger"); 
    private static final SimpleFormatter sf = new SimpleFormatter();
    private static Handler fhEvent; 

    /**
     * Sets up event log with correct formatting and tools to append 
     * existing messages.
     */
    public static void setupEvent()
    { 
        try 
        {
            fhEvent = new FileHandler("logs/SystemEvent.txt",999999,1,true);
            fhEvent.setFormatter(sf);
            logEvent.addHandler(fhEvent);
        } 
        catch (IOException | SecurityException e)
        {
            ErrorLogger.logIOError("Could not initialize system event log",e);
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error with initializing system event log",ex);
        }
    }

    /**
     * Logs a message into the error logging file.
     * @param message the message to log.
     */
    public static void logEvent(String message)
    {
        try 
        {
            if(fhEvent == null)//setup the log file if nonexistant
            {
                setupEvent();
            } 
            logEvent.log(Level.INFO,message + "\n\n");//log message
        }
        catch(Exception ex)
        {
            ErrorLogger.logRuntimeError("Unknown error with logging event",ex);
        }
    }
}

