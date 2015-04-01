import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * This class allow the program to control mouse actions during the game.
 * In function of the action, it appends a modification in the game field.
 */

public class GameControl implements MouseListener {
	
	MainFrame mff;
	
	public GameControl(MainFrame mf) {
		mff = mf;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//checking if it is the first click and launch the timer in this case.
		if (mff.firstClick==false) {
			mff.timeCount.start();
			mff.firstClick = true;
		}
		Case c = (Case) e.getSource();
		// this is the  when the user right click on a case.
		if(e.getButton()==3){
			if(c.hidden){
			if(!c.isIntero && !c.isFlag){
				if(c.parent.nbrFlag>0){
				//That's the sound launched when the user put a flag.
				Audio son = new Audio("Flag.wav");
			    son.start();
				c.isFlag=true;
				c.parent.nbrFlag--;
				if(c.parent.nbrFlag==0 && c.parent.flagWin()){
					//for each flag put, we check if the it was the last flag remaining and if all the flag are on mines. In this case the user wins!
					EndMessage end = new EndMessage(c.parent.jf, "You Win!");
					mff.disable();
				}
			 }
			}	
			else if(c.isIntero){
				c.isIntero=false;
			}
			else if (c.isFlag){
				c.isFlag=false;
				c.isIntero=true;
				c.parent.nbrFlag++;
			}
			
		}
	   }
	   if(e.getButton()==1)
		   if(c.hidden){
			   Audio son = new Audio("Brake.wav");
			   son.start();
			   if(c.isFlag){
				   c.parent.nbrFlag++;
			   }
			   c.parent.reveal(c);
			   if(!c.isBomb && c.parent.isWin()){
				   //for each left click on a case, check if it's not a bomb and check if it was the last case without a bomb, in this case, the user wins!
				   EndMessage end = new EndMessage(c.parent.jf, "You Win!");
				   mff.disable();
			   }
		   }
	   mff.segFlag.setValeur(c.parent.nbrFlag);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//when we the mouse goes on a case, the case change of color (selected = true)
		Case c = (Case) e.getSource();
		c.selected = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//when the mouse leave the case, the color strike back to the initial color (selected strike back to false)
		Case c = (Case) e.getSource();
		c.selected = false;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
