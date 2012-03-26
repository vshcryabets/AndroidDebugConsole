package com.v2soft.androidconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final Object COMMAND_EXIT = "exit";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String adbPath = "/home/mrco/work/android-sdk/platform-tools/adb";
		String action = "com.v2soft.SecureJabber.ACTION_DIRECT_COMMAND";
		final InputStreamReader converter = new InputStreamReader(System.in);
		final BufferedReader in = new BufferedReader(converter);
		final AdbCommandSender commandSender = new AdbCommandSender(adbPath, action);
		String command = null;
		while ( !COMMAND_EXIT.equals(command)) {
			System.out.print(">");
			try {
				command = in.readLine();
				if ( command.length() > 0 ) {
					commandSender.send(command);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

}
