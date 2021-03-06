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
	public static final String HOST = "host";
	public static final String HOST_ADDRESS = "address";
	public static final String HOST_PORT = "port";

	public static final String MODE = "mode";
	public static final String KEY = "key";

	// Valores padrões para MODE
	public static final String MODE_SERVER = "server";
	public static final String MODE_CLIENT = "client";

	// Valores das chaves
	private String address;
	private String mode;
	private String key;
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
		System.out.println(RelativePath.getPath(confFile));
		JSONDAO dao = new JSONDAO(RelativePath.getPath(confFile));
		this.conf = dao.read();
		/*
		* ##########################################
		* # USE ESTA REGIÃO PARA INSTANCIAR
		* # AS VARIÁVEIS DO ARQUIVO DE CONFIGURAÇÃO
		* ##########################################
		*/
		JSONObject host = ((JSONObject)conf.get(HOST));
		this.address = (String) host.get(HOST_ADDRESS);
		this.port = (int)(long)(host.get(HOST_PORT));

		this.mode = (String)(conf.get(MODE));
		this.key = (String)(conf.get(KEY));

		//this.checkSettings();

		/*
		* ##########################################
		* # FIM
		* ##########################################
		*/
	}

	public void saveVariables(){
		JSONDAO dao = new JSONDAO(RelativePath.getPath(confFile));
		/*
		* ##########################################
		* # USE ESTA REGIÃO PARA SALVAR
		* # AS VARIÁVEIS DO ARQUIVO DE CONFIGURAÇÃO
		* ##########################################
		*/
		JSONObject confJSON = new JSONObject();

		JSONObject hostJSON = new JSONObject();
		hostJSON.put(HOST_ADDRESS, this.address);
		hostJSON.put(HOST_PORT, new Integer(this.port));

		confJSON.put(HOST, hostJSON);
		confJSON.put(MODE, this.mode);
		confJSON.put(KEY, this.key);

		//this.checkSettings();

		dao.save(confJSON);

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

	public String getKey(){
		return this.key;
	}

	public boolean isClient(){
		return this.mode.trim().equals(MODE_CLIENT);
	}

	public boolean isServer(){
		return this.mode.trim().equals(MODE_SERVER);
	}

	/*
	* #######################################################
	* # FUNÇÕES DE ATUALIZAÇÃO
	* #######################################################
	*/
	public String setKey(String key){
		return this.key = key;
	}
}