import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * This is the Frame which appears at the beginning, 
 * asking the user what kind of level he wants. 
 * There are 3 levels of difficulty (easy, medium and hard) 
 * and one custom level. 
 */

public class SelectLevel extends JFrame {
	
	int heightSelected;
	int widthSelected;
	int nbMines;
	String levelSelected;
	ImagePanel mario;
	
	Audio son;
	ImagePanel	 wallpaper;
	
	Toolkit toolkit;
	Dimension screenDim;
	
	String[] tab1 = {"Easy","Medium","Hard","Custom"};

	JPanel container = new JPanel();
	JPanel top = new JPanel();
	JPanel customChoice = new JPanel();
	JPanel down = new JPanel();
	
	JComboBox<String> combo ;
	
	JTextField height;
	JTextField width;
	JTextField nbBombs;
	
	JButton play;
	JButton cancel;
					
	JLabel intro1;
	JLabel intro2;
	JLabel custom1;
	JLabel custom2;
	JLabel custom3;
	JLabel error;
	
	
	public SelectLevel (String title) {
		
		heightSelected=9;
		widthSelected=9;
		nbMines=10;
		levelSelected = "";
		
		wallpaper = new ImagePanel("Wallpaper.png","Mario.png");
		
		toolkit = Toolkit.getDefaultToolkit();
		screenDim = toolkit.getScreenSize();

		container = new JPanel();
		top = new JPanel();
		customChoice = new JPanel();
		down = new JPanel();
		
		combo = new JComboBox<String>(tab1);
		
		height = new JTextField(2);
		width = new JTextField(2);
		nbBombs = new JTextField(3);
		
		
		play = new JButton("Play");
		cancel = new JButton("Cancel");
						
		intro1 = new JLabel("Welcome to the Mine Sweeper game");
		intro2 = new JLabel("Please choose your dificulty level");
		custom1 = new JLabel("Height (6-24)");
		custom2 = new JLabel("Width  (6-30)");
		custom3 = new JLabel("Mines (maximum 35)");
		error = new JLabel("Bad custom values ... Please enter expected values ");
			
		this.setSize(320,300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle(title);	
		this.setAlwaysOnTop(true);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		wallpaper.setLayout(new BoxLayout(wallpaper, BoxLayout.PAGE_AXIS));
		wallpaper.setSize(new Dimension(310,270));
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setBackground(Color.WHITE);
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		intro1.setForeground(Color.YELLOW);
		intro2.setForeground(Color.BLACK);
		error.setForeground(Color.RED);
		error.setVisible(false);
		
		top.setBackground(Color.WHITE);
		down.setBackground(Color.WHITE);
		
		combo.setPreferredSize(new Dimension(120, 20));
		
		top.add(intro1);
		top.add(intro2);
	    top.add(combo);
	    
	    customChoice.add(custom1);
	    customChoice.add(height);
	    customChoice.add(custom2);
	    customChoice.add(width);
	    customChoice.add(custom3);
	    customChoice.add(nbBombs);
	    customChoice.add(error);
	    customChoice.setBackground(Color.LIGHT_GRAY);
	    customChoice.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));	    
	    customChoice.setVisible(false);
	     
	    down.add(play);
	    down.add(cancel);
	    down.setOpaque(false);
	    top.setOpaque(false);
	    
	    container.setOpaque(false);
	    container.setVisible(false);
	    
	    wallpaper.add(top);
	    wallpaper.add(customChoice);
	    wallpaper.add(down);
	    wallpaper.setVisible(true);
	    this.add(wallpaper);
	    
	    this.setContentPane(wallpaper);
	    this.setVisible(true);
	    
	    this.setVisible(true);
	    
	    son = new Audio("Menu.wav");
	    son.setDaemon(true);
		son.start();
		   
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelSelected = ""+combo.getSelectedItem();
				if (levelSelected.equals("Custom"))
				{
					customChoice.setVisible(true);
					heightSelected = 6;
					widthSelected = 6;
					nbMines = 5;
				}
				else 
				{
					customChoice.setVisible(false);
					if (levelSelected.equals("Easy"))
					{
						heightSelected = 9;
						widthSelected = 9;
						nbMines = 10;
					}
					else if (levelSelected.equals("Medium"))
					{
						heightSelected = 16;
						widthSelected = 16;
						nbMines = 40;
					}
					else if (levelSelected.equals("Hard"))
					{
						heightSelected = 16;
						widthSelected = 30;
						nbMines = 99;
					}
				}
			}
		});
		
	
		height.setPreferredSize(new Dimension(50,20));
		width.setPreferredSize(new Dimension(50,20));
		nbBombs.setPreferredSize(new Dimension(50,20));
		
		height.setDocument(new JTextFieldLimit(2));
		width.setDocument(new JTextFieldLimit(2));
		nbBombs.setDocument(new JTextFieldLimit(3));
		
		height.setText("6");
		width.setText("6");
		nbBombs.setText("5");
		
		height.setName("height");
		width.setName("width");
		nbBombs.setName("nbBombs");
		
		height.addKeyListener(new TextFieldController(this, height));
		width.addKeyListener(new TextFieldController(this, width));
		nbBombs.addKeyListener(new TextFieldController(this, nbBombs));
		
		play.addActionListener(new ButtonControler2 (this));
		
		cancel.addActionListener(new ButtonControler2 (this));
		
			    
	   	}

}
