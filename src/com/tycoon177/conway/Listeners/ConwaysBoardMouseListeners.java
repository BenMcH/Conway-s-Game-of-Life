package com.tycoon177.conway.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.PaintBrushEditor;
import com.tycoon177.conway.utils.Settings;

public class ConwaysBoardMouseListeners implements MouseListener,
		MouseMotionListener {
	boolean isClicked = false, inside = false, leftClick = false;

	@Override
	public void mouseDragged(MouseEvent e) {

		if ((isClicked)) {
			if(leftClick)
				addCell(e.getX() / Settings.CELL_SIZE, e.getY()
						/ Settings.CELL_SIZE);
			else
				removeCell(e.getX()/Settings.CELL_SIZE, e.getY()/Settings.CELL_SIZE);
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
		int j = e.getY() / Settings.CELL_SIZE;
		int i = e.getX() / Settings.CELL_SIZE;
		int[][] paintbrush = PaintBrushEditor.getPaintBrush();
		int halfPt = ((PaintBrushEditor.SIZE - 1) / 2);

		for (int y = 0; y < PaintBrushEditor.SIZE; y++)
			for (int x = 0; x < PaintBrushEditor.SIZE; x++) {
				int x1 = i + x - halfPt;
				int y1 = j + y - halfPt;
				if (paintbrush[y][x] == 1) {

					if (e.getButton() == MouseEvent.BUTTON1)
						addCell(x1, y1);
					else
						removeCell(x1, y1);

				}
			}
		if (i > ConwayGUI.game.board.length - 1
				|| j > ConwayGUI.game.board[0].length - 1)
			return;
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

}
