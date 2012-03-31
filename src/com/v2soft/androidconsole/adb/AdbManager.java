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
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author vshcryabets@gmail.com
 *
 */
public class AdbManager {
	private String mAdbPath;
	private AndroidDevice mSelectedDevice;
	
	public AdbManager(String adbPath) {
		if ( adbPath == null ) throw new NullPointerException();
		mAdbPath = adbPath;
		selectDevice(null);
	}
	
	/**
	 * 
	 * @return List of available adb devices
	 * @throws IOException
	 */
	public List<AndroidDevice> getDevices() throws IOException {
		List<AndroidDevice> result = new ArrayList<AndroidDevice>();
		// Execute a command with an argument that contains a space 
		String[] commands = new String[]{mAdbPath, "devices"};
		Process child = Runtime.getRuntime().exec(commands);
		BufferedReader standardIn = new BufferedReader(
		        new InputStreamReader(child.getInputStream())); 
        String line="";
        boolean first = true;
        while((line=standardIn.readLine()) != null) {
        	if ( first) {
        		first = false;
        	} else {
        		String[] columns = line.split("\t");
                result.add(new AndroidDevice(columns[0], this));
        	}
        }
		return result;
	}
	
	public AdbCommandSender getCommandSender(AndroidDevice device) {
		return new AdbCommandSender(mAdbPath, device);
	}

	/**
	 * 
	 * @return selected device
	 */
	public AndroidDevice getSelectedDevice() {
		return mSelectedDevice;
	}

	public void selectDevice(AndroidDevice device) {
//		if ( device == null ) throw new NullPointerException();
		this.mSelectedDevice = device;
	}

	/**
	 * 
	 * @return path to adb tool
	 */
	public String getAdbPath() {
		return mAdbPath;
	}
}
