package fr.mvinet.easyTake;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.corba.se.impl.orbutil.closure.Constant;

import apache.MultipartEntity;
import apache.content.ContentBody;
import apache.content.FileBody;
import apache.content.StringBody;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
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

	@SuppressWarnings("resource")
	public static void sendPost(File file)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

		try
		{
			player.addChatComponentMessage(new TextComponentTranslation(Constante.UPLOAD_STARTSEND));
			
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

			HttpPost httppost = Utils.getApiImgur("image");
			httppost.setHeader("Authorization", "Client-ID c37f45c23120776");

			MultipartEntity mpEntity = new MultipartEntity();
			ContentBody cbFile = new FileBody(file, "image/jpeg");

			mpEntity.addPart("image", cbFile);
			mpEntity.addPart(
					"title", 
					new StringBody("Captured by " + player.getName() + " with EasyTake")
			);
			
			httppost.setEntity(mpEntity);
			System.out.println("executing request " + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();

			System.out.println(response.getStatusLine());

			if (resEntity != null)
			{
				String jsonString = EntityUtils.toString(resEntity);
				JsonParser parser = new JsonParser();
				JsonObject result = (JsonObject)parser.parse(jsonString);
				JsonObject data = result.get("data").getAsJsonObject();
				
				player.addChatComponentMessage(new TextComponentTranslation(Constante.UPLOAD_CLIPBOARD));
				Utils.Copier(data.get("link").getAsString());
				
				ClickEvent event = new ClickEvent(Action.OPEN_URL, data.get("link").getAsString());
				Style style = new Style().setClickEvent(event);
				TextComponentString tct;
				tct = (TextComponentString) new TextComponentString(Constante.PREFIX).appendText(" " + data.get("link").getAsString()).setStyle(style);

				player.addChatComponentMessage(tct);
			}
			if (resEntity != null)
			{
				resEntity.consumeContent();
			}

			httpclient.getConnectionManager().shutdown();
			
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
