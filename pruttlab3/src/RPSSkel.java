import java.awt.*;
import java.awt.event.*;
<<<<<<< HEAD
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
=======
>>>>>>> branch 'master' of https://github.com/EvertLagerberg/pruttlab3

import javax.swing.*;

import java.net.*;
import java.io.*;
import java.util.*;
class RPSSkel extends JFrame implements ActionListener {
	
	
    Gameboard myboard, computersboard;
    int counter; // To count ONE ... TWO  and on THREE you play
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    JButton closebutton;
    
    RPSSkel () {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	closebutton = new JButton("Close");
<<<<<<< HEAD
	myboard = new Gameboard("Myself", this); // Must be changed later!
=======
	myboard = new Gameboard("Myself", this);
>>>>>>> branch 'master' of https://github.com/EvertLagerberg/pruttlab3
	computersboard = new Gameboard("Computer");
	JPanel boards = new JPanel();
	boards.setLayout(new GridLayout(1,2));
	boards.add(myboard);
	boards.add(computersboard);
	add(boards, BorderLayout.CENTER);
	closebutton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println("tja");
			
		}
		
	});
	add(closebutton, BorderLayout.SOUTH);
	setSize(300, 550);
	setVisible(true);
    
    
    
    closebutton.addActionListener(new ActionListener(){
    	public void actionPerformed (ActionEvent e){ 
    		System.out.println("Hej då!");
    	}
    	});
    
    }
<<<<<<< HEAD
    public static void main (String[] u) {
=======

    public static void main (String[] u) throws IOException {
>>>>>>> branch 'master' of https://github.com/EvertLagerberg/pruttlab3
	new RPSSkel();
	Klient klient = new Klient();
	klient.Send("Evert");
	klient.Read();
	klient.Send("Sax");
	klient.Read();
	klient.Send("Pase");
	klient.Read();
	
	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
<<<<<<< HEAD
		
=======
>>>>>>> branch 'master' of https://github.com/EvertLagerberg/pruttlab3
	}
	
<<<<<<< HEAD
=======
	
	
	

>>>>>>> branch 'master' of https://github.com/EvertLagerberg/pruttlab3
}
