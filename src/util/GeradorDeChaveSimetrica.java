package src.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import src.conf.Conf;

public class GeradorDeChaveSimetrica {

    /*
    * #########################################################
    * # MAIN
    * #########################################################
    */
    public static void main(String[] args) throws IOException {
        GeradorDeChaveSimetrica gerador = new GeradorDeChaveSimetrica();
        SecretKey chaveAES = gerador.secretKey;
        gerador.SalvaSenha();
    }  

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    KeyGenerator keyGen;  
    Key k; 
    SecretKey secretKey;
    String chaveEmString;
    public GeradorDeChaveSimetrica(){
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // for example
            secretKey = keyGen.generateKey();
            System.out.print("Chave secreta criada: ");
            System.out.println(bytesToHex(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GeradorDeChaveSimetrica.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    public String getChaveStr(){
        return bytesToHex(secretKey.getEncoded());
    }

    public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    public void SalvaSenha() throws IOException
    {
        Conf.getInstance().setKey(bytesToHex(secretKey.getEncoded()));
        Conf.getInstance().saveVariables();
    } 
}
