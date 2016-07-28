import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import fr.mvinet.easyTake.Utils;

public class ColorTest
{
	@Test
	public void testColor()
	{
		assertTrue("test Color", Utils.getColor("red") == Color.RED);
	}

}
