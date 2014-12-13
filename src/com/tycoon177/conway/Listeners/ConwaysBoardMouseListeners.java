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
	private boolean leftClick = false, inside = false;
	private int halfPt = ((PaintBrushEditor.SIZE - 1) / 2), i, j;
	private int[][] paintbrush;
	private Point point;
	private int[][] stamp;

	@Override
	public void mouseDragged(MouseEvent e) {
		j = e.getY() / Settings.CELL_SIZE;
		i = e.getX() / Settings.CELL_SIZE;
		paintbrush = PaintBrushEditor.getPaintBrush();
		changeCell(leftClick ? 1 : 0);
		if (Controls.tools.getTool().equals("select"))
			if (this.point == null)
				point = getPointOnScreen(e);
			else {
				Rectangle r = new Rectangle(point);
				r.add(getPointOnScreen(e));
				ConwayGUI.game.drawSelectionRect(r);
			}
		ConwayGUI.gui.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (inside) {
			point = getPointOnScreen(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// System.out.println(getCell(arg0));
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
		leftClick = e.getButton() == MouseEvent.BUTTON1;
		j = e.getY() / Settings.CELL_SIZE;
		i = e.getX() / Settings.CELL_SIZE;
		paintbrush = PaintBrushEditor.getPaintBrush();
		changeCell(leftClick ? 1 : 0);
		ConwayGUI.gui.repaint();
		// String tool = Controls.tools.getTool();
		this.point = getPointOnScreen(e);
		ConwayGUI.game.stopDrawingSelection();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		// ConwayGUI.game.stopDrawingSelection();
		ConwayGUI.game.repaint();
		if (Controls.tools.getTool().equals("select")) {
			Point endPoint = getCell(getPointOnScreen(arg0));
			if (endPoint.x < 0)
				endPoint.x = 0;
			if (endPoint.x >= ConwayGUI.game.board.length)
				endPoint.x = ConwayGUI.game.board.length - 1;

			Rectangle r = new Rectangle(getCell(point));
			r.add(endPoint);

			stamp = new int[r.height][r.width];

			for (int i = 0; i < stamp[0].length; i++) {
				for (int j = 0; j < stamp.length; j++) {
					stamp[j][i] = ConwayGUI.game.board[(int) (r.getMinX() + i)][(int) (r
							.getMinY() + j)];
				}
			}
			this.point = null;
			ConwayGUI.game.stopDrawingSelection();
		}

	}

	public void printStamp() {
		for (int[] row : stamp) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
		System.out.println();
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
		} else if (tool.equals("paste")) {
			if (stamp == null)
				return;
			int halfw = stamp.length % 2 == 0 ? stamp.length / 2
					: (stamp.length - 1) / 2;
			int halfh = stamp[0].length % 2 == 0 ? stamp[0].length / 2
					: (stamp[0].length - 1) / 2;
			for (int y = 0; y < stamp.length; y++) {
				for (int x = 0; x < stamp[0].length; x++) {
					int x1 = i + x - halfw;
					int y1 = j + y - halfh;
					if (stamp[y][x] == 1) {
						addCell(x1, y1);
					}
				}
			}
		}
		ConwayGUI.game.updateStats();
	}

	public Point getPointOnScreen(MouseEvent e) {
		int x = (int) (Math.round((double) e.getPoint().getX()
				/ Settings.CELL_SIZE));
		int y = (int) (Math.round((double) e.getPoint().getY()
				/ Settings.CELL_SIZE));
		x *= Settings.CELL_SIZE;
		y *= Settings.CELL_SIZE;
		if(x > ConwayGUI.game.getPreferredSize().width)
			x = ConwayGUI.game.getPreferredSize().width;
		if(y > ConwayGUI.game.getPreferredSize().height)
			y = ConwayGUI.game.getPreferredSize().height;
		if(x < 0)
			x = 0;
		if(y < 0)
			y = 0;
		Point p = new Point(x, y);
		return p;
	}

	public Point getPointOnScreenFromCell(Point p) {
		if (p.x >= Settings.GRID_WIDTH)
			p.x = Settings.GRID_WIDTH - 1;
		if (p.x < 0)
			p.x = 0;
		p.x *= Settings.CELL_SIZE;
		p.y *= Settings.CELL_SIZE;

		return p;
	}

	public Point getCell(Point p) {
		int x = p.x / Settings.CELL_SIZE;
		int y = p.y / Settings.CELL_SIZE;

		return new Point(x, y);
	}

}
