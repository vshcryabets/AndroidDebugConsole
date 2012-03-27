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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;

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

    public ConsoleWindow(String adbPath, String action) throws IOException {
        mFrame = new JFrame("AndroidDebugConsole");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mLogcatView = new LogcatView(adbPath);
        mCommandBox = new DirectCommandView(adbPath, action);

        mFrame.getContentPane().add(mLogcatView, BorderLayout.CENTER);
        mFrame.getContentPane().add(mCommandBox, BorderLayout.SOUTH);
        mFrame.addWindowListener(this);
        mFrame.setSize(480, 320);
        mFrame.setVisible(true);

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
