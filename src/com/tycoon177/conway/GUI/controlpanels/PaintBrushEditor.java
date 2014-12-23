package com.tycoon177.conway.GUI.controlpanels;


public class PaintBrushEditor {

	public static final int SIZE = 1;
	public static int[][] paintbrush = new int[SIZE][SIZE];

	public PaintBrushEditor() {
		paintbrush[0][0] = 1;
	}

	public static int[][] getPaintBrush() {
		return paintbrush;
	}

}
