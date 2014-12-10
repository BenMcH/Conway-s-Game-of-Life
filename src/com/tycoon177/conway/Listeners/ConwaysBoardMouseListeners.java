package com.tycoon177.conway.listeners;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.GUI.controlpanels.PaintBrushEditor;
import com.tycoon177.conway.utils.Settings;

public class ConwaysBoardMouseListeners implements MouseListener,
		MouseMotionListener {
	private boolean isClicked = false, leftClick = false;
	private int halfPt = ((PaintBrushEditor.SIZE - 1) / 2), i, j;
	private int[][] paintbrush;
	private Point point = new Point();
	private int[][] stamp = new int[1][1];

	@Override
	public void mouseDragged(MouseEvent e) {
		j = e.getY() / Settings.CELL_SIZE;
		i = e.getX() / Settings.CELL_SIZE;
		paintbrush = PaintBrushEditor.getPaintBrush();
		changeCell(leftClick ? 1 : 0);

		Rectangle r = new Rectangle(point);
		r.add(e.getPoint());

		// ConwayGUI.game.drawSelectionRect(r);
		if (Controls.tools.getTool().equals("select"))
			ConwayGUI.game.drawSelectionRect(r);
		else
			ConwayGUI.game.stopDrawingSelection();
		ConwayGUI.gui.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
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
		String tool = Controls.tools.getTool();
		if (tool.equals("select")) {
			point = e.getPoint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isClicked = false;
		if (Controls.tools.getTool().equals("select")) {
			int width = (int) Math.abs(Math.floor(point.getX())
					/ Settings.CELL_SIZE - arg0.getX() / Settings.CELL_SIZE);
			int height = (int) Math.abs(Math.floor(point.getY())
					/ Settings.CELL_SIZE - arg0.getY() / Settings.CELL_SIZE);
			stamp = new int[height][width];
			for (int i = 0; i < stamp.length; i++) {
				for (int j = 0; j < stamp[0].length; j++) {
					int y = ((int) point.getY() / Settings.CELL_SIZE) + j;
					int x = ((int) point.getX() / Settings.CELL_SIZE) + i;
					if (y >= 0 && y < Settings.GRID_HEIGHT && x >= 0
							&& x < Settings.GRID_WIDTH)

						stamp[i][j] = ConwayGUI.game.board[y][x];
				}
			}
			ConwayGUI.game.stopDrawingSelection();
			ConwayGUI.game.repaint();
		}
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
		String tool = Controls.tools.getTool();
		if (tool.equals("draw")) {
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
		} else if (tool.equals("select")) {

		} else if (tool.equals("paste")) {
			int halfH = stamp.length / 2, halfW = stamp[0].length / 2;
			for (int y = 0; y < stamp.length; y++)
				for (int x = 0; x < stamp[0].length; x++) {
					int x1 = i + x - halfW;
					int y1 = j + y - halfH;

					if (leftClick)

						if (stamp[y][x] == 1)
							addCell(x1, y1);
						else
							removeCell(x1, y1);

				}
		}
		ConwayGUI.game.updateStats();
	}
}
