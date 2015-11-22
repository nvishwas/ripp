import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PseudoColorManager {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    String readFormats[] = ImageIO.getReaderMIMETypes();
	    String writeFormats[] = ImageIO.getWriterMIMETypes();
	    System.out.println("Readers: " + 
	        Arrays.asList(readFormats));
	    System.out.println("Writers: " + 
	        Arrays.asList(writeFormats));
	    
	    File InputFile = new File("/Users/lprasanna/Documents/XRays/Test.jpeg");
	    System.out.println("File path : " + InputFile.getAbsolutePath());
	    BufferedImage biImage = ImageIO.read(InputFile);
	    int w = 1037;
		int h =627;
		int[] rgbArray = null;
		int offset = 0;
		int scansize = 100;
		rgbArray = biImage.getRGB(0, 0, w, h, rgbArray, offset, scansize);

		
		int iloopend = rgbArray.length;
		
		for(int i=0; i <= iloopend -1; i++)
		{
			System.out.print( rgbArray[i])	;
		}
		

	}

}
