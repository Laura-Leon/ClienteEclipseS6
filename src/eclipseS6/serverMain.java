package eclipseS6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

import processing.core.PApplet;

public class serverMain extends PApplet {
	private BufferedWriter writer;
	private  BufferedReader read;
	private BufferedWriter write;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("eclipseS6.serverMain");
		//String id = UUID.randomUUID().toString();
		//System.out.println();
		

	}
	public void settings() {
		size(500,500);
	}
	public void setup() {
		
		initServer();
	}
	public void initServer() {
		
		new Thread(
				()->{
					try {
						ServerSocket server = new ServerSocket(5000);
						System.out.println("Esperando...");
						Socket socket = server.accept();
						System.out.println("Conectado");
						
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						read = new BufferedReader (isr);
						
						OutputStream out = socket.getOutputStream();
						OutputStreamWriter outs = new OutputStreamWriter (out);
						write = new BufferedWriter (outs);
						
						//BufferedReader reader = new BufferedReader(new InputStreamReader(id));
						//writer = new BufferedWriter(new OutputStreamWriter(out));
						
						while(true) {
							String line = read.readLine();
							System.out.println(line);
							
						}
					
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				).start();
		
	}
	public void draw() {
		background(0);
	}

}
