package com.tycoon177.conway.GUI.controlpanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.utils.Settings;

public class Sliders extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008161015292605195L;
	JSlider zoom, fps;
	private int min = 1, max = 50, value = 8;

	public Sliders() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		zoom = new JSlider(min, max, value);
		fps = new JSlider(1, 300, 20);
		zoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Settings.CELL_SIZE = zoom.getValue();
				ConwayGUI.game.repaint();
			}
		});
		fps.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Controls.listen.setMaxUpdates(fps.getValue());
			}
		});
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		add(new JLabel("Zoom"), c);
		c.gridx = 1;
		add(zoom, c);
		c.gridy = 1;
		c.gridx = 0;
		add(new JLabel("Game Speed"), c);
		c.gridx = 1;
		add(fps, c);
		setBorder(new TitledBorder(new LineBorder(Color.black), "Game Control"));
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

	}

	public void setFPSDisabled(boolean b) {
		fps.setEnabled(!b);
	}
}
