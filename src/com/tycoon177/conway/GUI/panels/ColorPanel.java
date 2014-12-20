package com.tycoon177.conway.GUI.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.utils.Settings;

public class ColorPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3925023413100098405L;
	SquareComp bg = new SquareComp(Settings.BACKGROUND_COLOR);
	SquareComp cl = new SquareComp(Settings.CELL_COLOR);
	SquareComp gr = new SquareComp(Settings.GRID_COLOR);

	public ColorPanel() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// JLabel label = new JLabel("Current Color:");
		JButton backgroundButton = new JButton("Change Background Color");
		backgroundButton.setActionCommand("bg");
		backgroundButton.addActionListener(this);

		JButton cellButton = new JButton("Change Cell Color");
		cellButton.setActionCommand("cell");
		cellButton.addActionListener(this);
		JButton gridButton = new JButton("Change Grid Color");
		gridButton.setActionCommand("grid");
		gridButton.addActionListener(this);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.weightx = 0.01;
		c.weighty = .001;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy++;
		c.insets = new Insets(5, 0, 0, 0);
		add(backgroundButton, c);
		c.gridx++;
		add(bg, c);
		c.gridy++;
		c.gridx--;
		add(cellButton, c);
		c.gridx++;
		add(cl, c);
		c.gridy++;
		c.gridx--;
		add(gridButton, c);
		c.gridx++;
		add(gr, c);
		c.gridy++;
		c.weighty = 1;
		add(new JPanel(), c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "bg":
			Settings.BACKGROUND_COLOR = JColorChooser.showDialog(null,
					"Choose a new background color", Settings.BACKGROUND_COLOR);
			break;
		case "cell":
			Settings.CELL_COLOR = JColorChooser.showDialog(null,
					"Choose a new cell color", Settings.CELL_COLOR);
			break;
		case "grid":
			Settings.GRID_COLOR = JColorChooser.showDialog(null,
					"Choose a new grid color", Settings.GRID_COLOR);
			break;
		}
		bg.changeColor(Settings.BACKGROUND_COLOR);
		gr.changeColor(Settings.GRID_COLOR);
		cl.changeColor(Settings.CELL_COLOR);

	}

	private class SquareComp extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2516940945966763697L;

		public SquareComp(Color c) {
			super();
			this.setOpaque(true);
			this.setBackground(c);
			this.setBorder(new LineBorder(Color.black));
		}

		public void changeColor(Color c) {
			this.setBackground(c);
			repaint();
		}
	}
}
