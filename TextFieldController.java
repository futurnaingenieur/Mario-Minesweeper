import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

/*
 * This class is listening key events during the game, and
 * more precisely during the main menu when we try to custom
 * the field. This class verify that we enter in the
 * JTextField is coherent. 
 */
public class TextFieldController implements KeyListener {

	SelectLevel sll;
	JTextField jtff;
	
	public TextFieldController (SelectLevel sl, JTextField jtf) {
		this.sll = sl;
		this.jtff = jtf;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		sll.error.setVisible(false);
	
		//We verify if the Key pressed is really a number	
		if(!isNumeric(e.getKeyChar()))
		{
			jtff.setText(jtff.getText().replace(String.valueOf(e.getKeyChar()), ""));
		}
		else
		{
			//We verify that the Key pressed isn't the backspace
			if(!isBackSpace(e.getKeyCode()))
			{
				if (jtff.getName().equals("height"))
				{
					sll.heightSelected = Integer.parseInt(jtff.getText());
					if ((sll.heightSelected)*(sll.widthSelected)-1 > 668)
					{
						sll.custom3.setText("Mines (maximum 668)");
					}
					else
					{
						sll.custom3.setText("Mines (maximum "+((sll.heightSelected)*(sll.widthSelected)-1)+")");
					}
				}
				else if (jtff.getName().equals("width"))
				{
					sll.widthSelected = Integer.parseInt(jtff.getText());
					if ((sll.heightSelected)*(sll.widthSelected)-1 > 668)
					{
						sll.custom3.setText("Mines (maximum 668)");
					}
					else
					{
						sll.custom3.setText("Mines (maximum "+((sll.heightSelected)*(sll.widthSelected)-1)+")");
					}
					
				}
				else if (jtff.getName().equals("nbBombs"))
				{
					sll.nbMines = Integer.parseInt(jtff.getText());	
				}
			}     
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	public boolean isBackSpace (int code) {
		if (code == 8 )
			return true;
		return false;
	}
	
	public boolean isNumeric(char carac){
		try {
			Integer.parseInt(String.valueOf(carac));
		}
		catch (NumberFormatException e) {
			return false;            
		}
		return true;
	}
}
