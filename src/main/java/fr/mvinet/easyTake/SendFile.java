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

import apache.MultipartEntity;
import apache.content.ContentBody;
import apache.content.FileBody;
import net.minecraft.client.Minecraft;

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
		/**
		 * {"data":{ "id":"x07Mopl", "title":null, "description":null,
		 * "datetime":1455217972, "type":"image\/jpeg", "animated":false,
		 * "width":854, "height":480, "size":48104, "views":0, "bandwidth":0,
		 * "vote":null, "favorite":false, "nsfw":null, "section":null,
		 * "account_url":null, "account_id":0, "comment_preview":null,
		 * "deletehash":"aU38YcGx9PMTcfO", "name":"", "link":
		 * "http:\/\/i.imgur.com\/x07Mopl.jpg"},"success":true,"status":200}
		 */
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

			HttpPost httppost = Utils.getApiImgur("image");
			httppost.setHeader("Authorization", "Client-ID 10d70a48840de35");

			MultipartEntity mpEntity = new MultipartEntity();
			ContentBody cbFile = new FileBody(file, "image/jpeg");

			mpEntity.addPart("image", cbFile);

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
				
			//	Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Copied in clipboard !"));
				Utils.Copier(data.get("link").getAsString());
			}
			if (resEntity != null)
			{
				resEntity.consumeContent();
			}

			httpclient.getConnectionManager().shutdown();

			//Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Screen Save"));
		}
		catch (Exception e)
		{
		//	Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Error"));
			e.printStackTrace();
		}
	}
}
