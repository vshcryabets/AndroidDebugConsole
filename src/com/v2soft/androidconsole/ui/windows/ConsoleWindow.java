package com.v2soft.androidconsole.ui.windows;


import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JFrame;

import com.v2soft.androidconsole.ui.views.DirectCommandView;
import com.v2soft.androidconsole.ui.views.LogcatView;

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
