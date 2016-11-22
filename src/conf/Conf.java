package src.conf;

import src.util.JSONDAO;
import src.util.RelativePath;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public class Conf{
	private static Conf instance;

	private static final String confFile = "conf/default.json";

	// Chaves do arquivo de configuração
	public static final String ADDRESS = "address";
	public static final String PORT = "port";

	// Valores das chaves
	private String address;
	private int port;

	private JSONObject conf;

	private Conf(){
		this.start();
	}

	private void start(){
		this.buildVariables();

	}

	public static Conf getInstance(){
		if(instance == null){
			Conf.instance = new Conf();
		}
		return Conf.instance;
	}

	private void buildVariables(){
		JSONDAO dao = new JSONDAO(RelativePath.getPath(confFile));
		this.conf = dao.read();
		/*
		* ##########################################
		* # USE ESTA REGIÃO PARA INSTANCIAR
		* # AS VARIÁVEIS DO ARQUIVO DE CONFIGURAÇÃO
		* ##########################################
		*/
		this.address = (String) conf.get(ADDRESS);
		this.port = (int)(long)(conf.get(PORT));

		//this.checkSettings();

		/*
		* ##########################################
		* # FIM
		* ##########################################
		*/
	}

	/*
	* Checar se a o endereço e a porta estão disponíveis
	* 
	*/
	/*
	private void checkSettings(){

		try{
			// REFATORAR: Checar se todas os alias estão definidos na interface
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	*/

	/*
	* #######################################################
	* # FUNÇÕES DE CONSULTA
	* #######################################################
	*/
	public int getPort(){
		return this.port;
	}

	public String getAddress(){
		return this.address;
	}

}