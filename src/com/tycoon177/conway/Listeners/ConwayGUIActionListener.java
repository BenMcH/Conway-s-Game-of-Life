package com.tycoon177.conway.listeners;

import java.awt.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tycoon177.conway.GUI.GridSizeChanger;
import com.tycoon177.conway.GUI.PreferencesWindow;

public class ConwayGUIActionListener implements EventHandler<javafx.event.ActionEvent> {
	private JFrame frame;
	private JPanel pane;
	private JTextField number;
	private JButton set;
	private JButton cancel;

	public ConwayGUIActionListener() {
	}

	public void actionPerformed(ActionEvent event) {
		frame = new JFrame("");
		JLabel label = new JLabel("Size of grid (No. of cells");
		pane = new JPanel();
		pane.add(label);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		number = new JTextField();
		number.setOpaque(true);
		pane.add(number);
		set = new JButton("Apply");
		cancel = new JButton("Cancel");
		pane.add(set);
		pane.add(cancel);
		frame.setContentPane(pane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}

	@Override
	public void handle(javafx.event.ActionEvent event) {
		String cas = (((MenuItem)event.getSource()).getText());
		
		switch (cas) {
		case ("Change Board Sizee"):
			GridSizeChanger.showGridSizeChanger();
			break;
		case ("Preferences"):
			PreferencesWindow.showPreferences();
			break;
		}
	}

}
