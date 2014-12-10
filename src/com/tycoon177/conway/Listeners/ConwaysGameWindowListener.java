package com.tycoon177.conway.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.tycoon177.conway.utils.Settings;

public class ConwaysGameWindowListener implements WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		Settings s = new Settings();
		try {
			FileOutputStream fos = new FileOutputStream(Settings.settingsFile);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(s);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
