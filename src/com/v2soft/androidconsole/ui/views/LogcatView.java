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
package com.v2soft.androidconsole.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * 
 * @author V.Shcryabets (vshcryabets@gmail.com)
 *
 */
public class LogcatView 
extends JPanel 
implements Closeable {
    private static final long serialVersionUID = 1L;
    protected static final int MIN_STRING_COUNT = 3;
    private boolean isWorking = false;
    private Process mADBProcess;
    private BufferedReader mStdIn;
    private JTextArea mLogPane;

    public LogcatView(String adbPath) throws IOException {
        super();
        connectToDevice(null);
        // Execute a command with an argument that contains a space 
        String[] commands = new String[]{adbPath, "logcat"};
        mADBProcess = Runtime.getRuntime().exec(commands);
        mStdIn = new BufferedReader(
                new InputStreamReader(mADBProcess.getInputStream()));
        // create UI
        mLogPane = new JTextArea();
        JScrollPane listScroller = new JScrollPane(mLogPane);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(listScroller);
        this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        mLogPane.setBackground(Color.BLACK);
        mLogPane.setForeground(Color.WHITE);
        mLogPane.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    }

    private void connectToDevice(Object object) {
        Thread thread = new Thread(mlogcatRunnable, "Logcat");
        thread.start();
    }

    @Override
    public void close() throws IOException {
    }

    private Runnable mlogcatRunnable = new Runnable() {
        @Override
        public void run() {
            isWorking = true;
            // connect to adb
            while ( isWorking ) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    String line="";
                    while((line=mStdIn.readLine()) != null) {
                        mLogPane.append(line);
                        mLogPane.append("\n");
                        mLogPane.setCaretPosition(mLogPane.getDocument().getLength()); 
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
