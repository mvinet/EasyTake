package fr.mvinet.easyTake;

import java.io.File;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.common.config.Configuration;

public class SendFile extends Thread
{

	private File file;

	public SendFile(String name, File file)
	{
		super(name);
		this.file = file;
	}

	public void run()
	{
		sendPost(file);
		this.stop();
	}

	public static void sendPost(File file)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		String host = Config.getConfig().getCategory(Configuration.CATEGORY_GENERAL).get("host").getString();
		try
		{
			player.addChatComponentMessage(new TextComponentTranslation(Constante.UPLOAD_STARTSEND));

			String url = Utils.getApi(host, file);
			
			player.addChatComponentMessage(new TextComponentTranslation(Constante.UPLOAD_CLIPBOARD));
			

			Utils.Copier(url);
			ClickEvent event = new ClickEvent(Action.OPEN_URL, url);
			Style style = new Style().setClickEvent(event);
			TextComponentString tct;
			tct = (TextComponentString) new TextComponentString(Constante.PREFIX).appendText(" " + url).setStyle(style);

			player.addChatComponentMessage(tct);
			
			if(!Config.getConfig().getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getBoolean())
			{
				file.delete();
			}
			
		}
		catch (Exception e)
		{
			player.addChatComponentMessage(new TextComponentTranslation(Constante.UPLOAD_ERROR));
			e.printStackTrace();
		}
	}
}
