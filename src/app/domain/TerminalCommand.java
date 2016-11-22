public abstract class TerminalCommand {
	
	private static final String CMD_CONNECT = "^connect ((\d){3,3}\.){2,2}(\d){3,3}\:(\d){2,5}$"
	private static final String CMD_HELP = "^help$"

	public String run(String cmd){
		return execute(interprete(cmd));
	}

	private String interprete(String cmd){

	}

	abstract String execute(String cmd);

	public String msgHelp(){
		String	result;

		result = (
			"connect <ip>:<port>  Use para conectar-se a um host"
		);
		
		return result;
	}
}