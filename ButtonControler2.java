import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * This is the ActionListener managing 
 * the button Play and Cancel inside 
 * the SelectLevel frame.
 */


public class ButtonControler2 implements ActionListener {
	
	    SelectLevel sll;
	   
	    
		public ButtonControler2 (SelectLevel sl) {
			sll = sl;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			JButton b = (JButton) e.getSource();
			if(b.getText().equals("Cancel")){
				System.exit(0);
			}
			else if(b.getText().equals("Play")){
				//We check here if the values entered in the JTextFields are coherent
				if (sll.heightSelected<6 || sll.heightSelected>24 || sll.widthSelected<6 || sll.widthSelected>30 || sll.nbMines <1 || sll.nbMines >=sll.heightSelected*sll.widthSelected || sll.nbMines > 668)
				{
					if (sll.heightSelected>24)
					{
						sll.height.setText("24");
						sll.heightSelected = 24;
					}
					if (sll.widthSelected>30)
					{
						sll.width.setText("30");
						sll.widthSelected = 30;
					}
					if (sll.heightSelected<6)
					{
						sll.height.setText("6");
						sll.heightSelected = 6;
					}
					if (sll.widthSelected<6)
					{
						sll.width.setText("6");
						sll.widthSelected = 6;
					}
					if (sll.nbMines > 668 && 668 < sll.heightSelected*sll.widthSelected-1)
					{
						sll.nbMines = 668;
						sll.custom3.setText("Mines (maximum 668)");
					}
					if (668 > sll.heightSelected*sll.widthSelected-1)
					{
						if (sll.nbMines >=sll.heightSelected*sll.widthSelected)
						{
							sll.nbMines = sll.heightSelected*sll.widthSelected-1;
						}
						sll.custom3.setText("Mines (maximum "+(sll.heightSelected*sll.widthSelected-1)+")");
					}
					sll.nbBombs.setText(""+sll.nbMines);
					sll.error.setVisible(true);
				}
				else
				{
					sll.setVisible(false);
					
					sll.son.stop();
					sll.dispose();
					LoadingScreen wait = new LoadingScreen();
					Thread t = new Thread(new Waiter(sll.heightSelected,sll.widthSelected,sll.nbMines,wait));
				    t.start();
					
				}
				
			}
		}
		
		class Waiter implements Runnable{
			int heightSelected;
			int widthSelected;
			int nbMines;
			JFrame frame;
			public Waiter(int i, int j, int z,JFrame frame){
				super();
				heightSelected = i;
				widthSelected = j;
				nbMines = z;
				this.frame = frame;
			}
			 Segment seg = new Segment();
			 TimeCounter timeCount = new TimeCounter(seg);
			@Override
			public void run() {
				timeCount.start();
				int time = timeCount.aff.getValeur();
				while(time<3){
					System.out.println("");
					time = timeCount.aff.getValeur();
				}
				frame.dispose();
				MainFrame frame2 = new MainFrame(heightSelected,widthSelected,nbMines);
			}
			 
			
			 
		}
}
