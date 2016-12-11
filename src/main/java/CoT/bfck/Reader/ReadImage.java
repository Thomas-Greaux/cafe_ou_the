package CoT.bfck.Reader;

import CoT.bfck.Factory.Colors;
import CoT.bfck.Command.Command;
import CoT.bfck.Exception.NotACommandException;

import java.awt.image.BufferedImage;
import java.io.*;
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
	 * @param filename the file in which we want to write
	 * @return all the commands stored in.bf the file
	 * @throws IOException
	 * @throws NotACommandException
	 */
	public ArrayList<Command> readImage(String filename) throws IOException, NotACommandException {
		ArrayList<Command> list = new ArrayList<Command>();
		try {
			InputStream ips = new FileInputStream(new File(filename));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			BufferedImage img = ImageIO.read(new File(filename));
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