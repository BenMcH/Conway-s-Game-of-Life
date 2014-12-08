package com.tycoon177.conway;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class ConwayGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1233273878556674435L;
	public static ConwayGUI gui;
	public static ConwaysGame game;
	JScrollPane pane;
	public ConwayGUI(ConwaysGame g) {
		super();
		setSize(920, 830);
		setTitle("Conways Game of Life, made by tycoon177 - Stopped");
		//Set the UI to look more like the system.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		pane = new JScrollPane(g);
		add(setupMenu(), BorderLayout.NORTH);
		game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(pane, BorderLayout.CENTER);
		//Make the right panel
		Controls controls = new Controls();
		this.add(controls, BorderLayout.EAST);
		//setResizable(false);
		repaint();
		revalidate();
		gui = this;
		Settings.loadSettings();
	}
	
	public JMenuBar setupMenu(){
		JMenuBar bar = new JMenuBar();
		JMenu edit = new JMenu("Edit");
		JMenu tools = new JMenu("Tools");
		JMenu preferences = new JMenu("Preferences");
		ConwayGUIActionListener actionListener = new ConwayGUIActionListener();
		bar.add(edit);
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
		bar.add(tools);
		return bar;
	}
	

	public static void main(String[] args) {
		ConwaysGame game = new ConwaysGame(100);
		ConwayGUI gui = new ConwayGUI(game);
		gui.setVisible(true);
	}

	
	public void setNewBoard(ConwaysGame g){
		pane.setViewportView(g);
		ConwayGUI.game = g;
		revalidate();
		repaint();
	}

}
