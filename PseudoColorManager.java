//package rip;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PseudoColorManager {

	/* Useful Variables */
	File inputFile;
	BufferedImage biImage;
	BufferedImage opImage1;
	BufferedImage opImage2;
	BufferedImage opImage3;
	BufferedImage opImage4; 
	int width;
    int height;
	int colorTypes = 4;
    ///*
	double freq = 4;
    double phase = 2;
    //*/
   /*
    double freq = 2* Math.PI;
    double phase = Math.PI;
    */
	public PseudoColorManager(String pathname) {
		
		/* Read the input file */
		inputFile = new File(pathname);
		
		try {
			biImage = ImageIO.read(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Get required parameters from image */
		width = biImage.getWidth();
		height = biImage.getHeight();
		
		/* Create a clone as output image */
		cloneInput();
		
		/* Print the read file path */
		System.out.println("File path : " + inputFile.getAbsolutePath());
	}
	
	/* Create a frame and display the requested image */
	public void showImage(String image, String suffix) {
	    
		ImageIcon  icon;
        JLabel jLabel;
        
        if (image.equals("input")) {
        	jLabel = new JLabel(image);
        	icon = new ImageIcon(biImage);	
        } else if (image.equals("0")) {
        	jLabel = new JLabel("output" + image + suffix);
        	icon = new ImageIcon(opImage1);
        } else if (image.equals("1")) {
        	jLabel = new JLabel("output" + image+ suffix);
        	icon = new ImageIcon(opImage2);
        } else if (image.equals("2")) {
        	jLabel = new JLabel("output" + image+ suffix);
        	icon = new ImageIcon(opImage3);
        } else if (image.equals("3")) {
        	jLabel = new JLabel("output" + image+ suffix);
        	icon = new ImageIcon(opImage4);
        } else {
        	jLabel = new JLabel("Invalid option");
        	icon = new ImageIcon(biImage);
        }
                
        jLabel.setIcon(icon);
        JFrame editorFrame = new JFrame();
		editorFrame.getContentPane().add(jLabel);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.setVisible(true);	
	}
	
	/* Returns the number of coloring scheme supported */
	public int getColorTypes() {
		return colorTypes;
	}
	
	public double getFrequency ()
	{
		return freq;
	}
	
	public double getPhase ()
	{
		return phase;
	}
	public void addColor(int i) {
		switch(i){
		case 0:
			addColor1();
			break;
		case 1:
			addColor2();
			break;
		case 2:
			addColor3();
			break;
		case 3:
			addColor4();
			break;
		default:
			System.out.println(" Invalid color option ");
		}
	}
	
	/* Add a different color for each of different range of gray levels */
	private void addColor1() {       
        int r, g, b;
        Color nc;
        Color c;
        
        for (int i=0; i < width ; i++) {
        	for (int j=0; j <  height; j++) {
        		
        		c  = new Color(biImage.getRGB(i, j));
        		
        		r = (int)(c.getRed() * 0.299);
                g = (int)(c.getGreen() * 0.587);
                b = (int)(c.getBlue() *0.114); 
                
        		//System.out.println(c.getBlue() +" " + c.getGreen() + " " + c.getRed());
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
        		
        		opImage1.setRGB(i, j, nc.getRGB());
        	}
        }
		
	}
	
	/* Assign RGB a fraction of the values of grey level*/
	private void addColor2() {
        int r, g, b;
        Color nc, c;
        
        for (int i=0; i < width ; i++) {
        	for (int j=0; j <  height; j++) {
        		c  = new Color(biImage.getRGB(i, j));
        		r = (int)(c.getRed() * 0.299);
                g = (int)(c.getGreen() * 0.587);
                b = (int)(c.getBlue() *0.114);       		
        		nc = new Color(r,g,b);
        		
        		opImage2.setRGB(i, j, nc.getRGB());
        	}
        }
	
	}
	
	/* Assign RGB a value as a function of sinusoid */
	private void addColor3() {
        int r, g, b;
        Color nc, c;
    	@SuppressWarnings("unused")
		int red;
        for (int i=0; i < width ; i++) {
        	for (int j=0; j <  height; j++) {
        		c  = new Color(biImage.getRGB(i, j));
        		r = (int)(255* Math.abs(Math.sin(c.getRed() * freq / 255)));
                g = (int)(255* Math.abs(Math.sin(c.getGreen() * freq / 255 + phase/3)));
                b = (int)(255* Math.abs(Math.sin(c.getBlue() * freq  / 255  + phase/2)));       		
        		//r = (int)(255* Math.abs(Math.sin(c.getRed() * 2 * Math.PI / 255)));
                //g = (int)(255* Math.abs(Math.sin(c.getGreen() * 2 * Math.PI / 255 + Math.PI/3)));
                //b = (int)(255* Math.abs(Math.sin(c.getBlue() * 2 * Math.PI / 255 + Math.PI/2))); 
        		nc = new Color(r,g,b);
        		
        		nc = new Color(r,g,b);
        		red = c.getRed();
        		opImage3.setRGB(i, j, nc.getRGB());
        	}
        }
	
	}	

	private void addColor4() {
        int r, g, b;
        Color nc, c;
    	@SuppressWarnings("unused")
		int red;
        for (int i=0; i < width ; i++) {
        	for (int j=0; j <  height; j++) {
        		c  = new Color(biImage.getRGB(i, j));
        	//	r = (int)(255* Math.abs(Math.cos(c.getRed() * 2 * Math.PI * freq )));
            //    g = (int)(255* Math.abs(Math.cos(c.getGreen() * 2 * Math.PI * freq + phase/3)));
            //    b = (int)(255* Math.abs(Math.cos(c.getBlue() * 2 * Math.PI * freq + phase/2)));       		
        	
        		r = (int)(255* Math.abs(Math.cos(c.getRed() * 2 * Math.PI / 255)));
                g = (int)(255* Math.abs(Math.cos(c.getGreen() * 2 * Math.PI / 255 + Math.PI/3)));
                b = (int)(255* Math.abs(Math.cos(c.getBlue() * 2 * Math.PI / 255 + Math.PI/2))); 	
        	
        		red = c.getRed();
        		nc = new Color(r,g,b);        		
        		opImage4.setRGB(i, j, nc.getRGB());
        	}
        }
	
	}	
	
	/* Copy the input image in to output of different coloring schemes */
	private void cloneInput() {
        opImage1 = new BufferedImage(biImage.getWidth(),biImage.getHeight(), biImage.getType());
        Graphics2D g2d = opImage1.createGraphics();
        g2d.drawImage(biImage, 0, 0, null);
        
        opImage2 = new BufferedImage(biImage.getWidth(),biImage.getHeight(), biImage.getType());
        g2d = opImage2.createGraphics();
        g2d.drawImage(biImage, 0, 0, null);
        
        opImage3 = new BufferedImage(biImage.getWidth(),biImage.getHeight(), biImage.getType());
        g2d = opImage3.createGraphics();
        g2d.drawImage(biImage, 0, 0, null);

        opImage4 = new BufferedImage(biImage.getWidth(),biImage.getHeight(), biImage.getType());
        g2d = opImage4.createGraphics();
        g2d.drawImage(biImage, 0, 0, null);

        g2d.dispose();
	}
}
