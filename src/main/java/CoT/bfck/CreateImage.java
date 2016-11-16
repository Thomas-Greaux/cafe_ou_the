package CoT.bfck;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImage {
	
	public void create_Image(String shortened) throws Exception{
		int i = 1, j = 1;
		while(i < shortened.length()){
			i = j * j;
			j++;
		}
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
			ImageIO.write(img, "bmp", new File("C://Users/Admin123/Desktop/Polytech/SI3/cafe_ou_the_dev/Brainfuck/src/bfck/translation.bmp"));
		}
		catch(IOException e){
			System.out.println(e);
		}
	}

}
