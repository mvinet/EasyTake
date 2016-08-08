package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fr.mvinet.easyTake.screen.Screenshot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;

public class GuiListScreenshotSelection extends GuiListExtended
{
	private GuiScreenshotSelection screenshotselect;
    private final List<GuiListScreenshotSelectionEntry> entries = Lists.<GuiListScreenshotSelectionEntry>newArrayList();
	private int selectedIdx = -1;
    
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
	public GuiListScreenshotSelectionEntry getListEntry(int index)
	{
		return (GuiListScreenshotSelectionEntry)entries.get(index);
	}

    /**
     * Returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int slotIndex)
    {
        return slotIndex == this.selectedIdx;
    }
    
    public void selectScreenshot(int idx)
    {
    	this.selectedIdx = idx;
    	this.screenshotselect.selectScreenshot(this.getSelectedScreenshot());
    }
    
    @Nullable
    public GuiListScreenshotSelectionEntry getSelectedScreenshot()
    {
        return this.selectedIdx >= 0 && this.selectedIdx < this.getSize() ? this.getListEntry(this.selectedIdx) : null;
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
