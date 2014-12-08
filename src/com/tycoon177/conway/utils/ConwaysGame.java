package com.tycoon177.conway.utils;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tycoon177.conway.GUI.PaintBrushEditor;

public class ConwaysGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548436324523302187L;
	private int[][] board;
	private boolean showGrid = true;
	@SuppressWarnings("serial")
	private ArrayList<Integer> stayAlive = new ArrayList<Integer>() {
		{
			add(2);
			add(3);
		}
	};
	@SuppressWarnings("serial")
	private ArrayList<Integer> comeAlive = new ArrayList<Integer>() {
		{
			add(3);
		}
	};

	public ConwaysGame() {
		board = new int[10][10];
		randomizeBoard();
		init();
	}

	public ConwaysGame(int[][] b) {
		board = b;
		init();
	}

	public ConwaysGame(int size) {
		board = new int[size][size];
		init();
	}

	public void init() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int j = e.getY() / Settings.CELL_SIZE;
				int i = e.getX() / Settings.CELL_SIZE;
				int[][] paintbrush = PaintBrushEditor.getPaintBrush();
				int halfPt = ((PaintBrushEditor.SIZE - 1) / 2);

				for (int y = 0; y < PaintBrushEditor.SIZE; y++)
					for (int x = 0; x < PaintBrushEditor.SIZE; x++) {
						int x1 = i + x - halfPt;
						int y1 = j + y - halfPt;
						if (paintbrush[x][y] == 1) {
							if (x1 >= 0 && x1 < board.length)
								if (y1 >= 0 && y1 < board[0].length) {
									board[x1][y1] = e.getButton() == MouseEvent.BUTTON1 ? 1
											: 0;

								}
						}
					}
				if (i > board.length - 1 || j > board[0].length - 1)
					return;
				// board[i][j] = e.getButton() == MouseEvent.BUTTON1 ? 1 : 0;
				repaint();
				// System.out.println(i + " " + j);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.setDoubleBuffered(true);
		// this.setBorder(new LineBorder(Color.BLACK));
	}

	public void randomizeBoard() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Random().nextInt(2);
			}
		// step();
		// step();
		// step();
	}

	public int getNeighbors(int i, int j) {
		int neighbors = 0;
		neighbors = 0;
		// minus
		if (i - 1 >= 0) {
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1] > 0)
					neighbors++;

				if (board[i][j - 1] > 0)
					neighbors++;
			}
			if (board[i - 1][j] > 0)
				neighbors++;
		} else if (j - 1 >= 0) {
			if (board[i][j - 1] > 0)
				neighbors++;
		}

		// plus
		if (i + 1 < board.length) {
			if (j + 1 < board[0].length) {
				if (board[i + 1][j + 1] > 0)
					neighbors++;
				if (board[i][j + 1] > 0)
					neighbors++;
			}
			if (board[i + 1][j] > 0)
				neighbors++;
		} else if (j + 1 < board.length) {
			if (board[i][j + 1] > 0)
				neighbors++;
		}

		// plus minus
		if (i + 1 < board.length) {
			if (j - 1 >= 0) {
				if (board[i + 1][j - 1] > 0)
					neighbors++;
			}
		}
		if (i - 1 >= 0) {
			if (j + 1 < board.length) {
				if (board[i - 1][j + 1] > 0)
					neighbors++;
			}

		}
		return neighbors;
	}

	public void step() {
		int[][] newBoard = new int[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int neighbors = getNeighbors(i, j);
				if (board[i][j] > 0) {
					if (stayAlive.contains(neighbors))
						newBoard[i][j] = 1;
					else
						newBoard[i][j] = 0;
				} else if (board[i][j] == 0)
					if (comeAlive.contains(neighbors)) {
						newBoard[i][j] = 1;
					}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = newBoard[i][j];
			}
		}
	}

	public int[][] getBoard() {
		return board;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Settings.BACKGROUND_COLOR);
		g2.fillRect(0, 0, this.getPreferredSize().width,
				this.getPreferredSize().height);
		g2.setColor(Settings.GRID_COLOR);
		Stroke stroke = g2.getStroke();
		g2.setStroke(new BasicStroke());
		if (showGrid) {
			g2.drawRect(0, 0, board.length * Settings.CELL_SIZE, board.length
					* Settings.CELL_SIZE);
			for (int i = 0; i < board.length * Settings.CELL_SIZE; i += Settings.CELL_SIZE) {
				g2.drawRect(0, i, board.length * Settings.CELL_SIZE, 0);
				g2.drawRect(i, 0, 0, board.length * Settings.CELL_SIZE);
			}
		}
		g2.setStroke(stroke);
		g2.setColor(Settings.CELL_COLOR);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] > 0) {
					g2.fillRect(Settings.CELL_SIZE * i, Settings.CELL_SIZE * j,
							Settings.CELL_SIZE, Settings.CELL_SIZE);
				}
			}
		}
	}

	public void clearBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
				repaint();
			}
		}
	}

	public void setStayAlive(int... num) {
		stayAlive.removeAll(stayAlive);
		for (int num1 : num)
			stayAlive.add(num1);
	}

	public void setComeAlive(int... num) {
		comeAlive.removeAll(comeAlive);
		for (int num1 : num) {
			comeAlive.add(num1);
		}
	}

	/**
	 * Parse numbers in a csv style string into the numbers themselves
	 * 
	 * @param stayAlive
	 * @param comeAlive
	 */
	public void setRules(String stayAlive, String comeAlive) {
		String[] rules = stayAlive.split(",");
		String[] comeRules = comeAlive.split(",");
		int[] stayNums = new int[rules.length];
		int[] comeNums = new int[comeRules.length];
		for (int i = 0; i < stayNums.length; i++) {
			try {
				if (rules[i].trim().equals("")) {
					stayNums[i] = -1;
				} else
					stayNums[i] = Integer.parseInt(rules[i].trim());
			} catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(null,
								"Error changing rules. Make sure to enter whole numbers separated by commas.");
				return;
			}
		}
		for (int i = 0; i < comeNums.length; i++) {
			try {
				if (comeRules[i].trim().equals(""))
					comeNums[i] = -1;
				else
					comeNums[i] = Integer.parseInt(comeRules[i]);
			} catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(null,
								"Error changing rules. Make sure to enter whole numbers separated by commas.");
				return;
			}
		}
		setStayAlive(stayNums);
		setComeAlive(comeNums);
	}

	public void changeCellSize(int size) {
		Settings.CELL_SIZE = size;
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(board.length * Settings.CELL_SIZE, board.length
				* Settings.CELL_SIZE);
	}
}