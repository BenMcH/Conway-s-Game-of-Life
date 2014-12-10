package com.tycoon177.conway.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.PaintBrushEditor;
import com.tycoon177.conway.utils.Settings;

public class ConwaysBoardMouseListeners implements MouseListener,
		MouseMotionListener {
	private boolean isClicked = false, inside = false, leftClick = false;
	private int halfPt = ((PaintBrushEditor.SIZE - 1) / 2), i, j;
	private int[][] paintbrush;

	@Override
	public void mouseDragged(MouseEvent e) {
		if ((isClicked)) {
			j = e.getY() / Settings.CELL_SIZE;
			i = e.getX() / Settings.CELL_SIZE;
			paintbrush = PaintBrushEditor.getPaintBrush();
			changeCell(leftClick ? 1 : 0);
		}
		ConwayGUI.gui.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if ((isClicked && inside)) {
			if (e.getButton() == MouseEvent.BUTTON1)
				addCell(e.getX() / Settings.CELL_SIZE, e.getY()
						/ Settings.CELL_SIZE);
			else
				removeCell(e.getX() / Settings.CELL_SIZE, e.getY()
						/ Settings.CELL_SIZE);
		}
		ConwayGUI.gui.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		inside = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		inside = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isClicked = true;
		leftClick = e.getButton() == MouseEvent.BUTTON1;
		j = e.getY() / Settings.CELL_SIZE;
		i = e.getX() / Settings.CELL_SIZE;
		paintbrush = PaintBrushEditor.getPaintBrush();
		changeCell(leftClick ? 1 : 0);
		ConwayGUI.gui.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isClicked = false;
	}

	private void addCell(int x, int y) {

		if (x >= 0 && x < ConwayGUI.game.board.length)
			if (y >= 0 && y < ConwayGUI.game.board[0].length) {
				ConwayGUI.game.board[x][y] = 1;
			}
	}

	private void removeCell(int x, int y) {
		if (x >= 0 && x < ConwayGUI.game.board.length)
			if (y >= 0 && y < ConwayGUI.game.board[0].length) {
				ConwayGUI.game.board[x][y] = 0;
			}
	}

	private void changeCell(int val) {
		for (int y = 0; y < PaintBrushEditor.SIZE; y++)
			for (int x = 0; x < PaintBrushEditor.SIZE; x++) {
				int x1 = i + x - halfPt;
				int y1 = j + y - halfPt;
				if (paintbrush[y][x] == 1) {
					if (leftClick)
						addCell(x1, y1);
					else
						removeCell(x1, y1);

				}
			}
	}
}
