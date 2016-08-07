package fr.mvinet.easyTake.screen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.client.Minecraft;

public class Screenshot
{
	private Date dateScreen;
	private String title;
	
	public Screenshot(String title, Date date)
	{
		this.title = title;
		this.dateScreen = date;
	}
	
	public static ArrayList<Screenshot> getLocalScreenshot()
	{
		ArrayList<Screenshot> lesScreenshot;
		File directory;
		File[] lesFichiers;
		Screenshot leScreenshot;
		Date date;
		
		lesScreenshot = new ArrayList<Screenshot>();
		directory = new File(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
		lesFichiers = directory.listFiles();
		
		for(File leFichier : lesFichiers)
		{
			date = new Date(leFichier.lastModified());
			leScreenshot = new Screenshot(leFichier.getName(), date);
			lesScreenshot.add(leScreenshot);
		}
		
		return lesScreenshot;
	}
	
	public Date getDate()
	{
		return this.dateScreen;
	}
	
	public String getTitle()
	{
		return this.title;
	}
}
