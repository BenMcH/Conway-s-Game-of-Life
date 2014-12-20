package com.tycoon177.conway.GUI.controlpanels;

import javax.swing.JPanel;

public class PaintBrushEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571809177616025341L;
	public static final int SIZE = 1;
	public static int[][] paintbrush = new int[SIZE][SIZE];

	public PaintBrushEditor() {
		paintbrush[0][0] = 1;
	}

	public static int[][] getPaintBrush() {
		return paintbrush;
	}

}
