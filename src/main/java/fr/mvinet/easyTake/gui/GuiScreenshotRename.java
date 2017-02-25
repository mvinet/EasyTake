package fr.mvinet.easyTake.gui;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

import fr.mvinet.easyTake.Constant;
import fr.mvinet.easyTake.screen.Screenshot;

/**
 * Screenshot Rename Interface
 * 
 * @author mvinet
 */
@SideOnly(Side.CLIENT)
public class GuiScreenshotRename extends GuiScreen {

	/**
	 * Previous screen
	 */
	private final GuiScreen lastScreen;
	
	/**
	 * TextField for the name
	 */
    private GuiTextField nameEdit;
    
    /**
     * The file
     */
    private final File file;

    /**
     * Constructor
     * @param lastScreen the previous screen
     * @param file the file
     */
    public GuiScreenshotRename(GuiScreen lastScreen, File file) {
        this.lastScreen = lastScreen;
        this.file = file;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()  {
        this.nameEdit.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()  {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 48 + 12, I18n.format("selectWorld.edit.openFolder", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.format("easytake.edit.save", new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));

        String s = file.getName().substring(0, file.getName().length() - 4);
        this.nameEdit = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.nameEdit.setFocused(true);
        this.nameEdit.setText(s);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()  {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException  {
        if (button.enabled)  {
            if (button.id == 1) {
                this.mc.displayGuiScreen(this.lastScreen);
            }  else if (button.id == 0)  {
            	File newFile = new File(Constant.PATHSCREENSHOT + File.separator + this.nameEdit.getText() + ".jpg");
            	this.file.renameTo(newFile);
                this.mc.displayGuiScreen(this.lastScreen);
            }  else if (button.id == 4)  {
                ISaveFormat isaveformat2 = this.mc.getSaveLoader();
                OpenGlHelper.openFile(this.file.getParentFile());
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.nameEdit.textboxKeyTyped(typedChar, keyCode);
        ((GuiButton)this.buttonList.get(2)).enabled = !this.nameEdit.getText().trim().isEmpty();

        if (keyCode == 28 || keyCode == 156)  {
            this.actionPerformed((GuiButton)this.buttonList.get(2));
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.nameEdit.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)  {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("easytake.edit.title", new Object[0]), this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("easytake.edit.enterName", new Object[0]), this.width / 2 - 100, 47, 10526880);
        this.nameEdit.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}