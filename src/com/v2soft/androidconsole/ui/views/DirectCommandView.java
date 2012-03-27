package com.v2soft.androidconsole.ui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import com.v2soft.androidconsole.AdbCommandSender;

public class DirectCommandView 
    extends JTextField {
    private static final long serialVersionUID = 1L;
    private AdbCommandSender mCommandSender;
    private List<String> mHistory;
    private int mHistoryPosition;
    
    public DirectCommandView(String adbPath, String action) {
        super();
        mHistory = new ArrayList<String>();
        mCommandSender = new AdbCommandSender(adbPath, action, null);
        addActionListener(mListener);
        addKeyListener(mKeyListener);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mHistoryPosition = 0;
    }
    
    private KeyListener mKeyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {
            if ( e.getKeyCode() == KeyEvent.VK_UP) {
                mHistoryPosition--;
                setCommandFromHistory(mHistoryPosition);
            } else if ( e.getKeyCode() == KeyEvent.VK_DOWN) {
                mHistoryPosition++;
                setCommandFromHistory(mHistoryPosition);
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {}
    };
    
    private ActionListener mListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                mCommandSender.send(command);
                mHistory.add(command);
                mHistoryPosition = mHistory.size();
                DirectCommandView.this.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };

    protected void setCommandFromHistory(int historyPosition) {
        if ( historyPosition < 0 ) return;
        if ( historyPosition >= mHistory.size()) return;
        setText(mHistory.get(historyPosition));
    }
}
