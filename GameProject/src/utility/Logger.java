/**
 * 
 */
package utility;

/**
 * @author eshinig
 *
 */
public class Logger
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        LogMessage lm = new LogMessage();
        System.out.println(lm.getLogMessageFormat(lm.getClass().toString(), "This is first Log"));
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(lm.getLogMessageFormat(lm.getClass().getName(), "This is first Log"));
        
    }

}
