import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/*
 * This is the ActionListener managing 
 * the button Quit, restart and Back to Menu
 * inside the EndMessage JDialog.
 */

public class ButtonControler implements ActionListener {
    MainFrame jff;
    
	ButtonControler(MainFrame jf){
		
		jff = jf;
	}
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Quit")){
			System.exit(0);
		}
		else if(b.getText().equals("Restart")){
			jff.dispose();
			MainFrame frame2 = new MainFrame(jff.x,jff.y,jff.mainField.nbrmines);
			
		}
		else if(b.getText().equals("Back to Menu")){
			jff.dispose();
			SelectLevel frame = new SelectLevel("Main Menu");
		}
	}

}
