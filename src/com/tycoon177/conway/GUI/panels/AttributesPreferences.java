package com.tycoon177.conway.GUI.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tycoon177.conway.GUI.ConwayGUI;

public class AttributesPreferences extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688560188213351396L;

	public AttributesPreferences() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel title = new JLabel("Rule Changer");
		JLabel stay = new JLabel("Number of neighbors that keep cells alive:");
		JLabel come = new JLabel(
				"Number of neighbors that bring the cell to life:");
		JTextField stayAlive = new JTextField("2, 3");
		stayAlive
				.setToolTipText("Enter numbers separated by commas that will keep the cells alive.");
		JTextField comeAlive = new JTextField("3");
		comeAlive.setAutoscrolls(true);
		comeAlive
				.setToolTipText("Enter numbers separated by commas that will bring the cells to life.");
		JButton set = new JButton("     Set   ");
		// pane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 1;
		c.weighty = .001f;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(title, c);
		c.gridy++;
		add(stay, c);
		c.gridx++;
		c.ipadx = 100;
		add(stayAlive, c);
		c.ipadx = 0;
		c.gridy++;
		c.gridx = 0;
		add(come, c);
		c.ipadx = 100;
		c.gridx++;
		c.gridwidth = 2;
		add(comeAlive, c);
		c.gridy++;
		c.gridx = 0;
		add(set, c);

		
		//Fill the unneeded space with a JPanel
		c.weighty = 1;
		c.gridy++;
		add(new JPanel(),c);
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConwayGUI.game.setRules(stayAlive.getText(),
						comeAlive.getText());
			}
		});
	}
}
