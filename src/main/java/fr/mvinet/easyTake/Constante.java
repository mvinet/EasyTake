package fr.mvinet.easyTake;

import net.minecraft.util.text.TextFormatting;

public class Constante
{
	public static final String PREFIX = "[" + TextFormatting.GREEN + "Easy" + TextFormatting.GOLD + "Take"
			+ TextFormatting.WHITE + "]";

	public static final String MODID = "easytake";
	public static final String VERSION = "1.10.2-0.1.2";

	public static final String UPLOAD_COPIED = PREFIX
			+ " Le liens de votre screen a été copié dans votre presse papier !";
	public static final String UPLOAD_STARTSEND = PREFIX + " Upload en cours ..";
	public static final String UPLOAD_ERROR = PREFIX + " Erreur !";

	public static final String UPDATE_URL = "http://dev.mvinet.fr/EasyTake/version.txt";
	public static final String UPDATE_WITHURL = "{\"text\":\"" + PREFIX + " La version *version* d'EasyTake est maintenant disponible cliquer ici pour la télécharger\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://easytake.tk/download.html\"}}";
	public static final String UPDATE_DEFAULT = PREFIX + " Une nouvelle version d'EasyTake est disponible";
}
