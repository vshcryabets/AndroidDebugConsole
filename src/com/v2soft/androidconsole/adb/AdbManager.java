package com.v2soft.androidconsole.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdbManager {
	private String mAdbPath;
	
	public AdbManager(String adbPath) {
		if ( adbPath == null ) throw new NullPointerException();
		mAdbPath = adbPath;
	}
	
	public List<String> getDevices() throws IOException {
		List<String> result = new ArrayList<String>();
		// Execute a command with an argument that contains a space 
		String[] commands = new String[]{mAdbPath, "devices"};
		Process child = Runtime.getRuntime().exec(commands);
		BufferedReader standardIn = new BufferedReader(
		        new InputStreamReader(child.getInputStream())); 
        BufferedReader errorIn = new BufferedReader(
                new InputStreamReader(child.getErrorStream())); 
        String line="";
        boolean first = true;
        while((line=standardIn.readLine()) != null) {
        	if ( first) {
        		first = false;
        	} else {
        		String[] columns = line.split("\t");
                result.add(columns[0]);
        	}
        }
		return result;
	}
	
	public AdbCommandSender getCommandSender(String device) {
		return new AdbCommandSender(mAdbPath, device);
	}
}
