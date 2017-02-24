package fr.mvinet.easyTake;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.immutable.Stream.Cons;

public class PlayerEventHandler {

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent e)
	{
		if(e.getEntity() instanceof EntityPlayerSP)
		{
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			try 
			{
				URL url = new URL(Constante.UPDATE_URL);
				InputStream ins = url.openStream();
				BufferedReader txt = new BufferedReader(new InputStreamReader(ins));
				String urlv = txt.readLine();
				if(!Constante.VERSION.equalsIgnoreCase(urlv))
				{
					ClickEvent event = new ClickEvent(Action.OPEN_URL, Constante.UPDATE_DOWNLOAD);
					Style style = new Style().setClickEvent(event);
					TextComponentTranslation tct = (TextComponentTranslation) new TextComponentTranslation(Constante.UPDATE_WITHURL).setStyle(style);

					player.sendMessage(tct);
				}
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
				player.sendMessage(new TextComponentTranslation(Constante.UPDATE_DEFAULT));
			}
		}
	}
}
