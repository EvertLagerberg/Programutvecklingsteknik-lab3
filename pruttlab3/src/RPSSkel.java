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
	int counter = 1; // To count ONE ... TWO and on THREE you play
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	JButton closebutton;
	JButton soundbutton;
	Klient klient = new Klient();
	Boolean audio = true;

	RPSSkel(String name) throws IOException {
		klient.send(name);
		klient.read();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		closebutton = new JButton("Close");
		closebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		soundbutton = new JButton("Audio ON");
		soundbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audio = !audio;
				if (audio)
					soundbutton.setText("Audio ON");
				else
					soundbutton.setText("Audio OFF");
			}
		});

		myboard = new Gameboard(name, this); // Must be changed later!
		computersboard = new Gameboard("Computer");

		JPanel boards = new JPanel();
		boards.setLayout(new GridLayout(1, 2));
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(2, 1));
		boards.add(myboard);
		boards.add(computersboard);
		options.add(soundbutton);
		add(closebutton, BorderLayout.SOUTH);
		add(boards, BorderLayout.CENTER);
		add(options, BorderLayout.NORTH);

		setSize(300, 550);
		setVisible(true);

	}

	public static void main(String[] args) throws IOException {
		String name = args[0];
		new RPSSkel(name);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (counter == 1) {
			myboard.resetColor();
			computersboard.resetColor();
			myboard.setLower("ETT");
			computersboard.setLower("ETT");
			counter++;
		} else if (counter == 2) {
			myboard.setLower("TVÅ");
			computersboard.setLower("TVÅ");
			counter++;
		} else if (counter == 3)

			try {
				myboard.markPlayed(e.getActionCommand());
				klient.send(e.getActionCommand());
				String computerhand = klient.read();
				computersboard.markPlayed(computerhand);
				myboard.setUpper(e.getActionCommand());
				computersboard.setUpper(computerhand);
				counter = 1;
				compare(e.getActionCommand(), computerhand);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	public void compare(String playerhand, String computerhand) {

		if (playerhand.equals(computerhand)) {
			myboard.setLower("DRAW");
			computersboard.setLower("DRAW");
			if (audio)
				playSound("draw.wav");
		} else if (playerhand.equals("STEN")) {
			if (computerhand.equals("PASE")) {
				computersboard.wins();
				if (audio)
					playSound("lose.wav");
			} else if (computerhand.equals("SAX")) {
				myboard.wins();
				if (audio)
					playSound("win.wav");
			}
		} else if (playerhand.equals("PASE")) {
			if (computerhand.equals("SAX")) {
				computersboard.wins();
				if (audio)
					playSound("lose.wav");
			} else if (computerhand.equals("STEN")) {
				myboard.wins();
				if (audio)
					playSound("win.wav");
			}
		} else if (playerhand.equals("SAX")) {
			if (computerhand.equals("STEN")) {
				computersboard.wins();

				if (audio)
					playSound("lose.wav");
			} else if (computerhand.equals("PASE")) {
				myboard.wins();
				playSound("win.wav");
			}

		}

	}

	public void playSound(String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

	}
}
