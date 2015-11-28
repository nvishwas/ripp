package rip;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics2D;
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
	    
	    File InputFile = new File("D:\\RIP\\project\\images\\Test.jpeg");
	    System.out.println("File path : " + InputFile.getAbsolutePath());
	    BufferedImage biImage = ImageIO.read(InputFile);
	    Color c  = new Color(biImage.getRGB(313, 519));
	    
	    System.out.println("Image read: Number of pixels "+ biImage.getHeight()+ " " + biImage.getTileWidth() + "Image type is: " + biImage.getType()+"Pixel 313, 519: R" +c.getRed() + " G " + c.getGreen() + " B " + c.getBlue() );
	    
	    ImageIcon  icon = new ImageIcon(biImage);
        JLabel jLabel = new JLabel("InputImage");
        jLabel.setIcon(icon);
       
        JFrame editorFrame = new JFrame();
		editorFrame.getContentPane().add(jLabel);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.setVisible(true);
        
        BufferedImage clone = new BufferedImage(biImage.getWidth(),
                biImage.getHeight(), biImage.getType());
        Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(biImage, 0, 0, null);
        g2d.dispose();
        
        int width = biImage.getWidth();
        int height = biImage.getHeight();
        int r, g, b;
        double F, P;
        F=0.9;
        P=0.2;
        Color nc;
        
        for (int i=0; i < width ; i++) {
        	for (int j=0; j <  height; j++) {
        		c  = new Color(biImage.getRGB(i, j));
        		r = (int)(c.getRed() * 0.299);
                        g = (int)(c.getGreen() * 0.587);
                	b = (int)(c.getBlue() *0.114);       		
        		
        		if (0 <= c.getRed() && c.getRed() < 32) {   		
        			r = 0;
        			g = 0;
        			b = 0;
        		} else if (32 <= c.getRed() && c.getRed() < 64) {
        			r = 255;
        			g = 0;
        			b = 0;        			
        		} else if (64 <= c.getRed() && c.getRed() < 96) {
        			r = 255;
        			g = 0;
        			b = 255;        			
        		} else if (96 <= c.getRed() && c.getRed() < 128) {
        			r = 0;
        			g = 0;
        			b = 255;        			
        		} else if (128 <= c.getRed() && c.getRed() < 160) {
        			r = 0;
        			g = 255;
        			b = 255;        			 			
        		} else if (160 <= c.getRed() && c.getRed() < 192) {
        			r = 0;
        			g = 255;
        			b = 0;      
        		} else if (192 <= c.getRed() && c.getRed() < 224) {
        			r = 255;
        			g = 255;
        			b = 0;        			
        		} else {
        			r = 255;
        			g = 255;
        			b = 255;        			
        		}
			nc = new Color(r,g,b);
        		clone.setRGB(i, j, nc.getRGB());
        	}
        }
        
	    ImageIcon  oicon = new ImageIcon(clone);
        JLabel ojLabel = new JLabel("Output Image");
        ojLabel.setIcon(oicon);
       
        JFrame oeditorFrame =new JFrame();
        oeditorFrame.getContentPane().add(ojLabel);
        
        oeditorFrame.pack();
        oeditorFrame.setLocationRelativeTo(null);
        oeditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oeditorFrame.setVisible(true);      
	}
}
