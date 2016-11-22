package src.util;

import src.util.ExecuteShellCommand;

public class EthManager {
	private static final String ETH_CHK_MSG = "ifconfig | grep -o eth0:{ethNum}";
	private static final String ETH_CHK_MSG_ASW = "eth0:{ethNum}";

	private static final String ETH_CRT_MSG = "sudo ifconfig eth0:{ethNum} {ip_mask}";
	private static final String ETH_RMV_MSG = "sudo ifconfig eth0:{ethNum} down";

	public static boolean existsInterface(int ethNum){
		String ethNumStr = String.valueOf(ethNum);
		String msg = ETH_CHK_MSG.replace("{ethNum}", ethNumStr);
		String asw = ETH_CHK_MSG_ASW.replace("{ethNum}", ethNumStr);

		return ExecuteShellCommand.executeCommand(msg).trim().equals(asw);
	}

	public static void createNewInterface(int ethNum, String ip_mask){
		String msg = ETH_CRT_MSG.replace("{ethNum}", String.valueOf(ethNum));
		msg = msg.replace("{ip_mask}", String.valueOf(ip_mask));

		System.out.println("This command needs root mode");
		System.out.println("$ "+msg);
		ExecuteShellCommand.executeCommand(msg);
	}

	public static void removeInterface(int ethNum){
		String msg = ETH_CRT_MSG.replace("{ethNum}", String.valueOf(ethNum));

		System.out.println("This command needs root mode");
		System.out.println("$ "+msg);
		ExecuteShellCommand.executeCommand(msg);
	}	

}