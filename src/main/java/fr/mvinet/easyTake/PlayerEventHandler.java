package fr.mvinet.easyTake;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
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
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
			System.out.println(Constante.UPDATE_WITHURL);
			try 
			{
				URL url = new URL(Constante.UPDATE_URL);
				InputStream ins = url.openStream();
				BufferedReader txt = new BufferedReader(new InputStreamReader(ins));
				String urlv = txt.readLine();
				if(!Constante.VERSION.equalsIgnoreCase(urlv))
				{
					player.addChatMessage(ITextComponent.Serializer.jsonToComponent(Constante.UPDATE_WITHURL.replace("*version*", urlv)));
				}
			} 
			catch (Exception e2) 
			{
				player.addChatMessage(new TextComponentString(Constante.UPDATE_DEFAULT));
			}
		}
	}
}
