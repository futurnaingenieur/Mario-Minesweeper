import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EndMessage extends JDialog {
	   public MainFrame f;
	    
	/*
	 * This is a frame that appears when the user win or lose the game.
	 * If this is a win frame, the music launched will be different of
	 * the losing music. The user has 3 different choices.
	 * Restart the level, quit to main menu or quit the game.
	 */
	public EndMessage(MainFrame frame, String message){
			
		   super(frame, message, false);
		   f = frame;
		   f.timeCount.suspend();
		   this.setSize(304,81);
		   this.setResizable(false);
		   this.setDefaultCloseOperation(0);
		   this.setAlwaysOnTop(true);
		   if(message.equals("You Lose")){
			   Audio son = new Audio("Loose.wav");
			   son.start();
		   }
		   else{
			   Audio son = new Audio("Win.wav");
			   son.start();
		   }
		   
		   JPanel p = new JPanel();
		   JLabel l = new JLabel();
		   p.add(l);
		   JButton resetBtn = new JButton("Restart");
		   JButton menuBtn = new JButton("Back to Menu");
		   JButton quitBtn = new JButton("Quit");
		   p.add(resetBtn);
		   p.add(menuBtn);
		   p.add(quitBtn);
		   this.add(p);
		   this.setVisible(true);
	
		   this.setLocationRelativeTo(f.banner);
		   
		   this.setLocation(this.getLocation().x,this.getLocation().y-((f.x*25)/2)-130);
		   
		   
		   quitBtn.addActionListener(new ButtonControler(this.f));
		   
		   resetBtn.addActionListener(new ButtonControler(this.f));
		   
		   menuBtn.addActionListener(new ButtonControler(this.f));
			   
		   
	}
}

	
