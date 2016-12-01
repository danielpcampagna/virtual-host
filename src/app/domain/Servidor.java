package src.app.domain;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Servidor implements Runnable {
	
	private static final String EXIT_KEYWORD = "exit";

	private String address;
	private int port;

	private ServerSocket serverSocket;
	private Socket clientSocket;

	public Servidor(String address, int port) {
		// REFTORAR: Usar funçoes 'set' e checar a validade dos valores passados
		this.address = address;
		this.port = port;

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

	private void start() throws IOException, UnknownHostException {
		this.serverSocket = new ServerSocket(this.port, 2, InetAddress.getByName(this.address));
    System.out.println("Porta "+this.port+" aberta!");
    System.out.println("Endereço "+this.address+" em uso!");
	}

	private void waiting() throws IOException{
    clientSocket = serverSocket.accept();
    System.out.println("Nova conexão com o cliente " +   
      clientSocket.getInetAddress().getHostAddress());
	}

	private void messages() throws IOException{
		Scanner entrada = new Scanner(clientSocket.getInputStream());
		String msg;

	    while (entrada.hasNextLine()) {
	    	msg = entrada.nextLine();
	    	System.out.println(msg);
	    	if(msg.trim().equals(EXIT_KEYWORD)){
	    		break;
	    	}
	    }

	    entrada.close();
	}

	private void exit() throws IOException{
		serverSocket.close();
	}

	private void errorMessage(Exception e){
		System.err.println(e.getMessage());
	}

}