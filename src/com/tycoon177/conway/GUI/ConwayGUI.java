package com.tycoon177.conway.GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		Settings.loadSettings();
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
	}

}
