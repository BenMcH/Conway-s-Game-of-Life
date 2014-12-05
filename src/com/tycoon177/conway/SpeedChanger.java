package com.tycoon177.conway;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

public class SpeedChanger extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7600146103234076673L;
	JFormattedTextField number;

	public SpeedChanger(Controls controls) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		NumberFormatter formatter = new NumberFormatter(
				NumberFormat.getInstance());
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setMaximum(144);
		formatter.setCommitsOnValidEdit(true);
		formatter.setAllowsInvalid(false);
		number = new JFormattedTextField(formatter);
		number.setOpaque(true);
		number.setText("20");
		this.add(new JLabel("Target FPS"));
		this.add(number);
		number.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				controls.setMaxUpdates((Integer) number.getValue());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				controls.setMaxUpdates((Integer) number.getValue());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				controls.setMaxUpdates((Integer) number.getValue());
			}

		});
	}

//	@Override
//	public Dimension getMaximumSize() {
//		return (new Dimension(200, 35));
//	}

	public void setDisabled(boolean b) {
		number.setEditable(b);
		if(!b){
			number.setBackground(new Color(150,150,150));
		}
		else
			number.setBackground(Color.white);
	}
}
