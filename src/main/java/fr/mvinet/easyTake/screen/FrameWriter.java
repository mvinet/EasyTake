package fr.mvinet.easyTake.screen;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.Sys;

import net.minecraftforge.common.config.Configuration;
import fr.mvinet.easyTake.EasyTake;
import fr.mvinet.easyTake.SendFile;
import fr.mvinet.easyTake.Utils;

public class FrameWriter
{

	private static File outputFile;
	public int writerID;

	public FrameWriter(int id)
	{
		writerID = id;
	}

	// Sets the output file of the images.
	public static void setOutput(File file)
	{
		outputFile = file;
	}

	// Sets the output file of the images.
	public static void setOutput(String location)
	{
		outputFile = new File(location);
	}

	// Returns the output file, used for saving images.
	public static File getOutput()
	{
		return outputFile;
	}

	// Returns the files inside of the output file, used for loading in images.
	public static List<File> getOutputFiles()
	{
		return Arrays.asList(outputFile.listFiles());
	}

	// Saves the frame to the output file.
	public static void saveFrameAsImage(Frame frame)
	{
		try
		{
			// Filter.overlayFrameFromColor(frame, Color.RED, 0.4f);
			final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
			File output = new File(outputFile, "screenshot_" + dateFormat.format(new Date()).toString() + ".jpg");

			String color = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getString();
			Float transparency = (float)EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getDouble();;
			
			System.out.println(color + " " + transparency);
			if (!color.equalsIgnoreCase("none"))
			{
				Filter.overlayFrameFromColor(frame, Utils.getColor(color), transparency / 100F);
			}

			ImageIO.write(frame.getBufferedImage(), "jpg", output);

			SendFile sfile = new SendFile("sendfile", output);
			sfile.start();
			

		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}