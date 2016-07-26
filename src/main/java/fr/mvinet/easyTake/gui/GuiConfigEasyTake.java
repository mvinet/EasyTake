package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import fr.mvinet.easyTake.Constante;
import fr.mvinet.easyTake.EasyTake;
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
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		String color = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getString();
		Integer transparency = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getInt();
		Boolean saveOnDisk = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getBoolean();
		
		list.add(new DummyConfigElement("colorFilter", color, ConfigGuiType.STRING, "easyTake.Config.colorFilter", new String[] { "none", "red",
				"blue" }));
		list.add(new DummyConfigElement("transparency", transparency, ConfigGuiType.INTEGER, "easyTake.Config.transparency", 0, 100).setCustomListEntryClass(NumberSliderEntry.class));
		list.add(new DummyConfigElement("saveOnDisk", saveOnDisk, ConfigGuiType.BOOLEAN, "easyTake.Config.saveOnDisk"));
		
		String colorDefault = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getDefault();
		Integer transparencyDefault = Integer.parseInt(EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getDefault());
		Boolean saveOnDiskDefault = Boolean.parseBoolean(EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("saveOnDisk").getDefault());
		
		list.get(0).set(colorDefault);
		list.get(1).set(transparencyDefault);
		list.get(2).set(saveOnDiskDefault);

		return list;
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		ConfigCategory categ;
		categ = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL);

		for (Object object : this.entryList.listEntries)
		{
			if (object instanceof CycleValueEntry)
			{
				categ.get("colorFilter").set(((CycleValueEntry) object).getCurrentValue());
			}
			else if (object instanceof NumberSliderEntry)
			{
				categ.get("transparency").set(((NumberSliderEntry) object).getCurrentValue().toString());
			}
			else if(object instanceof BooleanEntry)
			{
				categ.get("saveOnDisk").set(((BooleanEntry) object).getCurrentValue().toString());
			}
		}

		EasyTake.config.save();
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
