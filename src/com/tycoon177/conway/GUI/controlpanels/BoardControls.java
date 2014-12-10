package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class BoardControls extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7078312220036256031L;

	public BoardControls() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton run = new JButton("Toggle Run");
		run.setActionCommand("run");
		// Continuously Step
		run.addActionListener(Controls.listen);
		JButton clear = new JButton("Clear");
		JButton step = new JButton("Step");
		clear.setActionCommand("clear");
		JButton random = new JButton("Random");
		random.setActionCommand("random");
		clear.addActionListener(Controls.listen);
		random.addActionListener(Controls.listen);
		step.setActionCommand("step");
		step.addActionListener(Controls.listen);
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = .01;
		c.weighty = .01;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(step, c);
		c.gridy = 2;
		c.gridx = 0;
		add(run, c);
		c.gridy = 0;
		c.gridx = 1;
		add(clear, c);
		c.gridy = 2;
		c.gridx = 1;
		add(random, c);
		c.gridy = 0;
		c.gridx = 0;
		setBorder(new TitledBorder(new LineBorder(Color.black), "Board Control"));

	}

}
