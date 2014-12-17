package com.tycoon177.conway.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import com.tycoon177.conway.utils.Settings;

public class Cell extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026585731793886548L;
	private boolean selected;
	private boolean highlighted;
	private boolean visited;
	private boolean changed;

	public boolean hasBeenVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		changed = true;
		visited = b;
	}

	public boolean isAlive() {
		return selected;
	}

	public void setAlive(boolean selected) {
		changed = true;
		this.selected = selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		changed = true;
		this.highlighted = highlighted;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Settings.CELL_SIZE, Settings.CELL_SIZE);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(Settings.CELL_SIZE, Settings.CELL_SIZE);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(Settings.CELL_SIZE, Settings.CELL_SIZE);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (!isAlive() && !isHighlighted() && !hasBeenVisited())
			return;
		changed = false;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Settings.CELL_COLOR);
		if (isAlive() || isHighlighted()) {
			if (isHighlighted() && !isAlive())
				g2.setColor(Color.LIGHT_GRAY);
		} else if (hasBeenVisited()) {
			g2.setColor(new Color(165, 253, 193));
		} else
			return;
		g2.fillRect(0, 0, Settings.CELL_SIZE, Settings.CELL_SIZE);
	}

	public boolean isChanged() {
		return changed;
	}
}
