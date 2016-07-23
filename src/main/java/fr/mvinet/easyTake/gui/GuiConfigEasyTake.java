package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import fr.mvinet.easyTake.Constante;
import fr.mvinet.easyTake.EasyTake;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CycleValueEntry;
import net.minecraftforge.fml.client.config.GuiConfigEntries.NumberSliderEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigEasyTake extends GuiConfig
{
	public GuiConfigEasyTake(GuiScreen screen)
	{
		super(screen,
		// new
		// ConfigElement(EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				getConfigElement(), Constante.MODID, false, false, "Config File For EasyTake");
	}

	private static List<IConfigElement> getConfigElement()
	{
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		String color = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getString();
		Integer transparency = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getInt();

		list.add(new DummyConfigElement("colorFilter", color, ConfigGuiType.STRING, "fr.mvinet.easyTake.EasyTake.config", new String[] { "none", "red",
				"blue" }));
		list.add(new DummyConfigElement("transparency", transparency, ConfigGuiType.INTEGER, "", 0, 100).setCustomListEntryClass(NumberSliderEntry.class));

		String colorDefault = EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("colorFilter").getDefault();
		Integer transparencyDefault = Integer.parseInt(EasyTake.config.getCategory(Configuration.CATEGORY_GENERAL).get("transparency").getDefault());

		list.get(0).set(colorDefault);
		list.get(1).set(transparencyDefault);

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
