package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Tools extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1366202218697271516L;

	private JRadioButton draw, select, paste;
	public static final int DRAW = 0, SELECT = 1, PASTE = 2;
	ButtonGroup group;

	public Tools() {
		super(new GridLayout(3, 1));
		group = new ButtonGroup();
		draw = new JRadioButton("Draw");
		draw.setActionCommand("draw");
		select = new JRadioButton("Select");
		select.setActionCommand("select");
		paste = new JRadioButton("Paste");
		paste.setActionCommand("paste");
		setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Tools"));
		draw.setSelected(true);
		group.add(draw);
		group.add(select);
		group.add(paste);
		add(draw);
		add(select);
		add(paste);
	}

	public String getTool() {
		for (Enumeration<AbstractButton> buttons = group.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton b = buttons.nextElement();
			if (b.isSelected())
				return b.getActionCommand();
		}
		return "";
	}

}
