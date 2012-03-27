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
package com.v2soft.androidconsole.ui.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.v2soft.androidconsole.adb.AdbManager;
import com.v2soft.androidconsole.ui.views.DirectCommandView;
import com.v2soft.androidconsole.ui.views.LogcatView;

/**
 * 
 * @author V.Shcryabets (vshcryabets@gmail.com)
 *
 */
public class ConsoleWindow
implements WindowListener {
    private JFrame mFrame;
    private LogcatView mLogcatView;
    private DirectCommandView mCommandBox;
    private JMenuBar mMenuBar;
    private AdbManager mManager;

    public ConsoleWindow(String adbPath) throws IOException {
    	mManager = new AdbManager(adbPath);
        mFrame = new JFrame("AndroidDebugConsole");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mLogcatView = new LogcatView(adbPath);
        mCommandBox = new DirectCommandView(mManager);

        mFrame.getContentPane().add(mLogcatView, BorderLayout.CENTER);
        mFrame.getContentPane().add(mCommandBox, BorderLayout.SOUTH);
        mFrame.setJMenuBar(createMenu());
        mFrame.addWindowListener(this);
        mFrame.setSize(480, 320);
        mFrame.setVisible(true);
    }
    private JMenuBar createMenu() {
    	mMenuBar = new JMenuBar();
    	JMenu menu = new JMenu("Preferences");
    	JMenuItem menuItem = new JMenuItem("Select device");
    	menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					selectDevice();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    	menu.add(menuItem);
    	mMenuBar.add(menu);
    	return mMenuBar;
	}
	//-------------------------------------------------------------------------
    // Dialogs
    //-------------------------------------------------------------------------
    private void selectDevice() throws IOException {
    	List<String> devicesList = mManager.getDevices();
    	String[] devices = devicesList.toArray(new String[devicesList.size()]);
    	String s = (String)JOptionPane.showInputDialog(
    	                    mFrame,
    	                    "Complete the sentence:\n"
    	                    + "\"Green eggs and...\"",
    	                    "Customized Dialog",
    	                    JOptionPane.PLAIN_MESSAGE,
    	                    null,
    	                    devices,
    	                    "ham");
//
//    	//If a string was returned, say so.
//    	if ((s != null) && (s.length() > 0)) {
//    	    setLabel("Green eggs and... " + s + "!");
//    	    return;
//    	}
//
//    	//If you're here, the return value was null/empty.
//    	setLabel("Come on, finish the sentence!");    	
    }
    //-------------------------------------------------------------------------
    // Window Listener
    //-------------------------------------------------------------------------
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
