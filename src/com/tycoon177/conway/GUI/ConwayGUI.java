package com.tycoon177.conway.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.listeners.ConwaysGameWindowListener;
import com.tycoon177.conway.utils.ConwaysGame;
import com.tycoon177.conway.utils.Settings;

public class ConwayGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1233273878556674435L;
	public static ConwayGUI gui;
	public static ConwaysGame game;
	public static Controls controls;
	JScrollPane pane;

	public ConwayGUI(ConwaysGame g) {
		super();
		// Load settings
		@SuppressWarnings("unused")
		Settings settings = null;
		try {
			Settings.settingsFile.getParentFile().mkdirs();
			Settings.settingsFile.createNewFile();
			FileInputStream fis = new FileInputStream(Settings.settingsFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			// settings = (Settings) ois.readObject();
			ois.close();
		} catch (Exception e) {

		}

		this.addWindowListener(new ConwaysGameWindowListener());
		ConwayGUI.controls = new Controls();
		setSize(920, 830);
		setTitle("Conways Game of Life, made by tycoon177 - Stopped");
		// Set the UI to look more like the system.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		pane = new JScrollPane(g);
		pane.getViewport().setAlignmentX(CENTER_ALIGNMENT);
		setJMenuBar(new ConwayMenuBar());
		game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().add(pane, BorderLayout.CENTER);
		// Make the right panel
		controls.setBorder(new TitledBorder(new LineBorder(Color.black),
				"Controls"));
		this.add(controls, BorderLayout.NORTH);
		// setResizable(false);
		repaint();
		revalidate();
		gui = this;
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		ConwaysGame game = new ConwaysGame(100);
		ConwayGUI gui = new ConwayGUI(game);
		gui.setVisible(true);
		// new PreferencesWindow().setVisible(true);;
	}

	public void setNewBoard(ConwaysGame g) {
		pane.setViewportView(g);
		ConwayGUI.game = g;
		revalidate();
		repaint();
		g.updateStats();
	}

}
