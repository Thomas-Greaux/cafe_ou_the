package CoT.bfck.Factory;

import CoT.bfck.Exception.NotACommandException;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class used to make a link between colors (RGB) and the hexa code of the same color.
 * @author cafe_ou_the
 */
public class Colors {

	private CommandFactory cf = new CommandFactory();

	/**
	 * Return the color corresponding to a String containing the hexadecimal code of a color.
	 * @param hexa hexadecimal value of a color
	 * @return Color created in function of the RGB of the color
	 * @throws NotACommandException
	 */
	public Color returnColor(String hexa) throws NotACommandException{
        if (cf.getCommand(hexa) == null)  return new Color(0, 0, 0);
		if(cf.getCommand(hexa).getRGBColor().equals(null))
			return new Color(0, 0, 0);
		else {
			return cf.getCommand(hexa).getRGBColor();
		}
	}

    /**
     * Return the hexa code of a pixel of a picture
     * @param img BufferedImage
     * @param x Pixel visé sur l'axe x
     * @param y Pixel visé sur l'axe y
     * @return code hexadécimal correspondant.
     */
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
