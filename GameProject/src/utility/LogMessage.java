/**
 * 
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author eshinig
 *
 */
public class LogMessage
{
    String time;
    String className;
    String message;
    
    public String getLogMessageFormat(String className,String message)
    {   String logMessage;
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        this.time=ft.format(dNow);
        this.className = className;
        this.message = message;
        logMessage=this.time + " : " + this.className + " : "+ this.message;
        System.out.println("LogMessage" + logMessage);
        return logMessage;
    }
}
