package CoT.bfck;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Colors {
	
	private CommandFactory cf = new CommandFactory();

	public Color returnColor(String c) throws Exception{
        if (cf.getCommand(c) == null)  return new Color(0, 0, 0);
		if(cf.getCommand(c).getRGBColor().equals(null))
			return new Color(0, 0, 0);
		else {
			return cf.getCommand(c).getRGBColor();
		}
	}

	public String getHexa(BufferedImage img, int x, int y){
	  	Color pixelcolor = new Color(img.getRGB(x, y));
	  	String hexa = "";

	  	if(pixelcolor.getRed()==0)
	  		hexa += "0" + Integer.toHexString(pixelcolor.getRed());
	  	else
	  		hexa += Integer.toHexString(pixelcolor.getRed());

	  	if(pixelcolor.getGreen()==0)
	  		hexa += "0" + Integer.toHexString(pixelcolor.getGreen());
	  	else
	  		hexa += Integer.toHexString(pixelcolor.getGreen());

	  	if(pixelcolor.getBlue()==0)
	  		hexa += "0" + Integer.toHexString(pixelcolor.getBlue());
	  	else
	  		hexa += Integer.toHexString(pixelcolor.getBlue());

	    return hexa;
		}
}
