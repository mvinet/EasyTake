package fr.mvinet.easyTake.screen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.client.Minecraft;

/**
 * Screenshot
 * 
 * @author mvinet
 */
public class Screenshot {

	/**
	 * The file
	 */
	private File file;
	
	/**
	 * Constructor
	 * @param file the File
	 */
	public Screenshot(File file) {
		this.file = file;
	}

	/**
	 * Return all the Screenshot saved
	 * @return a list of {@link Screenshot}
	 */
	public static ArrayList<Screenshot> getLocalScreenshot() {
		ArrayList<Screenshot> lesScreenshot;
		File directory;
		File[] lesFichiers;
		
		lesScreenshot = new ArrayList<Screenshot>();
		directory = new File(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
		lesFichiers = directory.listFiles();
		
		for(File leFichier : lesFichiers) {
			lesScreenshot.add(new Screenshot(leFichier));
		}
		
		return lesScreenshot;
	}
	
	/**
	 * @return the {@link Date}
	 */
	public Date getDate() {
		Date date = new Date();
		date.setTime(this.file.lastModified());
		
		return date;
	}
	
	/**
	 * @return the Title
	 */
	public String getTitle() {
		return this.file.getName();
	}
	
	/**
	 * @return the {@link File}
	 */
	public File getFile() {
		return this.file;
	}
}
