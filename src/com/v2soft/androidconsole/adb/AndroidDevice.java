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
 * @author vshcryabets@gmail.com
 *
 */
public class AndroidDevice {
	private String mSerial;
	private AdbManager mAdbManager;
	
	protected AndroidDevice(String serial, AdbManager manager) {
		mAdbManager = manager;
		setSerial(serial);
	}

	public String getSerial() {
		return mSerial;
	}

	protected void setSerial(String mSerial) {
		this.mSerial = mSerial;
	}
	
	@Override
	public int hashCode() {
		return mSerial.hashCode();
	}
	
	@Override
	public String toString() {
		return mSerial;
	}
	
	/**
	 * Start activity
	 * @throws IOException 
	 */
	public void startActivity(Intent intent) throws IOException {
		if ( intent == null ) throw new NullPointerException();
		// Execute a command with an argument that contains a space
		String amParams = String.format("am start %s", intent.getAdbString());
		String[] commands = new String[]{mAdbManager.getAdbPath(), "shell", amParams};
		Process child = Runtime.getRuntime().exec(commands);
        return;
	}
	
	/**
	 * Start service
	 */
	public void startService(Intent intent) {
		
	}
	
	/**
	 * Send broadcast intent
	 */
	public void sendBroadcastIntent(Intent intent) {
		
	}
}
