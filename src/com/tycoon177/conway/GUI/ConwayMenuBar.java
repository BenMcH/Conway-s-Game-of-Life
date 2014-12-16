package com.tycoon177.conway.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.tycoon177.conway.listeners.ConwayGUIActionListener;

public class ConwayMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3460583898347916149L;

	public ConwayMenuBar() {
		super();
		JMenu edit = new JMenu("Edit");
		ConwayGUIActionListener actionListener = new ConwayGUIActionListener();
		add(edit);
		JMenuItem changeSize = new JMenuItem("Change Board Size");
		changeSize.setActionCommand("ChangeSize");
		changeSize.addActionListener(actionListener);
		edit.add(changeSize);
		JMenuItem preferences = new JMenuItem("Preferences");
		preferences.setActionCommand("pref");
		preferences.addActionListener(actionListener);
		edit.add(preferences);
	}

}
