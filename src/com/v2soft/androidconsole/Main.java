package com.v2soft.androidconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.v2soft.androidconsole.ui.windows.ConsoleWindow;

public class Main {

	private static final Object COMMAND_EXIT = "exit";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	    String androidHome = System.getenv("ANDROID_HOME");
	    if ( androidHome == null ) throw new NullPointerException("ANDROID_HOME envar is null");
		String adbPath = System.getenv("ANDROID_HOME")+"/platform-tools/adb";
		String action = "com.v2soft.SecureJabber.ACTION_DIRECT_COMMAND";
		new ConsoleWindow(adbPath, action);
		/*final InputStreamReader converter = new InputStreamReader(System.in);
		final BufferedReader in = new BufferedReader(converter);
		
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
		};*/
	}

}
