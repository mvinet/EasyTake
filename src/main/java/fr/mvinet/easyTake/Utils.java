package fr.mvinet.easyTake;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;

import org.apache.http.client.methods.HttpPost;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import apache.MultipartEntity;
import apache.content.ContentBody;
import apache.content.FileBody;
import apache.content.StringBody;

/**
 * Utils
 * 
 * @author mvinet
 */
public class Utils {

	/**
	 * List of the colors
	 */
	public static final String[] LISTECOLOR = { "none", "black", "blue", "cyan", "darkgray", "gray", "green",
			"lightgray", "magenta", "orange", "pink", "red", "yellow", "white" };

	/**
	 * List of the hosts
	 */
	public static final String[] LISTHOST = {"Imgur"};

	/**
	 * Return the url of the uploaded file
	 * @param host the host
	 * @param file the file to upload
	 * @return the url of the file
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String uploadFileAndGetUrl(String host, File file) throws ClientProtocolException, IOException {
		String res;

		HttpPost post;
		HttpClient httpclient;
		HttpResponse response;
		HttpEntity resEntity;

		MultipartEntity mpEntity;
		ContentBody cbFile;

		post = new HttpPost("https://api.imgur.com/3/upload");
		post.setHeader("Authorization", "Client-ID e6de7d8d5a3bd1c");

		httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		mpEntity = new MultipartEntity();
		cbFile = new FileBody(file, "image/jpeg");

		mpEntity.addPart("image", cbFile);

		post.setEntity(mpEntity);
		response = httpclient.execute(post);
		resEntity = response.getEntity();

		res = null;

		if (resEntity != null) {
			String jsonString = EntityUtils.toString(resEntity);
			JsonParser parser = new JsonParser();
			JsonObject result = (JsonObject) parser.parse(jsonString);
			JsonObject data = result.get("data").getAsJsonObject();

			res = data.get("link").getAsString();
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}
		httpclient.getConnectionManager().shutdown();

		return res;
	}

	/**
	 * Copy a text in the clipboard
	 * @param text the text to copy
	 */
	public static void copyToClipboard(String text) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clip = toolkit.getSystemClipboard();
		clip.setContents(new StringSelection(text), null);
	}

	/**
	 * Get the good color with a string
	 * @param color the string color
	 * @return the {@link Color}
	 */
	public static Color getColor(String color) {
		if (color.equalsIgnoreCase("black"))
			return Color.black;
		else if (color.equalsIgnoreCase("blue"))
			return Color.blue;
		else if (color.equalsIgnoreCase("cyan"))
			return Color.cyan;
		else if (color.equalsIgnoreCase("darkgray"))
			return Color.darkGray;
		else if (color.equalsIgnoreCase("gray"))
			return Color.gray;
		else if (color.equalsIgnoreCase("green"))
			return Color.green;
		else if (color.equalsIgnoreCase("lightgray"))
			return Color.lightGray;
		else if (color.equalsIgnoreCase("magenta"))
			return Color.magenta;
		else if (color.equalsIgnoreCase("orange"))
			return Color.orange;
		else if (color.equalsIgnoreCase("pink"))
			return Color.pink;
		else if (color.equalsIgnoreCase("red"))
			return Color.red;
		else if (color.equalsIgnoreCase("yellow"))
			return Color.yellow;
		return Color.white;
	}
}
