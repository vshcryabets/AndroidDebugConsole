package com.v2soft.androidconsole;

import java.io.IOException;

public class AdbCommandSender {
	private String mAdbPath;
	private String mAction;
	
	public AdbCommandSender(String adbPath, String action) {
		if ( adbPath == null ) throw new NullPointerException();
		if ( action == null ) throw new NullPointerException();
		mAdbPath = adbPath;
		mAction = action;
	}
	public void send(String command) throws IOException {
		if ( command == null ) throw new NullPointerException();
		// Execute a command with an argument that contains a space 
		String amParams = String.format("am broadcast -a %s -e command \"%s\"", mAction, command);
		String[] commands = new String[]{mAdbPath, "shell", amParams};
		Process child = Runtime.getRuntime().exec(commands);
	}
}
