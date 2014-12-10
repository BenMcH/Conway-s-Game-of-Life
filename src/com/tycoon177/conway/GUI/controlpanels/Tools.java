package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Tools extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1366202218697271516L;
	
	@SuppressWarnings("unused")
	private JRadioButton draw, select, paste;
	
	public Tools(){
		super(new GridBagLayout());
		setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Tools"));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		
	}
	
	
	
}
