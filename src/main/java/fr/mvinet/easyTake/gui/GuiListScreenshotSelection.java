package fr.mvinet.easyTake.gui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fr.mvinet.easyTake.screen.Screenshot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;

/**
 * List Screenshot Selection Interface
 * 
 * @author mvinet
 */
public class GuiListScreenshotSelection extends GuiListExtended {
	
	/**
	 * Interface for thje screenshot selection
	 */
	private GuiScreenshotSelection screenshotselect;
	
	/**
	 * List for screenshots
	 */
    private final List<GuiListScreenshotSelectionEntry> entries = Lists.<GuiListScreenshotSelectionEntry>newArrayList();
	
    /**
     * Selected index
     */
    private int selectedIdx = -1;
    
    /**
     * Constructor 
     * @param select the selected screenshot
     * @param minecraft minecraft
     * @param width width of the frame
     * @param height height of the frame
     * @param top top
     * @param bottom bottom
     * @param slotHeight height of the slot
     */
	public GuiListScreenshotSelection(GuiScreenshotSelection select, Minecraft minecraft, int width, int height, int top, int bottom, int slotHeight) {
		//GuiListWorldSelection
		super(minecraft, width, height, top, bottom, slotHeight);
		this.screenshotselect = select;
		
		refreshList();
	}

	/**
	 * Actialize the list
	 */
	public void refreshList() {
		for (Screenshot screenshot : Screenshot.getLocalScreenshot())  {
            this.entries.add(new GuiListScreenshotSelectionEntry(this, screenshot));
        }
	}
	
	@Override
	public GuiListScreenshotSelectionEntry getListEntry(int index) {
		return (GuiListScreenshotSelectionEntry)entries.get(index);
	}

	@Override
    protected boolean isSelected(int slotIndex)  {
        return slotIndex == this.selectedIdx;
    }
    
	/**
	 * select the screenshot with the index
	 * @param idx index of the screenshot
	 */
    public void selectScreenshot(int idx) {
    	this.selectedIdx = idx;
    	this.screenshotselect.selectScreenshot(this.getSelectedScreenshot());
    }
    
    /**
     * Get the list of the screenshot
     * @return a {@link GuiListScreenshotSelectionEntry}
     */
    public GuiListScreenshotSelectionEntry getSelectedScreenshot() {
        return this.selectedIdx >= 0 && this.selectedIdx < this.getSize() ? this.getListEntry(this.selectedIdx) : null;
    }
    
	@Override
	protected int getSize() {
		return entries.size();
	}
	
	/**
	 * Get the Screenshot selected
	 * @return the {@link GuiScreenshotSelection}
	 */
    public GuiScreenshotSelection getGuiScreenshotSelection()  {
        return this.screenshotselect;
    }
    
    @Override
    protected int getScrollBarX()  {
        return super.getScrollBarX() + 20;
    }

    @Override
    public int getListWidth()  {
        return super.getListWidth() + 50;
    }
}
