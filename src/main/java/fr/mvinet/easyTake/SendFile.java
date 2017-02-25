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

/**
 * Send file
 * 
 * @author mvinet
 */
public class SendFile extends Thread {

	/**
	 * The file
	 */
	private File file;

	/**
	 * Constructor
	 * @param name the name of the file
	 * @param file the file
	 */
	public SendFile(String name, File file) {
		super(name);
		this.file = file;
	}

	/**
	 * When thread run
	 */
	@Override
	public void run() {
		uploadFile(file);
		this.interrupt();
	}

	/**
	 * Upload a file
	 * @param file the file to upload
	 */
	public static void uploadFile(File file) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		String host = Config.getConfig().getCategory(Configuration.CATEGORY_GENERAL).get("host").getString();
		try {
			player.sendMessage(new TextComponentTranslation(Constant.UPLOAD_STARTSEND));

			String url = Utils.uploadFileAndGetUrl(host, file);
			
			player.sendMessage(new TextComponentTranslation(Constant.UPLOAD_CLIPBOARD));
			
			Utils.copyToClipboard(url);
			ClickEvent event = new ClickEvent(Action.OPEN_URL, url);
			Style style = new Style().setClickEvent(event);
			TextComponentString tct;
			tct = (TextComponentString) new TextComponentString(Constant.PREFIX).appendText(" " + url).setStyle(style);

			player.sendMessage(tct);
			
			if(!Config.getConfig().getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getBoolean()) {
				file.delete();
			}
			
		} catch (Exception e) {
			player.sendMessage(new TextComponentTranslation(Constant.UPLOAD_ERROR));
			e.printStackTrace();
		}
	}
}
