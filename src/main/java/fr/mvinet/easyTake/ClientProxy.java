package fr.mvinet.easyTake;

import java.awt.image.BufferedImage;

import org.lwjgl.input.Keyboard;

import fr.mvinet.easyTake.gui.GuiConfigEasyTake;
import fr.mvinet.easyTake.gui.GuiScreenshotSelection;
import fr.mvinet.easyTake.screen.CameraHelper;
import fr.mvinet.easyTake.screen.Frame;
import fr.mvinet.easyTake.screen.FrameWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClientProxy extends CommonProxy{

	private static KeyBinding keyScreen;
	private static KeyBinding keyGuiOption;
	private static KeyBinding keyListScreen;
	
	public ClientProxy()
	{
		FMLCommonHandler.instance().bus().register(this);
		keyScreen = new KeyBinding("Screen", Keyboard.KEY_U, "EasyTake");
		keyGuiOption = new KeyBinding("Option", Keyboard.KEY_Y, "EasyTake");
		keyListScreen = new KeyBinding("List Screen", Keyboard.KEY_I, "EasyTake");
		
		ClientRegistry.registerKeyBinding(keyScreen);
		ClientRegistry.registerKeyBinding(keyGuiOption);
		ClientRegistry.registerKeyBinding(keyListScreen);
	}


	@SubscribeEvent
	public void onEvent(KeyInputEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if(keyScreen.isPressed())
		{
			screen();
		}
		
		if(keyGuiOption.isPressed())
		{
			mc.displayGuiScreen(new GuiConfigEasyTake(mc.currentScreen));
		//	Minecraft.getMinecraft().displayGuiScreen(new GuiEasyTake());
		}
		
		if(keyListScreen.isPressed())
		{
		//	mc.displayGuiScreen(new GuiScreenshotSelection(mc.currentScreen));
		}
	}

	public void screen()
	{
		BufferedImage image = CameraHelper.takeScreenShot();
		Frame frame = Frame.getFrameFromBufferedImage(image);
		FrameWriter.saveFrameAsImage(frame);

	}

}
