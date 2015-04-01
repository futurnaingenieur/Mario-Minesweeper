import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private String filename;
	private String filename2;
	boolean change;
	
	/*
	 * A very useful class that
	 * is a wallpaper for the JFrame.
	 * With this class we can easily
	 * put a banner at the top of our 
	 * minesweeper field or a mario.png
	 *  on the setting in the selectlevel frame
	 */
	
	//That's the constructor with 2 images in order to superimpose the two images.
	public ImagePanel(String filename, String filename2){
		this.filename=filename;
		this.filename2 = filename2;
		change =false;
		
		
	}
	
	
	public void ChangeSecondImg(String filename2){
		this.filename2 = filename2;
		change = true;
	}
	
	public ImagePanel(String filename){
		this.filename=filename;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		try {
			Image img=ImageIO.read(new File(filename));
			g.drawImage(img,0,0,this);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		if(filename2!=null){
		
		try {
			Image img2=ImageIO.read(new File(filename2));
			g.drawImage(img2,104,222,this);
		    } 
		catch (IOException e) {
			    e.printStackTrace();
		      }
		}
		if(change==true){
			try {
				Image img3=ImageIO.read(new File(filename2));
				g.drawImage(img3,104,150,this);
			    } 
			catch (IOException e) {
				    e.printStackTrace();
			      }
			repaint();
		}
	}
}
