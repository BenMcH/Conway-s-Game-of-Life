package com.tycoon177.conway.listeners;

import java.awt.Point;
import java.awt.Rectangle;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import com.tycoon177.conway.GUI.ConwayGUI;
import com.tycoon177.conway.GUI.controlpanels.Controls;
import com.tycoon177.conway.GUI.controlpanels.PaintBrushEditor;
import com.tycoon177.conway.utils.Settings;

public class ConwaysBoardMouseListeners {
	private boolean leftClick = false, inside = true;
	private int halfPt = ((PaintBrushEditor.SIZE - 1) / 2);
	private Point point, cell, ij;
	private int[][] stamp;

	public void mouseDragged(MouseEvent e) {
		if (inside) {
			ij = getCell(e.getX(), e.getY());
			cell = getCell(new Point((int) e.getX(), (int) e.getY()));
			changeCell(leftClick ? 1 : 0);
			if (Controls.tools.getTool().equalsIgnoreCase("select"))
				if (this.point == null)
					point = getPointOnScreen(e);
				else {
					Rectangle r = new Rectangle(point);
					r.add(getPointOnScreen(e));
					ConwayGUI.game.drawSelectionRect(r);
				}
			// ConwayGUI.gui.repaint();
		}
		e.consume();
	}

	public void mouseMoved(MouseEvent e) {
		if (inside) {
			point = getPointOnScreen(e);
			cell = getCell(new Point((int) e.getX(), (int) e.getY()));
			setStamp();
			// ConwayGUI.game.repaint();
		}
		e.consume();
	}

	private void setStamp() {
		if (inside) {
			if (Controls.tools.getTool().equalsIgnoreCase("paste")) {
				if (stamp == null)
					return;
				ConwayGUI.game.resetBoard();
				int halfw = 0, halfh = 0;
				halfh = stamp.length % 2 == 0 ? stamp.length - stamp.length / 2
						: stamp.length - (stamp.length + 1) / 2;

				halfw = stamp[0].length % 2 == 0 ? stamp[0].length
						- stamp[0].length / 2 : stamp[0].length
						- (stamp[0].length + 1) / 2;
				ConwayGUI.game.resetBoard();
				for (int y = 0; y < stamp.length; y++) {
					for (int x = 0; x < stamp[0].length; x++) {
						int x1 = (int) (cell.getX() + x - halfw);
						int y1 = (int) (cell.getY() + y - halfh);
						if (stamp[y][x] == 1) {
							setTempStamp(x1, y1);
						}
					}
				}
			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
		leftClick = e.getButton() == MouseButton.PRIMARY;
		ij = getCell(e.getX(), e.getY());
		cell = getCell(new Point((int) e.getX(), (int) e.getY()));
		changeCell(leftClick ? 1 : 0);
		// ConwayGUI.gui.repaint();
		// String tool = Controls.tools.getTool();
		this.point = getPointOnScreen(e);
		ConwayGUI.game.stopDrawingSelection();
	}

	public void mouseReleased(MouseEvent arg0) {
		if (inside) {
			// ConwayGUI.game.repaint();
			if (Controls.tools.getTool().equalsIgnoreCase("select")) {
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
						// stamp[j][i] = ConwayGUI.game.board[(int) (r.getMinX()
						// +
						// i)][(int) (r
						// .getMinY() + j)];
					}
				}
				this.point = null;
				ConwayGUI.game.stopDrawingSelection();
			}
			if (Controls.tools.getTool().equalsIgnoreCase("paste"))
				setStamp();
		}
	}

	/**
	 * prints the stamp for debugging purposes
	 */
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
				ConwayGUI.game.board[x][y].setAlive(true);
				repaint();
			}
	}

	private void removeCell(int x, int y) {
		if (x >= 0 && x < ConwayGUI.game.board.length)
			if (y >= 0 && y < ConwayGUI.game.board[0].length) {
				ConwayGUI.game.board[x][y].setAlive(false);
				repaint();
			}
	}

	/**
	 * Sets the ghost of a stamp onto the board.
	 * 
	 * @param x
	 * @param y
	 */
	private void setTempStamp(int x, int y) {
		if (x >= 0 && x < ConwayGUI.game.stamp.length)
			if (y >= 0 && y < ConwayGUI.game.stamp[0].length) {
				if (ConwayGUI.game.stamp[x][y] == 0)
					ConwayGUI.game.stamp[x][y] = 2;
				else
					ConwayGUI.game.stamp[x][y] = 3;
			}
	}

	private void changeCell(int val) {
		String tool = Controls.tools.getTool();
		if (tool.equalsIgnoreCase("draw")) {
			int x1 = ij.y - halfPt;
			int y1 = ij.x - halfPt;
			if (leftClick)
				addCell(x1, y1);
			else
				removeCell(x1, y1);

		} else if (tool.equalsIgnoreCase("paste")) {
			if (stamp == null)
				return;
			int halfw = 0, halfh = 0;
			halfh = stamp.length % 2 == 0 ? stamp.length - stamp.length / 2
					: stamp.length - (stamp.length + 1) / 2;
			halfw = stamp[0].length % 2 == 0 ? stamp[0].length
					- stamp[0].length / 2 : stamp[0].length
					- (stamp[0].length + 1) / 2;
			for (int y = 0; y < stamp.length; y++) {
				int y1 = (int) (cell.getY() + y - halfh);
				for (int x = 0; x < stamp[0].length; x++) {
					int x1 = (int) (cell.getX() + x - halfw);
					if (stamp[y][x] == 1) {
						addCell(x1, y1);
					}
				}
			}
		}
		ConwayGUI.game.updateStats();
		repaint();
	}

	private Point getPointOnScreen(MouseEvent e) {
		
		int x = (int) (Math.round((double) e.getX() / Settings.CELL_SIZE));
		int y = (int) (Math.round((double) e.getY() / Settings.CELL_SIZE));
		x *= Settings.CELL_SIZE;
		y *= Settings.CELL_SIZE;
		if (x > ConwayGUI.game.getPreferredSize().width)
			x = ConwayGUI.game.getPreferredSize().width;
		if (y > ConwayGUI.game.getPreferredSize().height)
			y = ConwayGUI.game.getPreferredSize().height;
		if (x < 0)
			x = 0;
		if (y < 0)
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

	private Point getCell(Point p) {
		int x = (p.x) / Settings.CELL_SIZE;
		int y = (p.y) / Settings.CELL_SIZE;
		return new Point(x, y);
	}

	public Point getCell(double x, double y) {
		return getCell(new Point((int) x, (int) y));
	}

	private void repaint() {
		ConwayGUI.game.drawCells();
	}

}
