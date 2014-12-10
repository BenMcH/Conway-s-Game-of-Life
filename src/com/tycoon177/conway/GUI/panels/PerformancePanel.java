package com.tycoon177.conway.GUI.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import com.tycoon177.conway.GUI.controlpanels.Controls;

public class PerformancePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2139493821612459073L;
	JFormattedTextField number;

	public PerformancePanel() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		NumberFormatter formatter = new NumberFormatter(
				NumberFormat.getInstance());
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		// formatter.setMaximum(144);
		formatter.setCommitsOnValidEdit(true);
		formatter.setAllowsInvalid(false);
		number = new JFormattedTextField(formatter);
		number.setOpaque(true);
		number.setText("20");
		c.weightx = 0.1;
		c.weighty = .01;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(new JLabel("Target FPS"), c);
		c.gridy++;
		add(number, c);
		c.gridx = 0;
		c.gridy++;
		c.ipadx = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.weightx = 1;
		add(new JPanel(), c);
		number.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				Controls.listen.setMaxUpdates((Integer) number.getValue());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Controls.listen.setMaxUpdates((Integer) number.getValue());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				Controls.listen.setMaxUpdates((Integer) number.getValue());
			}

		});
	}

}
