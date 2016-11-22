package src.util;

// Referência: https://www.mkyong.com/java/how-to-execute-shell-command-from-java/
// REFATORAR: usar estratégia para executar em outros Sistemas Operacionais
import java.io.*;

public class ExecuteShellCommand {

	private static String printStream(InputStream in) throws IOException {
		String result = "";
	  BufferedReader is = new BufferedReader(new InputStreamReader(in));
 		String line;
    while ((line = is.readLine()) != null) {
      result += line;
    }
    return result;
	}
	public static String executeCommand(String command) {
		String result = "";
	  try {
      Runtime rt = Runtime.getRuntime();
      String[] cmd = {"/bin/sh", "-c", command};
      Process proc = rt.exec(cmd);
      result = printStream(proc.getInputStream());
      /*
      * REFATORAR: Pensar numa forma de exibir o erro apenas quando acontecer
      System.out.println("Error : ");
      printStream(proc.getErrorStream());
      */
	  } catch (Exception ex) {
      ex.printStackTrace();
    }

    return result;
	}
}