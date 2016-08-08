package fr.mvinet.easyTake.gui;
import fr.mvinet.easyTake.screen.Screenshot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListWorldSelection;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.gui.GuiWorldEdit;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiListScreenshotSelectionEntry implements IGuiListEntry
{
    private final Minecraft client;
    private final GuiScreenshotSelection selection;
    private final GuiListScreenshotSelection containingListSel;
    private final Screenshot screenshot;
    
    public GuiListScreenshotSelectionEntry(GuiListScreenshotSelection listScreenSelIn, Screenshot screenshot)
    {
    	//GuiListWorldSelectionEntry
		this.client = Minecraft.getMinecraft();
		this.containingListSel = listScreenSelIn;
		this.selection = listScreenSelIn.getGuiScreenshotSelection();
		this.screenshot = screenshot;
    	
    }

	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
	{
		this.client.fontRendererObj.drawString(screenshot.getTitle(), x + 32 + 3, y + 1, 16777215);
        this.client.fontRendererObj.drawString(screenshot.getDate().toString(), x + 32 + 3, y + this.client.fontRendererObj.FONT_HEIGHT + 3, 8421504);
        this.client.fontRendererObj.drawString(screenshot.getTitle(), x + 32 + 3, y + (this.client.fontRendererObj.FONT_HEIGHT * 2) + 3, 8421504);
	}


	public void deleteScreenshot()
	{
		this.client.displayGuiScreen(new GuiYesNo(new GuiYesNoCallback()
        {
            public void confirmClicked(boolean result, int id)
            {
                if (result)
                {
                	GuiListScreenshotSelectionEntry.this.screenshot.getFile().delete();
                    GuiListScreenshotSelectionEntry.this.containingListSel.refreshList();
                }
                GuiListScreenshotSelectionEntry.this.client.displayGuiScreen(new GuiScreenshotSelection(GuiListScreenshotSelectionEntry.this.client.currentScreen));
                
            }
        }, I18n.format("easytake.selectScreenshot.deleteQuestion", new Object[0]), "\'" + this.screenshot.getTitle() + "\' " + I18n.format("selectWorld.deleteWarning", new Object[0]), I18n.format("selectWorld.deleteButton", new Object[0]), I18n.format("gui.cancel", new Object[0]), 0));
	}
	
	public void renameScreenshot()
	{
		this.client.displayGuiScreen(new GuiScreenshotRename(this.selection, this.screenshot.getFile()));
	}
	
	@Override
	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
	{
		this.containingListSel.selectScreenshot(slotIndex);
		return true;
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
	{
	}
	
	@Override
	public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_)
	{
	}
}

