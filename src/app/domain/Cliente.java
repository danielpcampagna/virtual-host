package src.app.domain;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;

public class Cliente {
	private static final String EXIT_KEYWORD = "exit";

	private String address;
	private int port;
	private String serverAddress;
	private int serverPort;

	private Socket clientSocket;

	public Cliente(String address, int port, String serverAddress, int serverPort) {
		// REFTORAR: Usar funçoes 'set' e checar a validade dos valores passados
		this.address 				= address;
		this.port 					= port;
		this.serverAddress 	= serverAddress;
		this.serverPort 		= serverPort;

	}

	public void run(){
   	try {
	   	this.start();
	   	this.waiting();
	   	this.messages();
	   	this.exit();
	  } catch(Exception e){
	  	this.errorMessage(e);
	  }
	}	

	private void start() throws UnknownHostException, IOException {

		this.clientSocket = new Socket(
			InetAddress.getByName(this.serverAddress),
			this.serverPort,
			InetAddress.getByName(this.address),
			this.port);
	}

	private void waiting(){
	}

	private void messages() throws IOException{
		Scanner teclado = new Scanner(System.in);
    PrintStream saida = new PrintStream(clientSocket.getOutputStream());
    
    String msg;
    while (teclado.hasNextLine()) {
      msg = teclado.nextLine();
      saida.println(msg);
      if(msg.trim().equals(EXIT_KEYWORD)){
      	break;
      }
    }
    
    saida.close();
    teclado.close();
	}

	private void exit(){
	}

	private void errorMessage(Exception e){
		System.err.println(e.getMessage());
	}
}