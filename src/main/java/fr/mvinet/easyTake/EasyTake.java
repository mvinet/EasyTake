package fr.mvinet.easyTake;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.ConfigGuiType;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = EasyTake.MODID, version = EasyTake.VERSION, name = "EasyTake", guiFactory = "fr.mvinet.easyTake.gui.GuiFactoryEasyTake")
public class EasyTake
{
	@Instance(EasyTake.MODID)
	public static EasyTake instance;

	@SidedProxy(clientSide = "fr.mvinet.easyTake.ClientProxy", serverSide = "fr.mvinet.easyTake.CommonProxy")
	
	public static CommonProxy proxy;
	public static Configuration config;
	
	public static final String MODID = "easyTake";
	public static final String VERSION = "0.04";

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		File directory = new File(Minecraft.getMinecraft().mcDataDir
				+ "//EasyTake//Screen");

		if (!directory.exists())
		{
			directory.mkdirs();
		}

		config = new Configuration(e.getSuggestedConfigurationFile());
		synConfig();

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(this);
	}
	
	public static void synConfig()
	{
		config.load();

		config.getString("colorFilter", Configuration.CATEGORY_GENERAL, "none", "Your chosen color filter!");
		config.getInt("transparency", Configuration.CATEGORY_GENERAL, 0, 0, 100, "Transparency for color filter");
		
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equals(MODID))
		{
			config.save();
		}
	}
}
