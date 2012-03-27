package com.v2soft.androidconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdbCommandSender {
	private String mAdbPath;
	private String mAction;
	private String mDeviceSerial;
	
	public AdbCommandSender(String adbPath, String action, String deviceSerial) {
		if ( adbPath == null ) throw new NullPointerException();
		if ( action == null ) throw new NullPointerException();
		mAdbPath = adbPath;
		mAction = action;
		mDeviceSerial = deviceSerial;
	}
	public String send(String command) throws IOException {
		if ( command == null ) throw new NullPointerException();
		// Execute a command with an argument that contains a space 
		String amParams = String.format("am broadcast -a %s -e command \"%s\"", mAction, command);
		String[] commands = new String[]{mAdbPath, "shell", amParams};
		Process child = Runtime.getRuntime().exec(commands);
		BufferedReader standardIn = new BufferedReader(
		        new InputStreamReader(child.getInputStream())); 
        BufferedReader errorIn = new BufferedReader(
                new InputStreamReader(child.getErrorStream())); 
        String line="";
        StringBuilder result = new StringBuilder();
        while((line=standardIn.readLine()) != null) {
                result.append(line);
                result.append("\n");
        }
        while((line=errorIn.readLine()) != null) {
            result.append(line);
            result.append("\n");
        }
        return result.toString();
	}
}
