package fr.mvinet.easyTake.gui;

import java.io.IOException;
import java.util.ArrayList;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiScreenshotSelection extends GuiScreen
{
	private GuiScreen prevScreen;
	private GuiListScreenshotSelection selectionList;

	public GuiScreenshotSelection(GuiScreen screen)
	{
		// GuiWorldSelection
		this.prevScreen = screen;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawBackground(0);
		this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(this.fontRendererObj, I18n.format("easytake.gui.listScreenshot.title", new Object[0]),
				this.width / 2, 20, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui()
	{
		this.selectionList = new GuiListScreenshotSelection(this, this.mc, this.width, this.height, 32, this.height - 64, 36);

		this.buttonList.add(new GuiButton(1, this.width / 2 + 56, this.height - 52, 98, 20, I18n.format("easytake.upload", new Object[0])));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 154, this.height - 52, 98, 20, I18n.format("selectWorld.edit", new Object[0])));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 49, this.height - 52, 98, 20, I18n.format("easytake.rename", new Object[0])));
		this.buttonList.add(new GuiButton(4, this.width / 2 - 154, this.height - 28, 150, 20, I18n.format("selectWorld.delete", new Object[0])));
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4, this.height - 28, 150, 20, I18n.format("menu.returnToGame", new Object[0])));
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed
	 * for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0)
		{                
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		}
	}

	/**
	 * Handles mouse input.
	 */
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		this.selectionList.handleMouseInput();
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Called when a mouse button is released.
	 */
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
		this.selectionList.mouseReleased(mouseX, mouseY, state);
	}
}