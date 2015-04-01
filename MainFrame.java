import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * This is the frame that contain the game field and 
 * all related informations, such as time and number
 * of remaining flags. 
 */

public class MainFrame extends JFrame {
		int x;
		int y;
		Field mainField;
		TimeCounter timeCount;
		ImagePanel banner;
		Segment segTime;
		Segment segFlag;
		boolean firstClick = false;
		
	public MainFrame(int height, int width, int mines){
		
		x=height;
		y=width;
		Audio sound = new Audio("begin.wav");
		this.setSize(y*25+6,(x*25)+125);
		this.setLocationRelativeTo(null); //Put the frame on the center of the screen.
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Ends the process when the user click on the red cross
	    this.setResizable(false); 
	    this.setAlwaysOnTop(false); 
	    
	    
	    Field contenant = new Field(x,y,mines, this);
	    mainField = contenant;
	    
	    this.add(contenant);
	    
	    banner = new ImagePanel("Superbanier.png");
	    banner.setLayout(new FlowLayout());
	    
	    segTime = new Segment();
	    segFlag = new Segment();
	    segFlag.setValeur(mainField.nbrmines);
	    
	    JLabel time = new JLabel("Time");
	    JLabel score = new JLabel("Flags");
	    
	    timeCount = new TimeCounter(segTime);
	    
	    segTime.setAlignmentY(LEFT_ALIGNMENT);
	    segFlag.setAlignmentX(RIGHT_ALIGNMENT);
	    
	    banner.setSize(new Dimension(500,96));
	    banner.add(time);
	    banner.add(segTime);
	    banner.add(score);
	    banner.add(segFlag);
	    this.add(banner);
	    
	    contenant.setLocation(0, 96);
	   
	    this.setVisible(true);
	    
	}
}
