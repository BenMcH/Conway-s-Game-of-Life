package com.tycoon177.conway.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.tycoon177.conway.utils.Settings;

public class GridSizeChanger {

	public static void showGridSizeChanger() {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		frame.add(new JLabel("Width:"), c);
		c.gridx = 1;
		JTextField width = new JTextField(Settings.GRID_WIDTH + "");
		frame.add(width, c);

		c.gridy = 1;
		c.gridx = 0;
		frame.add(new JLabel("Height:"), c);
		c.gridx = 1;
		JTextField height = new JTextField(Settings.GRID_HEIGHT + "");
		frame.add(height, c);

		c.gridy = 2;
		c.gridx = 0;
		JButton cancel = new JButton("Cancel");
		frame.add(cancel, c);
		JButton set = new JButton("Ok");
		c.gridx = 1;
		frame.add(set, c);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});

		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int w = Integer.parseInt(width.getText());
					int h = Integer.parseInt(height.getText());
					ConwaysGame game = new ConwaysGame(w, h);
					ConwayGUI.gui.setNewBoard(game);
					frame.setVisible(false);
					frame.dispose();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Only whole numbers are allowed!", "Error!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
