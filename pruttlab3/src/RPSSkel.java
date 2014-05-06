import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

import java.net.*;
import java.io.*;
import java.util.*;
class RPSSkel extends JFrame implements ActionListener {
	
	
	
	  AudioFormat audioFormat;
	  AudioInputStream audioInputStream;
	  SourceDataLine sourceDataLine;
	  boolean audio = true;

	  
	
	
	
	
	
	
	
	
	
    Gameboard myboard, computersboard;
    int counter = 1; // To count ONE ... TWO  and on THREE you play
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    JButton closebutton;
    
    public Klient klient = new Klient();
    
    
    RPSSkel (String player) throws IOException {
    	
  
  
    klient.send(player);
    klient.read();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	closebutton = new JButton("Close");
	myboard = new Gameboard(player, this);
	computersboard = new Gameboard("Computer");
	
	
	JPanel boards = new JPanel();
	boards.setLayout(new GridLayout(1,2));
	boards.add(myboard);
	boards.add(computersboard);
	
	
	JPanel audioboard = new JPanel();

	final JTextField textField =
            new JTextField("Audio on");
  	final JButton audioBtn = new JButton("Toggle Audio");
  	
	audioBtn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			audio = !audio;
			System.out.println(audio);
			textField.setText("Off");
			
			
		}
		
	});
  	
  	
  	
  	
  	audioboard.setLayout(new GridLayout(1,2));

	audioboard.add(audioBtn);
	audioboard.add(textField);
  	
  	
	add(boards, BorderLayout.CENTER);
	closebutton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("tja");
			
		}
		
	});
	add(closebutton, BorderLayout.SOUTH);
	add(audioboard, BorderLayout.NORTH);
	setSize(300, 550);
	setVisible(true);

    }

    public static void main (String[] u) throws IOException {
	new RPSSkel(u[0]);
	

	
	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(counter == 1){
			myboard.setLower("ETT");
			computersboard.setLower("ETT");
			counter++;
		}
		else if(counter == 2){
			myboard.setLower("TVÅ");
			computersboard.setLower("TVÅ");
			counter++;
			
		}
		else if(counter == 3){
			String computerhand = "";

			myboard.setLower("TRE");
			myboard.setUpper(e.getActionCommand());
			myboard.markPlayed(e.getActionCommand());
			
			
			counter = 1;
			klient.send(e.getActionCommand());
			try {
				computerhand = klient.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			computersboard.setLower("TRE");
			computersboard.setUpper(computerhand);
			compare(e.getActionCommand(), computerhand);
		
		}
	}
	
public void compare(String player, String computer){
	
	
	
}
	
	

}
