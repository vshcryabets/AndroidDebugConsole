package com.v2soft.androidconsole.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.v2soft.androidconsole.adb.AndroidDevice;
import com.v2soft.androidconsole.adb.Intent;

public class SendIntentDialog 
extends JDialog implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_CLOSE = "close";
	private static final String ACTION_SEND = "send";

	private AndroidDevice mDevice;
	private JTextField mAction;
	private JTextField mCategory;
	private JTextField mComponent;

	public SendIntentDialog(Frame frame, AndroidDevice device) {
		super(frame, "Send Intent", true);
//		super("Select device");
		if ( device == null ) throw new NullPointerException();
		mDevice = device;
		buildUI();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		pack();
//		setSize(400,400);
	}

	private void buildUI() {
		JPanel vbox = new JPanel();
		vbox.setLayout(new BoxLayout(vbox, BoxLayout.PAGE_AXIS));
		// input fields 
		// 1. action
		vbox.add(new JLabel("Action:"));
		mAction = new JTextField();
		vbox.add(mAction);
		// 2. component
		vbox.add(new JLabel("Component:"));
		mComponent = new JTextField();
		vbox.add(mComponent);
		// 3. category
		vbox.add(new JLabel("Category:"));
		mCategory = new JTextField();
		vbox.add(mCategory);
		// 4. two column list of extras
		vbox.add(Box.createGlue()); // Box.createRigidArea(new Dimension(0, Integer.MAX_VALUE)));
		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createGlue());

		JButton cancel = new JButton("Send");
		cancel.setActionCommand(ACTION_SEND);
		cancel.addActionListener(this);
		hbox.add(cancel);
		JButton close = new JButton("Close");
		close.setActionCommand(ACTION_CLOSE);
		close.addActionListener(this);
		hbox.add(close);


		vbox.add(hbox);
		vbox.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		getContentPane().add(vbox);

	}

	public void showDialog() {
		setEnabled(true);
		setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		closeDialog();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String command = arg0.getActionCommand();
			if ( ACTION_CLOSE.equals(command)) {
				closeDialog();
			} else if ( ACTION_SEND.equals(command)) {
				sendIntent();
			} 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void sendIntent() throws IOException {
		final Intent intent = new Intent(mAction.getText(), null, mComponent.getText(), mCategory.getText());
		mDevice.startActivity(intent);
	}

	public void closeDialog() {
		setVisible(false);
		this.dispose();
		//		WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		//		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);		
	}
}
