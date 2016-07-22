package fr.mvinet.easyTake;

import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import fr.mvinet.easyTake.gui.GuiConfigEasyTake;
import fr.mvinet.easyTake.screen.CameraHelper;
import fr.mvinet.easyTake.screen.Frame;
import fr.mvinet.easyTake.screen.FrameWriter;

public class ClientProxy extends CommonProxy{

	private static KeyBinding keyScreen;
	private static KeyBinding keyGui;
	
	public ClientProxy()
	{
		FMLCommonHandler.instance().bus().register(this);
		keyScreen = new KeyBinding("Screen", Keyboard.KEY_U, "EasyTake");
		keyGui = new KeyBinding("Gui", Keyboard.KEY_Y, "EasyTake");
		
		ClientRegistry.registerKeyBinding(keyScreen);
		ClientRegistry.registerKeyBinding(keyGui);
	}


	@SubscribeEvent
	public void onEvent(KeyInputEvent event)
	{
		if(keyScreen.isPressed())
		{
			screen();
		}
		
		if(keyGui.isPressed())
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiConfigEasyTake(Minecraft.getMinecraft().currentScreen));
		//	Minecraft.getMinecraft().displayGuiScreen(new GuiEasyTake());
		}
	}

	public void screen()
	{
		BufferedImage image = CameraHelper.takeScreenShot();
		Frame frame = Frame.getFrameFromBufferedImage(image);
		FrameWriter.saveFrameAsImage(frame);

	}

}
