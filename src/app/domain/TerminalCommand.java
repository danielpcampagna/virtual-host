package src.app.domain;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TerminalCommand {
	
	private static final String CMD_CONNECT = "^connect (?<address>((\\d){1,3}\\.){3}(\\d){1,3})\\:(?<port>\\d{2,5})$";
	private static final String CMD_EXIT 		= "^exit|close|quit$";
	private static final String CMD_HELP 		= "^help$";
	private static final String CMD_SEND 		= "^send (?<msg>[\\w\\d\\s\\.\\-\\_\\/\\\\]+)$";

	private static final int ALIAS_CONNECT 	= 0;
	private static final int ALIAS_EXIT 		= 1;
	private static final int ALIAS_HELP 		= 2;
	private static final int ALIAS_SEND 		= 3;

	public void start(){
		System.out.println("\ntype help for help:\n");
		Scanner teclado = new Scanner(System.in);
		String msg;

		while(true){
			System.out.print("$ ");
			msg = teclado.nextLine();
			// REFATORAR: Pensar numa forma de reusar os comandos quando apertas as setas
			if(!run(msg)){
				System.out.println("ERRO: Comando inexistente.");
				System.out.println(" Digite \'help\'");
				System.out.println("");
			}

			if(msg.matches(CMD_EXIT)){
				break;
			}
		}
	}

	public boolean run(String cmd){
		return interprete(cmd);
	}

	private boolean interprete(String cmd){
		// execute regex
		boolean result = true;
		// Create a Pattern object
    Pattern r;
    // Now create matcher object.
    Matcher m;

		if(cmd.matches(CMD_CONNECT)){
      r = Pattern.compile(CMD_CONNECT);
    	m = r.matcher(cmd);

    	if(m.find()){
				String address = m.group("address");
				int port = Integer.parseInt(m.group("port"));
				connect(address, port);
			}else{
				result = false;
			}


		}else if(cmd.matches(CMD_EXIT)){
			exit();

		}else if(cmd.matches(CMD_HELP)){
			System.out.println(msgHelp());

		}else if(cmd.matches(CMD_SEND)){
			r = Pattern.compile(CMD_SEND);
    	m = r.matcher(cmd);

    	if(m.find()){
				String msg = m.group("msg");
				send(msg);
			}else{
				result = false;
			}

		}else{

			result = false;
		}

		return result;
	}

	protected abstract void connect(String address, int port);
	protected abstract void exit();
	protected abstract void send(String msg);

	private String msgHelp(){
		String	result;

		result = (
			"\n"+
			"connect <ip>:<port>  Use para conectar-se ao host <ip>\n"+
			"                      na porta <port>.\n" +
			"\n" +
			"exit                 Use para desconectar ou desligar\n" +
			"                      o seu host.\n" +
			"\n" +
			"help                 Use para ver essa mensagem.\n" +
			"\n" +
			"send <file>          Use para enviar o arquivo <file> \n" +
			"                      para o servidor.\n"+
			"\n"
		);

		return result;
	}
}