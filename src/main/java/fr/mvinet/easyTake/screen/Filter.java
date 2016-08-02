package fr.mvinet.easyTake.screen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class Filter
{

	// Overlays a filter with the set color and transparency.
	public static void overlayFrameFromColor(Frame frame, Color color, float transparency)
	{
		BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		BufferedImage image2 = frame.getBufferedImage();
		g.drawImage(image2, 0, 0, null);
		g.setColor(color);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		frame.loadFrame(image);
	}
	
	public static void overlayFrameFromPicture(File output, String imageOverlay)
	{	
		try
		{
			File file = new File("overlay.png");
			URL url = new URL("http://dev.mvinet.fr/EasyTake/img/" + imageOverlay);
			
			FileUtils.copyURLToFile(url, file);
			
			BufferedImage screen = ImageIO.read(output);
			//BufferedImage overlay = ImageIO.read(url);
			BufferedImage overlay = ImageIO.read(file);
			
			int w = screen.getWidth();
			int h = screen.getHeight();

			BufferedImage resizeImage = new BufferedImage((int)(w*0.35f), (int)(h*0.2f), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = resizeImage.createGraphics();
			g2d.drawImage(overlay, 0, 0, (int)(w*0.35f), (int)(h*0.2f), null);
			g2d.dispose();
			g2d.setComposite(AlphaComposite.Src);
			
			BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
			Graphics g = result.getGraphics();
			g.drawImage(screen, 0, 0, null);
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();

			ImageIO.write(result, "jpg", output);
			//frame.loadFrame(overlay);
			
			file.delete();
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// Sets the frame to a gray/greyscale version.
	public static void setToGrayscale(Frame frame)
	{
		BufferedImage bufferedImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(frame.getBufferedImage(), 0, 0, null);
		g.dispose();
		frame.setFrame(bufferedImage.getRGB(0, 0, frame.getWidth(), frame.getHeight(), null, 0, frame.getWidth()));
	}
	
	// Below 1.0F and above 0.0F will darken, Above 1.0F will brighten, very
	// sensitive.
	public static void setBrightness(Frame frame, float brightness)
	{
		BufferedImage bufferedImage = frame.getBufferedImage();
		new RescaleOp(brightness, 0, null).filter(bufferedImage, bufferedImage);
		frame.setFrame(bufferedImage.getRGB(0, 0, frame.getWidth(), frame.getHeight(), null, 0, frame.getWidth()));
	}

}
