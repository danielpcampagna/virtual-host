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
		this.input = new Servidor(settings.getPort(), settings.getAddress());

	}

	private void startService() throws IOException {
		System.out.println("Starting service....");
		new Thread(input).start();
		this.waitingConnections();

		System.out.println("###################################");
		System.out.println("###### VIRTUAL ROUTER STARTED #####");
		System.out.println("###################################");

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        public void run() {
            down();
        }
    }, "Shutdown-Server"));
	}

	public void down() {

	}

	public void waitingConnections() throws IOException{
		System.out.println("\nwaiting connections:\n");
				

	}

	/*
	* #######################################################
	* # FUNÇÕES DE CONSULTA
	* #######################################################
	*/

}