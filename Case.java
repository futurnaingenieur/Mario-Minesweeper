import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * This is a JPanel that displays the boxes of the 
 * mine sweeper's game.
 * 
 */
public class Case extends JPanel {
		boolean isBomb;
		boolean isFlag;
		boolean isIntero;
		boolean hidden;
		boolean selected;
		boolean explose;
		Thread t;
		Field parent;
		int value;
		int x;
		int y;
	
			                   
	//constructor with x = coord on the line and y coord on the row		  
	public Case(int x, int y){
		isBomb = false;
		isFlag = false;
		hidden = true;
		selected = false;
		isIntero = false;
		explose = false;
		value = 0;
		this.x = x;
		this.y = y;
		this.setSize(20,20);
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new FlowLayout());
		
	}
	
	
	
	  
	  public void paintComponent(Graphics g){
		    Font font = new Font("Courier", Font.BOLD, 17);
		    g.setFont(font);
		    g.setColor(Color.WHITE); 
		    super.paintComponent(g);    
		 if(hidden){
			 if(!selected){
				 Image imgCase = null;
					try {
						imgCase = ImageIO.read(new File("cases.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				      g.drawImage(imgCase, 0, 0, this);
				  
			 
			 if(isFlag){
				 Image imgFlag = null;
					try {
						imgFlag = ImageIO.read(new File("flagmario.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				      g.drawImage(imgFlag, 0, 0, this);
				   
			 }
			 if(isIntero){
				 Image imgIntero = null;
					try {
						imgIntero = ImageIO.read(new File("interomario.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				      g.drawImage(imgIntero, 0, 0, this);
			
			 }
			 }
			 else{
				 if(isIntero){
					 Image imgInterosel = null;
						try {
							imgInterosel = ImageIO.read(new File("interomariosel.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					      g.drawImage(imgInterosel, 0, 0, this);
				 	}
				 else if(isFlag){
					 Image imgflagsel = null;
						try {
							imgflagsel = ImageIO.read(new File("flagmariosel.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  
					      g.drawImage(imgflagsel, 0, 0, this);
				 	}
				 else{
				 Image imgCase = null;
					try {
						imgCase = ImageIO.read(new File("caseselected.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				      g.drawImage(imgCase, 0, 0, this);
				 }  
			 }
			
		 }
		 
		 else{
		  if(!this.isBomb){
		 if(this.value==0){
			  g.setColor(Color.BLUE);
			  g.fillRect(1,1,2000,2000);
		  }
		  if(this.value==1){
			  g.setColor(Color.LIGHT_GRAY);
			  g.fillRect(1,1,2000,2000);
			  g.setFont(font);
			  g.setColor(Color.WHITE);
			  g.drawString("1", 8, 17);  
		  
			  
		  }
		  if(this.value==2){
			  g.setColor(Color.RED);
			  g.fillRect(1,1,2000,2000);
			  g.setColor(Color.WHITE);
			  g.drawString("2", 8, 17);  
		  }
		  if(this.value==3){
				  g.setColor(Color.BLACK);
				  g.fillRect(1,1,2000,2000);
				  g.drawString("3", 0, 0);
				  g.setColor(Color.WHITE);
				  g.drawString("3", 8, 17);  
			  }
	      if(this.value==4){
				  g.setColor(Color.PINK);
				  g.fillRect(1,1,2000,2000);
				  g.setColor(Color.WHITE);
				  g.drawString("4", 8, 17);  
			  }
		  if(this.value==5){
				  g.setColor(Color.GREEN);
				  g.fillRect(1,1,2000,2000);
				  g.setColor(Color.WHITE);
				  g.drawString("5", 8, 17);  
			  }
		  if(this.value==6){
			  g.setColor(Color.MAGENTA);
			  g.fillRect(1,1,2000,2000);
			  g.setColor(Color.WHITE);
			  g.drawString("6", 8, 17);  
		  }
		  if(this.value==7){
			  g.setColor(Color.ORANGE);
			  g.fillRect(1,1,2000,2000);
			  g.setColor(Color.WHITE);
			  g.drawString("7", 8, 17);  
		  }
		  if(this.value==8){
			  g.setColor(Color.CYAN);
			  g.fillRect(1,1,2000,2000);
			  g.setColor(Color.WHITE);
			  g.drawString("8", 8, 17);  
		  }
		  }
		  else{
			  
			  if(!explose){
			  Image img = null;
			try {
				img = ImageIO.read(new File("Bombmario.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		      g.drawImage(img, 0, 0, this);
		    }
			  else if(explose){
				  Image img2 = null;
					try {
						img2 = ImageIO.read(new File("explo3.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				      g.drawImage(img2, 0, 0, this);
			  }
			 
		    }
		 }
		 
		 repaint();
	    }
	  }	
	
	
	

