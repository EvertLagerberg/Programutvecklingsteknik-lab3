import java.awt.*;
import java.awt.event.*;

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
	myboard = new Gameboard("Myself", this);
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

    }

    public static void main (String[] u) throws IOException {
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
	}
	
	
	
	

}
