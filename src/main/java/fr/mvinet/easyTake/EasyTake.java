package fr.mvinet.easyTake;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
		modid = Constante.MODID, 
		version = Constante.VERSION, 
		name = "EasyTake", 
		guiFactory = "fr.mvinet.easyTake.gui.GuiFactoryEasyTake",
		canBeDeactivated = true,
		clientSideOnly = true,
		updateJSON = "http://dev.mvinet.fr/EasyTake/version.json"
)

public class EasyTake
{
	@Instance(Constante.MODID)
	public static EasyTake instance;

	@SidedProxy(clientSide = "fr.mvinet.easyTake.ClientProxy", serverSide = "fr.mvinet.easyTake.CommonProxy")
	
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		File directory = new File(Minecraft.getMinecraft().mcDataDir
				+ "//EasyTake//Screen");

		if (!directory.exists())
		{
			directory.mkdirs();
		}

		Config.setConfig(new Configuration(e.getSuggestedConfigurationFile()));
		Config.synConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equals(Constante.MODID))
		{
			Config.getConfig().save();
		}
	}
}
