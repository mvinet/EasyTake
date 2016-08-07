package fr.mvinet.easyTake.gui;
import fr.mvinet.easyTake.screen.Screenshot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListWorldSelection;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiListScreenshotSelectionEntry implements IGuiListEntry
{
    private final Minecraft client;
    private final GuiScreenshotSelection selection;
    private final Screenshot screenshot;
    
    public GuiListScreenshotSelectionEntry(GuiListScreenshotSelection listScreenSelIn, Screenshot text)
    {
    	//GuiListWorldSelectionEntry
		this.client = Minecraft.getMinecraft();
		this.selection = listScreenSelIn.getGuiScreenshotSelection();
		this.screenshot = text;
    	
    }

	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
	{
		this.client.fontRendererObj.drawString(screenshot.getTitle(), x + 32 + 3, y + 1, 16777215);
        this.client.fontRendererObj.drawString(screenshot.getDate().toString(), x + 32 + 3, y + this.client.fontRendererObj.FONT_HEIGHT + 3, 8421504);
        this.client.fontRendererObj.drawString(screenshot.getTitle(), x + 32 + 3, y + (this.client.fontRendererObj.FONT_HEIGHT * 2) + 3, 8421504);
	}

	@Override
	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
	{
		return false;
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

