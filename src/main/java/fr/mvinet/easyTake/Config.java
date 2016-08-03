package fr.mvinet.easyTake;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.config.GuiConfigEntries.BooleanEntry;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CycleValueEntry;
import net.minecraftforge.fml.client.config.GuiConfigEntries.IConfigEntry;
import net.minecraftforge.fml.client.config.GuiConfigEntries.NumberSliderEntry;

public class Config
{
	private static Configuration config;
	
	public static void synConfig()
	{
		config.load();

		config.getString("colorFilter", Configuration.CATEGORY_GENERAL, "none", "Votre filtre de couleur");
		config.getInt("transparency", Configuration.CATEGORY_GENERAL, 0, 0, 100, "La transparence du filtre de couleur");
		config.getBoolean("saveOnDisk", Configuration.CATEGORY_GENERAL, true, "Sauvegarde l'image en local");
		config.getBoolean("showOverlayEasyTake", Configuration.CATEGORY_GENERAL, true, "Affiche l'overlay Easytake");
		
		if(config.hasChanged())
		{
			config.save();
		}
	}

	public static List<IConfigElement> getConfigElement()
	{
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		String color = config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getString();
		Integer transparency = config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getInt();
		Boolean saveOnDisk = config.getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getBoolean();
		Boolean showOverlayEasyTake = config.getCategory(Configuration.CATEGORY_GENERAL).get("showOverlayEasyTake").getBoolean();
		
		list.add(new DummyConfigElement("colorFilter", color, ConfigGuiType.STRING, "easyTake.Config.colorFilter", Utils.LISTECOLOR));
		list.add(new DummyConfigElement("transparency", transparency, ConfigGuiType.INTEGER, "easyTake.Config.transparency", 0, 100).setCustomListEntryClass(NumberSliderEntry.class));
		list.add(new DummyConfigElement("saveOnDisk", saveOnDisk, ConfigGuiType.BOOLEAN, "easyTake.Config.saveOnDisk"));
		list.add(new DummyConfigElement("showOverlayEasyTake", showOverlayEasyTake, ConfigGuiType.BOOLEAN, "easyTake.Config.showOverlayEasyTake"));
		
		String colorDefault = config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getDefault();
		Integer transparencyDefault = Integer.parseInt(config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getDefault());
		Boolean saveOnDiskDefault = Boolean.parseBoolean(config.getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getDefault());
		Boolean showOverlayEasyTakeDefault = Boolean.parseBoolean(config.getCategory(Configuration.CATEGORY_GENERAL).get("showOverlayEasyTake").getDefault());
		
		list.get(0).set(colorDefault);
		list.get(1).set(transparencyDefault);
		list.get(2).set(saveOnDiskDefault);
		list.get(3).set(showOverlayEasyTakeDefault);
		
		return list;
	}
	
	public static void saveConfig(List<IConfigEntry> entry)
	{		
		ConfigCategory categ;
		categ = config.getCategory(Configuration.CATEGORY_GENERAL);

		categ.get("colorFilter").set(((CycleValueEntry) entry.get(0)).getCurrentValue());
		categ.get("transparency").set(((NumberSliderEntry) entry.get(1)).getCurrentValue().toString());
		categ.get("saveOnDisk").set(((BooleanEntry) entry.get(2)).getCurrentValue().toString());
		categ.get("showOverlayEasyTake").set(((BooleanEntry) entry.get(3)).getCurrentValue().toString());
		
		config.save();
	}
	
	public static void setConfig(Configuration config)
	{
		Config.config = config;
	}
	
	public static Configuration getConfig()
	{
		return Config.config;
	}
}
