package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.tycoon177.conway.GUI.ConwayGUI;

public class GenerationSkip extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4279175558720524893L;

	public GenerationSkip() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		final JTextField f = new JTextField("0");
		JButton b = new JButton("Skip");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ConwayGUI.game.skipToGeneration(Integer.parseInt(f
							.getText()));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Please Enter a Whole Number!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(new JLabel("Desired Generation:"),c);
		c.gridy = 1;
		add(f,c);
		c.gridy = 2;
		add(b,c);
		setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Generation Skip"));
	}
}
