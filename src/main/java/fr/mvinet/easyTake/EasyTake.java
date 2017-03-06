package fr.mvinet.easyTake;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicHeader;

import apache.Header;
import apache.MinimalField;

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

/**
 * EasyTake 
 * 
 * @author mvinet
 */
@Mod(modid = Constant.MODID, version = Constant.VERSION, name = "EasyTake", 
	guiFactory = "fr.mvinet.easyTake.gui.GuiFactoryEasyTake", canBeDeactivated = true, 
	clientSideOnly = true, updateJSON = "http://dev.mvinet.fr/EasyTake/version.json")
public class EasyTake {
	
	/**
	 * The instance of the mod
	 */
	@Instance(Constant.MODID)
	private static EasyTake instance;

	/**
	 * The proxy
	 */
	@SidedProxy(clientSide = "fr.mvinet.easyTake.ClientProxy", serverSide = "fr.mvinet.easyTake.CommonProxy")
	public static CommonProxy proxy;
	
	/**
	 * List of uploader 
	 */
	private List<Uploader> uploader;
	
	/**
	 * The pre init of the mod
	 * @param event the event of the pre-initialization
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		initUploader();
		File directory = Constant.PATHSCREENSHOT;

		if (!directory.exists()) {
			directory.mkdirs();
		}

		Config.setConfig(new Configuration(event.getSuggestedConfigurationFile()));
		Config.synConfig();
	}
	
	/**
	* Initialize the uploader
	 */
	private void initUploader() {
		uploader = new ArrayList<>();
		
		//Setup Imgur
		Uploader upImgur = new Uploader("Imgur");
		upImgur.setHost("https://imgur.com");
		upImgur.setPathUpload("https://api.imgur.com/3/upload");
		upImgur.getHeader().add(new BasicHeader("Authorization", "Client-ID e6de7d8d5a3bd1c"));
		uploader.add(upImgur);
	}

	/**
	 * The init of the mod
	 * @param event the event of the initialization
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(this);
	}
	
	/**
	 * Called when config is changed
	 * @param event the event
	 */
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(event.getModID().equals(Constant.MODID)) {
			Config.getConfig().save();
		}
	}
	
	/**
	 * @return the instance
	 */
	public static EasyTake getInstance() {
		return instance;
	}
	
	/**
	 * @return the uploader
	 */
	public List<Uploader> getUploader() {
		return uploader;
	}
}
