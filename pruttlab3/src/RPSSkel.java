import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	myboard = new Gameboard("Myself", this); // Must be changed later!
	computersboard = new Gameboard("Computer");
	JPanel boards = new JPanel();
	boards.setLayout(new GridLayout(1,2));
	boards.add(myboard);
	boards.add(computersboard);
	add(boards, BorderLayout.CENTER);
	add(closebutton, BorderLayout.SOUTH);
	setSize(300, 550);
	setVisible(true);
    
    
    
    closebutton.addActionListener(new ActionListener(){
    	public void actionPerformed (ActionEvent e){ 
    		System.out.println("Hej då!");
    	}
    	});
    
    }
    public static void main (String[] u) {
	new RPSSkel();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
	}
	
}
