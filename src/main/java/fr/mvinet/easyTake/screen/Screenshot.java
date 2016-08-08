package fr.mvinet.easyTake.screen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.client.Minecraft;

public class Screenshot
{
	private File file;
	
	public Screenshot(File file)
	{
		this.file = file;
	}
	
	public static ArrayList<Screenshot> getLocalScreenshot()
	{
		ArrayList<Screenshot> lesScreenshot;
		File directory;
		File[] lesFichiers;
		
		lesScreenshot = new ArrayList<Screenshot>();
		directory = new File(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
		lesFichiers = directory.listFiles();
		
		for(File leFichier : lesFichiers)
		{
			lesScreenshot.add(new Screenshot(leFichier));
		}
		
		return lesScreenshot;
	}
	
	public Date getDate()
	{
		Date date = new Date();
		date.setTime(this.file.lastModified());
		
		return date;
	}
	
	public String getTitle()
	{
		return this.file.getName();
	}
	
	public File getFile()
	{
		return this.file;
	}
}
