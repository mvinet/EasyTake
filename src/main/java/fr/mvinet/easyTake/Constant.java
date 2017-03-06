package fr.mvinet.easyTake;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

/**
 * The constant
 * 
 * @author mvinet
 */
public class Constant {
	
	/**
	 * The prefix [EasyTake]
	 */
	public static final String PREFIX = "[" + TextFormatting.DARK_GREEN + "Easy" + TextFormatting.GOLD + "Take" + TextFormatting.WHITE + "]";

	/**
	 * The modid
	 */
	public static final String MODID = "easytake";
	
	/**
	 * The version
	 */
	public static final String VERSION = "0.2.4";

	/**
	 * Translation for the clipboard
	 */
	public static final String UPLOAD_CLIPBOARD = "easytake.Upload.clipboard";
	
	/**
	 * Translation when the upload start
	 */
	public static final String UPLOAD_STARTSEND = "easytake.Upload.startsend";
	
	/**
	 * Translatation when error
	 */
	public static final String UPLOAD_ERROR = "easytake.error";

	/**
	 * Url for the update
	 */
	public static final String UPDATE_URL = "http://files.mvinet.fr/EasyTake/version.txt";
	
	/**
	 * Url for the download
	 */
	public static final String UPDATE_DOWNLOAD = "http://easytake.tk/download.html";
	
	/**
	 * Translation for the new update
	 */
	public static final String UPDATE_WITHURL = "easytake.Update.newUpdate";
	
	/**
	 * Translation for the default update
	 */
	public static final String UPDATE_DEFAULT = "easytake.Update.default";

	/**
	 * Path for the screenshot
	 */
	public static final File PATHSCREENSHOT = new File(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
}
