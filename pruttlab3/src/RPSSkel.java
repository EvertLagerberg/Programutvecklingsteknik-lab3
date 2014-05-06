import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import java.net.*;
import java.io.*;
import java.util.*;
class RPSSkel extends JFrame implements ActionListener {
	
	
	
		
	Gameboard myboard, computersboard;
    int counter = 1; // To count ONE ... TWO  and on THREE you play
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    JButton closebutton;
    JButton soundbutton;
    Klient klient = new Klient();
    
    
    RPSSkel (String name) throws IOException {
    klient.send("Tommy");
    klient.read();	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	closebutton = new JButton("Close");
	soundbutton = new JButton("Sound");
	myboard = new Gameboard(name, this); // Must be changed later!
	computersboard = new Gameboard("Computer");
	JPanel boards = new JPanel();
	boards.setLayout(new GridLayout(1,2));
	JPanel options = new JPanel();
	options.setLayout(new GridLayout(2,1));
	boards.add(myboard);
	boards.add(computersboard);
	options.add(soundbutton);
	options.add(closebutton);
	add(boards, BorderLayout.CENTER);
	add(options, BorderLayout.SOUTH);
	
	setSize(300, 550);
	setVisible(true);
    
    closebutton.addActionListener(new ActionListener(){
    	public void actionPerformed (ActionEvent e){ 
    		System.exit(0);
    	}
    	});
    
    }
    public static void main (String[] args) throws IOException  {
    String name = args[0];
	new RPSSkel(name);
	
	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(counter == 1){
			myboard.resetColor();
			computersboard.resetColor();
			myboard.setLower("ETT");
			computersboard.setLower("ETT");
			counter++;
		
		
		}
		else if(counter == 2){
			myboard.setLower("TVÅ");
			computersboard.setLower("TVÅ");
			counter++;
		}
		else if(counter == 3)
			
			try {
					myboard.markPlayed(e.getActionCommand());
					klient.send(e.getActionCommand());
					String computerhand = klient.read();
					computersboard.markPlayed(computerhand);
					myboard.setUpper(e.getActionCommand());
					computersboard.setUpper(computerhand);
					counter=1;
					compare(e.getActionCommand(),computerhand);
				}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
 
	public void compare(String playerhand, String computerhand){
		
	
	

		if(playerhand.equals(computerhand)){
			myboard.setLower("DRAW");
			computersboard.setLower("DRAW");
			playSound("draw.wav");
		}
		else if (playerhand.equals("STEN")){
			if (computerhand.equals("PASE")){
				computersboard.wins();
				playSound("lose.wav");
			}
			else if (computerhand.equals("SAX")){
				myboard.wins();
				playSound("win.wav");
			}
		}
		else if (playerhand.equals("PASE")){
			if (computerhand.equals("SAX")){
				computersboard.wins();
				playSound("lose.wav");
			}
			else if (computerhand.equals("STEN")){
				myboard.wins();
				playSound("win.wav");
			}
	}
		else if (playerhand.equals("SAX")){
			if (computerhand.equals("STEN")){
				computersboard.wins();
				playSound("lose.wav");
			}
			else if (computerhand.equals("PASE")){
				myboard.wins();
				playSound("win.wav");
			}
			
	}
	
}
	
	public void playSound(String filename){
		try
	    {
			Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("test.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
	}
}
