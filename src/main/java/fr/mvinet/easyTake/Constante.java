package fr.mvinet.easyTake;

import net.minecraft.util.text.TextFormatting;

public class Constante
{
	public static final String PREFIX = 
			"[" + TextFormatting.GREEN + "Easy" + TextFormatting.GOLD + "Take" + TextFormatting.WHITE + "]";
	
	public static final String MODID = "easyTake";
	public static final String VERSION = "1.10.2-0.1";
	
	public static final String UPLOAD_COPIED = PREFIX + " Url of your screenshot was copied to the clipboard !";
	public static final String UPLOAD_STARTSEND = PREFIX + " Upload in progress..";
	public static final String UPLOAD_ERROR = PREFIX + " Error !";

	
	public static final String UPDATE_URL = "http://dev.mvinet.fr/EasyTake/version.txt";
	public static final String UPDATE_WITHURL = PREFIX + " A new release of EasyTake *version* is now available on http://build.mvinet.fr/job/EasyTake/ws/build/libs/";
	public static final String UPDATE_DEFAULT = PREFIX + " A new release of EasyTake is now available";
}
