import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class LoadingScreen extends JFrame{
	/*
	 * This is the Frame that appears when the user
	 * made his choice and make him wait with an 
	 * animation of Mario jumping on the box play
	 * and a beginning music.
	 * This is kind of a loading before the grid 
	 * appears.
	 */
	public LoadingScreen(){
		
		/*
		 * The animation is basicly a .gif image that is put
		 * inside a jLabel which is put on the JFrame.
		 */
		
		ImageIcon gifImage = new ImageIcon("AnimStart.gif");
		JLabel label = new JLabel(gifImage);
		
		this.setSize(320,300);
		
		
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Loading...");	
		this.setAlwaysOnTop(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
		this.setVisible(true);
		// Here we declare and use a thread that we defined just below
		 Thread t = new Thread(new PlayAnimation());
	     t.start();
	     
	
	}
	
	/*
	 * This is a Thread class that implements the Runnable interface. 
	 * We use it in order to launch a sound at the same time that the animation is played.
	 * Inside we use the class Audio that we've defined.
	 */
	class PlayAnimation implements Runnable{
	    public void run() {
	    	Audio sound = new Audio("begin.wav");   
	    	sound.run();
	    } 
	}
	
}
