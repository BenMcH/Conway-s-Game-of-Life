package com.tycoon177.conway.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		// Load settings
		Settings settings = null;
		try {
			Settings.settingsFile.getParentFile().mkdirs();
			Settings.settingsFile.createNewFile();
			FileInputStream fis = new FileInputStream(Settings.settingsFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			settings = (Settings) ois.readObject();
			ois.close();
		} catch (Exception e) {

		}

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				Settings s = new Settings();
				try {
					FileOutputStream fos = new FileOutputStream(s.settingsFile);
					ObjectOutputStream os = new ObjectOutputStream(fos);
					os.writeObject(s);
					os.flush();
					os.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
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
