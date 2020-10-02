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
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;

import processing.core.PApplet;

public class serverMain extends PApplet {
	//private BufferedWriter writer;
	private  BufferedReader read;
	private BufferedWriter write;
	private ArrayList <Usuario> usuarios;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("eclipseS6.serverMain");
		//String id;
		System.out.println();
		

	}
	public void settings() {
		size(500,500);
	}
	public void setup() {
		
		
		initServer();
		usuarios = new ArrayList <Usuario>();
		
		//String id = UUID.randomUUID().toString();
		
		
		
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
						
						while(true) {
							String line = read.readLine();
							System.out.println(line);
							
							Gson gson = new Gson();
							Usuario obj = gson.fromJson(line,Usuario.class);
							usuarios.add(new Usuario ("wilson","wilfredo"));
							usuarios.add(new Usuario ("natalia","natiorvi"));
							usuarios.add(new Usuario ("gabriel","gab8"));
						
							
							
							for (int i = 0; i<usuarios.size(); i++) {
								if(obj.getName().equals(usuarios.get(i).getName())) {
									if(obj.getPass().equals(usuarios.get(i).getPass())) {
									sendMessage("ya esta");	
									}
									
									
									
								}else {
									sendMessage ("no esta inscrito");
									
								}
								
							}
							
						}
					
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				).start();
		
	}
	
	public void sendMessage(String msg){

        new Thread(
                ()-> {
                    try {

                        write.write(msg+"\n");
                       write.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
	
	public void draw() {
		background(0);
	}

}
