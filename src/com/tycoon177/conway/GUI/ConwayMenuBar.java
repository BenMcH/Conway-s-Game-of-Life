package com.tycoon177.conway.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.tycoon177.conway.Listeners.ConwayGUIActionListener;

public class ConwayMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3460583898347916149L;

	public ConwayMenuBar() {
		super();
		JMenu edit = new JMenu("Edit");
		JMenu tools = new JMenu("Tools");
		JMenu preferences = new JMenu("Preferences");
		ConwayGUIActionListener actionListener = new ConwayGUIActionListener();
		add(edit);
		JMenuItem changeSize = new JMenuItem("Change Board Size");
		changeSize.setActionCommand("ChangeSize");
		changeSize.addActionListener(actionListener);
		edit.add(changeSize);
		JMenuItem changeCellSize = new JMenuItem("Change Cell Size");
		changeCellSize.setActionCommand("ChangeCellSize");
		changeCellSize.addActionListener(actionListener);
		edit.add(changeCellSize);
		JMenuItem color = new JMenuItem("Change Cell Color");
		color.setActionCommand("Color");
		color.addActionListener(actionListener);
		
		preferences.add(color);
		edit.add(preferences);
		add(tools);
	}

}
