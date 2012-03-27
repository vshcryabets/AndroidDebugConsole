package com.v2soft.androidconsole;

import java.io.IOException;

import com.v2soft.androidconsole.ui.windows.ConsoleWindow;

public class Main {
	private static final String COMMAND_ACTION = "-a";

    /**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	    if ( args.length == 0 ) {
            showUsage();
            return;
        }
        int pos = 0;
        String action = null;
        while ( pos < args.length ) {
            String command = args[pos];
            if ( COMMAND_ACTION.equals(command)) {
                pos++;
                action = args[pos];
            }
            pos++;
        }
        if ( action == null ) {
            showUsage();
            return;
        }
	    String androidHome = System.getenv("ANDROID_HOME");
	    if ( androidHome == null ) throw new NullPointerException("ANDROID_HOME envar is null");
		String adbPath = System.getenv("ANDROID_HOME")+"/platform-tools/adb";
		new ConsoleWindow(adbPath, action);
	}
	
    private static void showUsage() {
        System.out.println("Usage:");
        System.out.println("\t{AndroidDebugConsole} -a action [-sdk sdk_path] ");
    }	
}
