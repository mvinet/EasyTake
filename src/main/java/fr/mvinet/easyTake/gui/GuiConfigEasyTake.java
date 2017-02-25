package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import fr.mvinet.easyTake.Config;
import fr.mvinet.easyTake.Constant;
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

/**
 * Config Easy Take Interface
 * 
 * @author mvinet
 */
public class GuiConfigEasyTake extends GuiConfig {

	/**
	 * Constructor
	 * @param screen the screen interface
	 */
	public GuiConfigEasyTake(GuiScreen screen) {
		super(screen, getConfigElement(), Constant.MODID, false, false, "EasyTake Configuration");
	}

	/**
	 * Get the configuration
	 * @return a list of {@link IConfigElement}
	 */
	private static List<IConfigElement> getConfigElement() {
		return Config.getConfigElement();
	}

	/**
	 * Called when the gui is closed
	 */
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		Config.saveConfig(this.entryList.listEntries);
	}

	/**
	 * Called when the gui is initialized
	 */
	@Override
	public void initGui() {
		super.initGui();
	}

	/**
	 * Draw the screen
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	/**
	 * Called when button is performed
	 */
	@Override
	public void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
	}
}
