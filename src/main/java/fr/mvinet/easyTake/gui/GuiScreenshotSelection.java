package fr.mvinet.easyTake.gui;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Nullable;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumAction;


/**
 * Enumeration of the button
 * 
 * @author mvinet
 */
enum IdButton {
	
	/**
	 * Back button
	 */
	BACK(0), 
	
	/**
	 * Upload button
	 */
	UPLOAD(1), 
	
	/**
	 * Edit button
	 */
	EDIT(2), 
	
	/**
	 * Rename button
	 */
	RENAME(3), 
	
	/**
	 * Delete button
	 */
	DELETE(4);

	/**
	 * Identifier
	 */
	private final int id;

	/**
	 * Constructor
	 * @param id the id
	 */
	IdButton(int id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return this.id;
	}
}

/**
 * Screenshot Selection Interface
 * 
 * @author mvinet
 */
public class GuiScreenshotSelection extends GuiScreen {

	/**
	 * Previous screen
	 */
	private GuiScreen lastScreen;
	
	/**
	 * Selection list of the screenshot
	 */
	private GuiListScreenshotSelection selectionList;


	/**
	 * Constructor
	 * @param screen
	 */
	public GuiScreenshotSelection(GuiScreen screen) {
		// GuiWorldSelection
		this.lastScreen = screen;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(0);
		this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(this.fontRendererObj, I18n.format("easytake.gui.listScreenshot.title", new Object[0]),
				this.width / 2, 20, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		this.selectionList = new GuiListScreenshotSelection(this, this.mc, this.width, this.height, 32,
				this.height - 64, 36);

		this.buttonList.add(new GuiButton(IdButton.BACK.getValue(), this.width / 2 + 4, this.height - 28, 150, 20,
				I18n.format("menu.returnToGame", new Object[0])));
		this.buttonList.add(new GuiButton(IdButton.UPLOAD.getValue(), this.width / 2 + 56, this.height - 52, 98, 20,
				I18n.format("easytake.upload", new Object[0])));
		this.buttonList.add(new GuiButton(IdButton.EDIT.getValue(), this.width / 2 - 154, this.height - 52, 98, 20,
				I18n.format("selectWorld.edit", new Object[0])));
		this.buttonList.add(new GuiButton(IdButton.RENAME.getValue(), this.width / 2 - 49, this.height - 52, 98, 20,
				I18n.format("easytake.rename", new Object[0])));
		this.buttonList.add(new GuiButton(IdButton.DELETE.getValue(), this.width / 2 - 154, this.height - 28, 150, 20,
				I18n.format("selectWorld.delete", new Object[0])));

		this.buttonList.get(IdButton.UPLOAD.getValue()).enabled = false;
		this.buttonList.get(IdButton.EDIT.getValue()).enabled = false;
		this.buttonList.get(IdButton.RENAME.getValue()).enabled = false;
		this.buttonList.get(IdButton.DELETE.getValue()).enabled = false;
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed
	 * for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == IdButton.BACK.getValue()) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else {
			GuiListScreenshotSelectionEntry glsse = this.selectionList.getSelectedScreenshot();
			if (glsse != null) {
				if (button.id == IdButton.DELETE.getValue()) {
					glsse.deleteScreenshot();
				} else if (button.id == IdButton.RENAME.getValue()) {
					glsse.renameScreenshot();
				}
			}
		}
	}

	/**
	 * Change enable of button list when entry is not null
	 * @param entry the selected screenshot selection
	 */
	public void selectScreenshot(GuiListScreenshotSelectionEntry entry) {
		boolean flag = entry != null;
		this.buttonList.get(IdButton.UPLOAD.getValue()).enabled = flag;
		this.buttonList.get(IdButton.EDIT.getValue()).enabled = flag;
		this.buttonList.get(IdButton.RENAME.getValue()).enabled = flag;
		this.buttonList.get(IdButton.DELETE.getValue()).enabled = flag;
	}

	/**
	 * Handles mouse input.
	 */
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.selectionList.handleMouseInput();
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Called when a mouse button is released.
	 */
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		this.selectionList.mouseReleased(mouseX, mouseY, state);
	}
}