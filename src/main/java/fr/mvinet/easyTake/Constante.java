package fr.mvinet.easyTake;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

public class Constante
{
	public static final String PREFIX = "[" + TextFormatting.DARK_GREEN + "Easy" + TextFormatting.GOLD + "Take"
			+ TextFormatting.WHITE + "]";

	public static final String MODID = "easytake";
	public static final String VERSION = "0.2.4";

	public static final String UPLOAD_CLIPBOARD = "easytake.Upload.clipboard";
	public static final String UPLOAD_STARTSEND = "easytake.Upload.startsend";
	public static final String UPLOAD_ERROR = "easytake.error";

	public static final String UPDATE_URL = "http://dev.mvinet.fr/EasyTake/version.txt";
	public static final String UPDATE_DOWNLOAD = "http://easytake.tk/download.html";
	public static final String UPDATE_WITHURL = "easytake.Update.newUpdate";
	public static final String UPDATE_DEFAULT = "easytake.Update.default";

	public static final File PATHSCREENSHOT = new File(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
}
