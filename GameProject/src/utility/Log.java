/**
 *
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.Sys;

/**
 * @author eshinig
 *
 */
public class Log
{


	public static List<String> logList = new ArrayList<String>();
	/**
	 * @param args
	 */
/*	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		LogMessage lm = new LogMessage();


		logList.add(lm.getLogMessageFormat(lm.getClass().toString(), "Tower"));
		logList.add(lm.getLogMessageFormat(lm.getClass().toString(), "TowerCannon"));
		logList.add(lm.getLogMessageFormat(lm.getClass().toString(), "TowerBomb"));
		logList.add(lm.getLogMessageFormat(lm.getClass().toString(), "TowerCannon12"));
		logList.add(lm.getLogMessageFormat(lm.getClass().toString(), "Tower"));
		logList.add(lm.getLogMessageFormat(lm.getClass().getCanonicalName(), "TowerBomb"));
		for(String log: logList)
			System.out.println(log);

		String name="";
		for(int i =0;i<logList.size();i++)
		{
			//System.out.println(logList.get(i).matches("(?i:.*\\btowercannon[0-9].*)"));
			//System.out.println(logList.get(i).matches("(?i:.*\\btowercannon[0-9].*)"));
			if(name.equals(""))
				System.out.println(logList.get(i));
		}

	}*/

	/**
	 * THis method adds the log message with time stamp
	 */

	public static void addLogMessage(String className,String message)
	{   
		String logMessage;
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
		logMessage=ft.format(dNow) + " : " + className + " : "+ message;
		logList.add(logMessage);
	}

	/**
	 * THis method prints the logs for 
	 * 1 specific towers 
	 * 2 all towers of one type 
	 * 3 all towers
	 * 4 Wave events
	 * 5 Global events
	 * 
	 * @returns true if key or log exist
	 */
	public static boolean printLogs(String key)
	{
		String pattern;
		if(key==null)
			return false;

		else
		{
			if(key.equals(""))
			{
				for (int i =0;i<logList.size();i++)
					System.out.println(logList.get(i));	

			return true;
			}


			pattern="(?i:.*\\b"+key+".*)";
			for (int i =0;i<logList.size();i++)
			{
				if(logList.get(i).matches(pattern))
					System.out.println(logList.get(i));	
			}
			return true;
		}

	}

}