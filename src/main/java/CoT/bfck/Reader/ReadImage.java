package CoT.bfck.Reader;

import CoT.bfck.Colors;
import CoT.bfck.Command.Command;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Reader used when the controller find out a .BMP file.
 * Call the reader with the hexa code of each picture zone.
 * @author cafe_ou_the
 */
public class ReadImage extends Reader {

	/**
	 * Read the instruction in.bf the picture in.bf parameter. To do this, read the
	 * color (RGB) of every first pixel of every zone.
	 * 
	 * @param file
	 * @return all the commands stored in.bf the file
	 * 
	 */
	public ArrayList<Command> readImage(String file) throws Exception {
		ArrayList<Command> list = new ArrayList<Command>();
		try {
			InputStream ips = new FileInputStream(new File(file));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			BufferedImage img = ImageIO.read(new File(file));
			int height = img.getHeight();
			int width = img.getWidth();
			for (int i = 0; i < width; i += 3) {
				for (int j = 0; j < height; j += 3) {
					list.addAll(read(new Colors().getHexa(img, j, i)));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
			System.exit(1);
		}
		return list;
	}

}