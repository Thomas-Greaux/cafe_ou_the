package CoT.bfck.Reader;

import CoT.bfck.Colors;
import CoT.bfck.Exception.NotACommandException;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class used to create an image
 * @author cafe_ou_the
 */
public class CreateImage {

	/**
	 * Method used to create an image, with a String containing all the commands in the shortened syntax
	 * @param shortened the shortened version of the commands
	 * @throws FileNotFoundException
	 * @throws NotACommandException
	 */
	public void create_Image(String shortened) throws FileNotFoundException, NotACommandException{
		int i = 1;
		int j;
        int n = shortened.length();
		for(j = 1; i < n; j++){ i = j * j; }
		String[] tableInstru = shortened.split("");
		BufferedImage img  = new BufferedImage((j-1)*3, (j-1)*3,BufferedImage.TYPE_INT_RGB);
		int p = 0;
		Color c;
		try{
			for(int k = 0; k < img.getWidth(); k += 3){
				for(int l = 0; l < img.getHeight(); l += 3){
						if(p >= shortened.length()) {
							c = new Color(0,0,0);
						}else {
							c = new Colors().returnColor(tableInstru[p]);
					}
						for(int v=k;v<k+3;v++){
							for(int w=l;w<l+3;w++){
								img.setRGB(w, v, c.getRGB());
							}
						}
						p++;
				}
			}
			//Si problème, remplacer avec translation.bmp (Linux) ou bien le bon chemin (IDE Windows)
			ImageIO.write(img, "bmp", new File("Présentation/Images/image.bmp"));
		}
		catch(IOException e){
            System.out.println("Classe CreateImage");
			System.out.println(e);
		}
	}

}
