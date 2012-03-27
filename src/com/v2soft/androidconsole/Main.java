/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package com.v2soft.androidconsole;

import java.io.IOException;

import com.v2soft.androidconsole.ui.windows.ConsoleWindow;
/**
 * 
 * @author V.Shcryabets (vshcryabets@gmail.com)
 *
 */
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
