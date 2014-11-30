package com.tycoon177.conway;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class ConwayGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1233273878556674435L;
	public static ConwayGUI gui;
	public static ConwaysGame game;
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
		game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(game, BorderLayout.CENTER);
		//Make the right panel
		Controls controls = new Controls(game);
		this.add(controls, BorderLayout.EAST);
		setResizable(false);
		repaint();
		revalidate();
		gui = this;
	}

	public static void main(String[] args) {
		ConwaysGame game = new ConwaysGame(100);
		ConwayGUI gui = new ConwayGUI(game);
		// game.randomizeBoard();
		gui.setVisible(true);
	}

}
