import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Klient {
	
	Klient(){
		try {
		       Socket socket=new Socket("gru-ld03.csc.kth.se",4713);
		       BufferedReader in=new BufferedReader
		           (new InputStreamReader(socket.getInputStream()));
		       PrintWriter ut=new PrintWriter(socket.getOutputStream());
		       ut.println("Tommy"); ut.flush();
		       System.out.println(in.readLine());
		   }
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		public static void main(String args[]) {
			new Klient();
			new Gameboard("Tommy");
		}
}
