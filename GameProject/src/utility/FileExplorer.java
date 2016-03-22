/**
 * 
 */
package utility;

import java.io.File;
import java.io.FilenameFilter;

/**
 * THis class handles the MAP XML files for loading the map
 * @author s_niga
 *
 */
public class FileExplorer 
{
	private File folder = null;
	private FilenameFilter xmlFile;
	private File[] listOfFiles ;

	/**
	 * THis method handles the XML files in the current directory path
	 */
	public FileExplorer()
	{
		folder = new File(".");
		xmlFile = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				if(name.endsWith(".xml"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		};

		listOfFiles=folder.listFiles(xmlFile);
	}

	/**
	 * This method displays the XML files
	 */
	public void displayXMLFiles()
	{
		for (int i = 0; i < listOfFiles.length; i++) 
		{
			System.out.println((i+1)+". " + listOfFiles[i].getName());
		}
	}
	
	/**
	 * This method returns the list of files
	 * @param fileNum
	 * @return
	 */
	public String getFileName(int fileNum)
	{
		return listOfFiles[fileNum - 1].getName();
	}
}
