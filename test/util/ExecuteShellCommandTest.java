// REFTORAR: usar JUnit

import java.io.File;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ExecuteShellCommandTest {

  public static void main(String[] args){
		test01_creatingEth01();
	}

	public static void test01_creatingPath(){
		String cmd = "mkdir test01.tst";
		File f = new File("test01.tst");

		System.out.println("$ "+cmd);
		System.out.println("Exists: " + f.exists());

		executeCommand(cmd);

		System.out.println("Exists: " + f.exists());


	}

	public static void test01_creatingEth01(){
		String oracle = "ifconfig | grep -o eth0:1";
		String cmd = "sudo ifconfig eth0:1 192.168.1.1";

		System.out.println("$ "+oracle);
		System.out.println("$ "+executeCommand(oracle));

		System.out.println("$ "+cmd);
		System.out.println("$ "+executeCommand(cmd));

		System.out.println("$ "+oracle);
		System.out.println("$ "+executeCommand(oracle));


	}

	public static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
}