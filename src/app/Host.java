package src.app;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;

import org.json.simple.JSONObject;
import src.conf.Conf;
import src.util.EthManager;

import src.app.Connection;
import src.app.domain.Servidor;
import src.app.domain.Cliente;

public class Host{

	private Conf settings;

	private Servidor input;
	private Cliente output;

	public void start(){
		try{
			this.startSettings();
			this.startService();

		} catch(IOException e){
			this.printErrorMessage(e);
		}
	}

	private void printErrorMessage(Exception e){
			System.out.println("Sorry, we can not accomplish your desire");
			System.out.println("\n¯\\_(ツ)_/¯\n");

			// Sort random
			System.out.println("Try with Genie Lamp");
			System.out.println("Try to find the Blue Fairy, Pinocchio");
	}

	private void startSettings(){
		System.out.println("Starting settings....");
		this.settings = Conf.getInstance();
		if(this.settings.isServer()){
			this.input = new Servidor(settings.getAddress(), settings.getPort());
		}else if(this.settings.isClient()){
			this.output = new Cliente(settings.getAddress(), settings.getPort());
		}

	}

	private void startService() throws IOException {
		
		System.out.println("###################################");
		System.out.println("####### VIRTUAL HOST STARTED ######");
		System.out.println("###################################");

		if(this.settings.isServer()){
			new Thread(input).start();
		}else if(this.settings.isClient()){
			this.hostTerminal();
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        public void run() {
            down();
        }
    }, "Shutdown-Server"));
	}

	public void down() {
		System.out.println();
		System.out.println("###################################");
		System.out.println("####### VIRTUAL HOST EXITED! ######");
		System.out.println("###################################");
		System.out.println();
	}

	public void hostTerminal() throws IOException{
		System.out.println("\ntype help for help:\n");
		Scanner teclado = new Scanner(System.in);
		String msg;

		while(true){
			System.out.print("$ ");
			msg = teclado.nextLine();
			// REFATORAR: usar interpretador para captura o ip de origem e destino
			if(msg.trim().equals("connect")){
				output.setDst("192.168.0.10", 12345);
				System.out.println("### Connected");
				output.run();
				
			}else if(msg.trim().equals("exit")){
				break;
			}
		}

	}

	/*
	* #######################################################
	* # FUNÇÕES DE CONSULTA
	* #######################################################
	*/

}