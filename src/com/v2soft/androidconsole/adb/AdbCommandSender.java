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
package com.v2soft.androidconsole.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * @author V.Shcryabets (vshcryabets@gmail.com)
 *
 */
public class AdbCommandSender {
	private String mAdbPath;
	private String mAction;
	private AndroidDevice mDevice;
	
	/**
	 * Construct new device command sender
	 * @param adbPath
	 * @param device
	 */
	protected AdbCommandSender(String adbPath, AndroidDevice device) {
		if ( adbPath == null ) throw new NullPointerException();
		if ( device == null ) throw new NullPointerException();
		mAdbPath = adbPath;
		setDevice(device);
	}
	
	/**
	 * Set Intent action
	 * @param action intent action
	 */
	public void setAction(String action) {
		if ( action == null ) throw new NullPointerException();
		mAction = action;
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

	public AndroidDevice getDevice() {
		return mDevice;
	}

	protected void setDevice(AndroidDevice mDevice) {
		this.mDevice = mDevice;
	}
}
