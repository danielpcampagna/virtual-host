package src.app.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TerminalCommand {
	
	private static final String CMD_CONNECT = "^connect (((\\d){1,3}\\.){3}(\\d){1,3})\\:(\\d{2,5})$";
	private static final String CMD_EXIT 		= "^exit$";
	private static final String CMD_HELP 		= "^help$";
	private static final String CMD_SEND 		= "^send ([/].*)*$";

	private static final int ALIAS_CONNECT 	= 0;
	private static final int ALIAS_EXIT 		= 1;
	private static final int ALIAS_HELP 		= 2;
	private static final int ALIAS_SEND 		= 3;

	public boolean run(String cmd){
		if()
		stackCmds.push(cmd);
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

			String address = m.group(0);
			int port = Integer.parseInt(m.group(0));
			connect(address, port);

		}else if(cmd.matches(CMD_EXIT)){
			exit();

		}else if(cmd.matches(CMD_HELP)){
			msgHelp();

		}else if(cmd.matches(CMD_SEND)){
			r = Pattern.compile(CMD_CONNECT);
    	m = r.matcher(cmd);

			String msg = m.group(0);
			send(msg);

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
			"connect <ip>:<port>  Use para conectar-se ao host <ip>\n"+
			"                      na porta <port>.\n" +
			"\n" +
			"exit                 Use para desconectar ou desligar\n" +
			"                      o seu host.\n" +
			"\n" +
			"help                 Use para ver essa mensagem.\n" +
			"\n" +
			"send <file>          Use para enviar o arquivo <file> \n" +
			"                      para o servidor.\n"
		);

		return result;
	}
}