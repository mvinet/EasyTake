package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import fr.mvinet.easyTake.screen.Screenshot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;

public class GuiListScreenshotSelection extends GuiListExtended
{
	private GuiScreenshotSelection screenshotselect;
    private final List<GuiListScreenshotSelectionEntry> entries = Lists.<GuiListScreenshotSelectionEntry>newArrayList();
    
	public GuiListScreenshotSelection(GuiScreenshotSelection select, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn)
	{
		//GuiListWorldSelection
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		this.screenshotselect = select;
		
		refreshList();
	}

	public void refreshList()
	{
		for (Screenshot screenshot : Screenshot.getLocalScreenshot())
        {
            this.entries.add(new GuiListScreenshotSelectionEntry(this, screenshot));
        }
	}
	
	@Override
	public IGuiListEntry getListEntry(int index)
	{
		return entries.get(index);
	}

	@Override
	protected int getSize()
	{
		return entries.size();
	}
	
    public GuiScreenshotSelection getGuiScreenshotSelection()
    {
        return this.screenshotselect;
    }
    
    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 20;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 50;
    }


}
