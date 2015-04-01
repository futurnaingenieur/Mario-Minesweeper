import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

/*
 * The field is a JPanel ordering with a GridLayout containing
 * the mine sweeper's field of case.
 */


public class Field extends JPanel {
	
	Case matrix[][];
	int rows;
	int columns;
	int nbrmines;
	int nbrFlag;
	MainFrame jf;
	
	// The constructor of the class Field, which creates a field of x rows and y columns containing "mines" bombs and contained in the MainFrame called jf.
	    public Field(int x, int y, int mines, MainFrame jf){
		this.setSize(new Dimension(y*25,x*25));
		this.setLayout(new GridLayout(x,y));
		this.rows = x;
		this.columns = y;
		this.nbrmines = mines;
		this.nbrFlag= mines;
		this.jf =jf;
		matrix = new Case[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				matrix[i][j] = new Case(i,j);
				matrix[i][j].parent = this;
				matrix[i][j].addMouseListener(new GameControl(jf));
			}
		}
		this.dropBomb(mines);
		this.giveValue();
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				this.add(matrix[i][j]);
			}
		}
		
		
		
	}
	
	// The method that drops a number of bombs entered in parameter on the field. It's used at the beginning of the mine sweeper execution, when the field is build up.
	public void dropBomb(int bombs){
		Random rand = new Random();
		int m;
		int n;
		while(bombs!=0){
			m=rand.nextInt(this.rows);
			n=rand.nextInt(this.columns);
			if(!matrix[m][n].isBomb){
						bombs--;
						matrix[m][n].isBomb=true;
			}		
		}
	}
	
	// The method that computes and return the number of bombs hidden around a targeted case. This number will be the coefficient of the case.
	public int bombsAround(Case target){
		int nbr=0;
		
		// case left
		if(target.y>0 && matrix[target.x][(target.y)-1].isBomb==true){
			nbr++;
		}
		// case left top
		if(target.y>0 && target.x>0 && matrix[(target.x)-1][(target.y)-1].isBomb==true){
			nbr++;
		}
		// case top
		if(target.x>0 && matrix[(target.x)-1][(target.y)].isBomb==true){
			nbr++;
		}
		// case top right
		if(target.y<(this.columns)-1 && target.x>0 && matrix[(target.x)-1][(target.y)+1].isBomb==true){
			nbr++;
		}
		// case right
		if(target.y<(this.columns)-1 &&  matrix[(target.x)][(target.y)+1].isBomb==true){
			nbr++;
		}
		// case bottom right
		if(target.y<(this.columns)-1 && target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)+1].isBomb==true){
			nbr++;
		}
		// case bottom
		if(target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)].isBomb==true){
			nbr++;
		}
		// case bottom left
		if(target.y>0 && target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)-1].isBomb==true){
			nbr++;
		}
		
		
		return nbr;
	}
	
	// The recursive method that the program uses when the player click on an hidden case in order to reveal it and all the case without bomb that are close to it.
	public void reveal(Case target){
		if(target.hidden){
			
			if(!target.isBomb){
				target.hidden=false;
				if(target.value==0){
					
					// case left
					if(target.y>0 && matrix[target.x][(target.y)-1].isBomb==false){
						reveal(matrix[target.x][(target.y)-1]);
					}
					// case left top
					if(target.y>0 && target.x>0 && matrix[(target.x)-1][(target.y)-1].isBomb==false && matrix[(target.x)-1][(target.y)-1].value>0){
						reveal(matrix[(target.x)-1][(target.y)-1]);
					}
					// case top
					if(target.x>0 && matrix[(target.x)-1][(target.y)].isBomb==false){
						reveal(matrix[(target.x)-1][(target.y)]);
					}
					// case top right
					if(target.y<(this.columns)-1 && target.x>0 && matrix[(target.x)-1][(target.y)+1].isBomb==false && matrix[(target.x)-1][(target.y)+1].value>0){
						reveal(matrix[(target.x)-1][(target.y)+1]);
					}
					// case right
					if(target.y<(this.columns)-1 &&  matrix[(target.x)][(target.y)+1].isBomb==false){
						reveal(matrix[(target.x)][(target.y)+1]);
					}
					// case bottom right
					if(target.y<(this.columns)-1 && target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)+1].isBomb==false && matrix[(target.x)+1][(target.y)+1].value>0){
						reveal(matrix[(target.x)+1][(target.y)+1]);
					}
					// case bottom
					if(target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)].isBomb==false){
						reveal(matrix[(target.x)+1][(target.y)]);
					}
					// case bottom left
					if(target.y>0 && target.x<(this.rows)-1 && matrix[(target.x)+1][(target.y)-1].isBomb==false && matrix[(target.x)+1][(target.y)-1].value>0){
						reveal(matrix[(target.x)+1][(target.y)-1]);
					}
				}
			}
			//Now it's the case when the user click on a case containing a bomb. It launch the loosing process.
			else{
			target.explose=true;
			target.hidden=false;
			
			this.revealAll();
			
			EndMessage end = new EndMessage(jf, "You Lose");
			jf.disable();
			}
		}
		}
	
		
	// The method that reveal all the field when the player click on a bomb.
	public void revealAll(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				matrix[i][j].hidden=false;
				
			  }
			}
	}
	
	// The method that give a value for each case of the field using the bombsAround method.
	public void giveValue(){
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
					if(!matrix[i][j].isBomb){
					matrix[i][j].value=this.bombsAround(matrix[i][j]);
					}
				}
			}
		}
	
	// The method that tests when the player reveal a case without bomb if all the hidden cases remaining hide bombs. If it's the case, the player wins.
	public boolean isWin(){
		boolean win=true;
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
				if(matrix[i][j].hidden && !matrix[i][j].isBomb){
					win=false;
				}
				
			}
	    }
		return win;
	}
	
	// The method that tests when all the flag allowed are placed, if they are all placed on a bomb. If it's the case, then the player wins.
	public boolean flagWin(){
		boolean win=true;
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
				if(matrix[i][j].isFlag && !matrix[i][j].isBomb){
					win=false;
				}
				
			}
	    }
		return win;
	}
	
	
}

