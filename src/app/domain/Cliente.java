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
   	this.start();
   	this.waiting();
   	this.messages();
   	this.exit();
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

	private void messages(){
		Scanner teclado = new Scanner(System.in);
    PrintStream saida = new PrintStream(clientSocket.getOutputStream());
    
    String msg;
    while (teclado.hasNextLine()) {
      msg = teclado.nextLine();
      saida.println(msg);
      if(saida.println()){
      	break;
      }
    }
    
    saida.close();
    teclado.close();
	}

	private void exit(){
	}
}