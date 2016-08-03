package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import fr.mvinet.easyTake.Config;
import fr.mvinet.easyTake.Constante;
import fr.mvinet.easyTake.EasyTake;
import fr.mvinet.easyTake.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries.*;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigEasyTake extends GuiConfig
{
	public GuiConfigEasyTake(GuiScreen screen)
	{
		super(screen, getConfigElement(), Constante.MODID, false, false, "EasyTake Configuration");
	}

	private static List<IConfigElement> getConfigElement()
	{
		return Config.getConfigElement();
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();

		Config.saveConfig(this.entryList.listEntries);
	}

	public void initGui()
	{
		super.initGui();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);
	}
}
