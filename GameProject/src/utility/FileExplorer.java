/**
 * 
 */
package utility;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author s_niga
 *
 */
public class FileExplorer {
	private File folder = null;
	private FilenameFilter xmlFile;
	private File[] listOfFiles ;

	public FileExplorer() {
		folder =new File(".");

		xmlFile = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.endsWith(".xml"))
					return true;
				else
					return false;
			}
		};

		listOfFiles=folder.listFiles(xmlFile);

	}

	public void displayXMLFiles()
	{
		for (int i = 0; i < listOfFiles.length; i++) 
			System.out.println((i+1)+". " + listOfFiles[i].getName());

	}
	
	public String getFileName(int fileNum)
	{
		return listOfFiles[fileNum-1].getName();
	}
	
	

}
