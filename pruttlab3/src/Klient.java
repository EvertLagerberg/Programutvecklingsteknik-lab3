import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient {
	
	private PrintWriter ut;
	private BufferedReader in;
	
	Klient() {
		try {
			Socket socket = new Socket("localhost", 4323);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			ut = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static void main(String args[]) {
		new Klient();
		new Gameboard("Tommy");
	}*/
	
	
	public void send(String message){
		ut.println(message);
		ut.flush();
	}
	
	
	public String read() throws IOException{
		return(in.readLine());
	}
	
	
}
