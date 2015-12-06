//package rip;

public class PseudoColorTester {
    /** 
     * A test class to test the PseudoColorManager class 
     * @param args
     */
	public static void main(String args[]) {
		int iter, i;
		PseudoColorManager colorImage  = new PseudoColorManager("/Users/lprasanna/Documents/XRays/Test.jpeg");
		
		/* Read number of coloring schemes supported */
		//iter = colorImage.getColorTypes();
		iter = 3;
		int loopstart = 2; 
		double freq=0;
		double phase=0;
	
		freq = colorImage.getFrequency();
		phase =colorImage.getPhase();
		
		/* Loop through different coloring schemes and display them*/
		for (i = loopstart; i < iter; i++) {
			colorImage.addColor(i);
			colorImage.showImage(""+i,"-"+freq+"-"+phase);
		}				
	}
}
