package com.tycoon177.conway;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConwaysGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548436324523302187L;
	private int[][] board;
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
				int j = e.getY() / 8;
				int i = e.getX() / 8;
				int[][] paintbrush = PaintBrushEditor.getPaintBrush();
				for (int y = 0; y < 3; y++)
					for (int x = 0; x < 3; x++) {
						if (paintbrush[x][y] == 1) {
							if (i + x - 1 >= 0 && i + x - 1 < board.length)
								if (j + y - 1 >= 0 && j + y - 1 < board[0].length)
									board[i + x - 1][j + y - 1] = 1;
						}
					}
				if (i > board.length - 1 || j > board[0].length - 1)
					return;
//				board[i][j] = e.getButton() == MouseEvent.BUTTON1 ? 1 : 0;
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
		for (int i = 0; i < board.length * 8; i += 8) {
			g.drawRect(0, i, board.length * 8, 0);
		}
		for (int i = 0; i < board[0].length * 8; i += 8) {
			g.drawRect(i, 0, 0, board[0].length * 8);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] > 0) {
					g.fillRect(8 * i, 8 * j, 8, 8);
				}
			}
		}
		g.drawRect(0, 0, 800, 800);
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
}