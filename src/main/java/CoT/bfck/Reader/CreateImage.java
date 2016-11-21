package CoT.bfck.Reader;

import CoT.bfck.Colors;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImage {
	
	public void create_Image(String shortened) throws Exception{
		int i = 1;
		int j;
        int n = shortened.length();
        System.out.println(shortened);
		for(j = 1; i < n; j++){ i = j * j; }
		String[] tableInstru = shortened.split("");
		BufferedImage img  = new BufferedImage((j-1)*3, (j-1)*3,BufferedImage.TYPE_INT_RGB);
		int p = 0;
		Color c;
		try{
            System.out.println("Boucle pas commence");
			for(int k = 0; k < img.getWidth(); k += 3){
                System.out.println("Boucle 1");
				for(int l = 0; l < img.getHeight(); l += 3){
                    System.out.println("Boucle 2");
						if(p >= shortened.length()) {
							c = new Color(0,0,0);
						}else {
							c = new Colors().returnColor(tableInstru[p]);
					}
						for(int v=k;v<k+3;v++){
                            System.out.println("Boucle 3");
							for(int w=l;w<l+3;w++){
								img.setRGB(w, v, c.getRGB());
							}
						}
						p++;
				}
			}
			System.out.println("Boucle fini");
			//Si problème, remplacer avec translation.bmp (Linux) ou bien le bon chemin (IDE Windows)
			ImageIO.write(img, "bmp", new File("D:/translation.bmp"));
		}
		catch(IOException e){
            System.out.println("Classe CreateImage");
			System.out.println(e);
		}
	}

}
