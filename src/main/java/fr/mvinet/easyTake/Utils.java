package fr.mvinet.easyTake;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

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

import org.apache.http.client.methods.HttpPost;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utils
{

	public static HttpPost getApiImgur(String url)
	{
		HttpPost post = new HttpPost("https://api.imgur.com/3/" + url);
		
		return post;
	}
	
	public static void Copier(String text)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clip = toolkit.getSystemClipboard();
		clip.setContents(new StringSelection(text), null);
	}

	/**
	 * return the Color of the parametre
	 */
	public static Color getColor(String color)
	{
		if (color.equalsIgnoreCase("red"))
			return Color.RED;
		else if (color.equalsIgnoreCase("green"))
			return Color.GREEN;
		else if (color.equalsIgnoreCase("blue"))
			return Color.BLUE;
		return null;
	}

}
