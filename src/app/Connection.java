package src.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Scanner;

import org.json.simple.JSONObject;
import src.conf.Conf;

public abstract class Connection implements Runnable {

	private String serverAddress;
	private int serverPort;

	public Connection(String serverAddress, int serverPort){
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
	}

	public void run(){
		try{
			this.waitState();
		}catch(UnknownHostException e){

		} catch(IOException e){

		}
	}

	private void waitState() throws UnknownHostException, IOException{
		ServerSocket server = new ServerSocket(this.serverPort, 0, InetAddress.getByName(this.serverAddress));
    System.out.println(this.serverAddress + ":" + this.serverPort + " is open!");
   
    InetAddress inet = server.getInetAddress();
    System.out.println("HostAddress="+inet.getHostAddress());
    System.out.println("HostName="+inet.getHostName());

    Socket client = server.accept();
    try {
   		this.connect(client);
  	} catch(Exception e){
  		// REFATORAR: exibir mensagem de erro
  	}

    server.close();
	}

	public abstract void connect(Socket client) throws Exception;

}