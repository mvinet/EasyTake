package fr.mvinet.easyTake.screen;

import java.awt.Graphics2D;
import java.awt.image.*;

import net.minecraft.client.Minecraft;

/**
 * Frame
 * 
 * @author mvinet
 */
public class Frame extends BufferedImage {

	/**
	 * Frame id
	 */
	private int frameID;

	/**
	 * Array of pixel
	 */
	private int[] pixelArray;

	/**
	 * Constructor
	 * @param image the bufferedImage
	 * @param frameID the frame Id
	 */
	public Frame(BufferedImage image, int frameID) {
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.frameID = frameID;

		if (FrameWriter.getOutput() == null) {
			FrameWriter.setOutput(Minecraft.getMinecraft().mcDataDir + "//EasyTake//Screen");
		}
		loadFrame(image);
	}

	/**
	 *  Sets the frame's size.
	 * @param width the width
	 * @param height the height
	 * @return the {@link Frame}
	 */
	public Frame setSize(int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(getBufferedImage(), 0, 0, width, height, null);
		g.dispose();
		return new Frame(resizedImage, getFrameID());
	}

	@Override
	public WritableRaster getRaster() {
		return getData().createCompatibleWritableRaster();
	}

	/**
	 * Sets the frame's pixel data.
	 * @param intArray the array
	 */
	public void setFrame(int[] intArray) {
		this.setRGB(0, 0, getWidth(), getHeight(), intArray, 0, getWidth());
	}

	/**
	 * Loads the frame from the image.
	 * @param image the {@link BufferedImage}
	 */
	public void loadFrame(BufferedImage image) {
		setFrame(((DataBufferInt) image.getRaster().getDataBuffer()).getData());
	}

	/**
	 * Returns the pixel data of the frame.
	 * @return the pixel Array
	 */
	public int[] getPixelArray() {
		return pixelArray;
	}

	/**
	 * @param id the frameId
	 */
	public void setFrameID(int id) {
		frameID = id;
	}

	/**
	 * @return the frameID
	 */
	public int getFrameID() {
		return frameID;
	}

	/**
	 * Returns the image during initialization, used with filters.
	 * @return the {@link BufferedImage}
	 */
	public BufferedImage getBufferedImage() {
		return this.getSubimage(0, 0, getWidth(), getHeight());
	}

	/**
	 * Returns a new frame from the image.
	 * @param image the {@link BufferedImage}
	 * @return the {@link Frame}
	 */
	public static Frame getFrameFromBufferedImage(BufferedImage image) {
		return new Frame(image, FrameHandler.getFrameList().size() + 1);
	}
}
