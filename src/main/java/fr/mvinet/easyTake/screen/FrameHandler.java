package fr.mvinet.easyTake.screen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Frame Handler
 * 
 * @author mvinet
 */
public class FrameHandler {

	/**
	 * List of the {@link Frame}
	 * @return List of the {@link Frame}
	 */
	public static List<Frame> getFrameList() {
		return new ArrayList<Frame>();
	}

	/**
	 * Adds a frame to the main frame list.
	 * @param frame list of the {@link Frame}
	 */
	public static void addFrame(Frame frame) {
		getFrameList().add(frame);
	}

	/**
	 * Returns the file the frame is stored in.
	 * @param frame the {@link Frame}
	 * @return the {@link File}
	 */
	public static File getFileFromFrame(Frame frame) {
		File returnFile = null;

		try {
			for (File file : FrameWriter.getOutputFiles()) 	{
				if (ImageIO.read(file) == frame.getBufferedImage()) {
					returnFile = file;
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return returnFile;
	}
}
