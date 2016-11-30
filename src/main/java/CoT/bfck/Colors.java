package CoT.bfck;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Classe permettant de faire le lien entre des couleurs en hexadécimal et des couleurs RGB
 */
public class Colors {

	private CommandFactory cf = new CommandFactory();

	/**
	 * Renvoie la couleur correspondant à un String (contenant le code hexadécimal d'une couleur).
	 * @param hexa valeur hexadécimale d'une couleur
	 * @return Couleur crée en fonction du code RGB
	 * @throws Exception
	 */
	public Color returnColor(String hexa) throws Exception{
        if (cf.getCommand(hexa) == null)  return new Color(0, 0, 0);
		if(cf.getCommand(hexa).getRGBColor().equals(null))
			return new Color(0, 0, 0);
		else {
			return cf.getCommand(hexa).getRGBColor();
		}
	}

    /**
     * Renvoie le code hexa d'un pixel d'une image.
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
