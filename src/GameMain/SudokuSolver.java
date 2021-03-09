package GameMain;

import java.io.IOException;
import java.util.Arrays;

public class SudokuSolver {
	public static void main(String[] argrs){
		SudokuSolver s = new SudokuSolver(grid);
		System.out.println("Original:");
		System.out.println("");
		s.print();
		boolean sol = s.solve();
		if (sol == true) {
			System.out.println("Solved:");
			System.out.println("");
			s.print();
			// System.out.println(Arrays.deepToString(s.board));
		}
		else{
			System.out.println("Could Not Solve:");
			System.out.println("");
			s.print();
		}
	}

	private static int[][] grid = {
			{9,4,0,  6,7,0,  0,0,0},
			{2,0,0,  9,0,0,  0,0,0},
			{0,0,0,  0,0,0,  6,2,0},
			
			{0,5,0,  0,1,0,  8,0,6},
			{0,1,0,  0,0,3,  0,0,7},
			{0,0,0,  0,2,0,  0,0,0},
			
			{0,0,5,  7,0,0,  0,0,3},
			{0,0,9,  0,0,4,  0,0,8},
			{0,0,0,  0,0,0,  1,5,2}		
	};

	private void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					System.out.print("  ");
				}
				if (board[i][j] == 0) {
					System.out.print("*" + " ");
				} else
					System.out.print(board[i][j] + " ");
			}

			System.out.println("");
			if (i % 3 == 2) {
				System.out.println("");
			}
		}
	}

	public int[][] board;

	public SudokuSolver(int[][] board) {
		this.board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	private boolean inRow(int row, int number) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private boolean inCol(int col, int number) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == number) {
				return true;
			}
		}
		return false;
	}

	private boolean inBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;

		return false;
	}

	private boolean isValid(int row, int col, int number) {
		if (!inRow(row, number) && !inCol(col, number) && !inBox(row, col, number)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean solve() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					for (int m = 1; m <= 9; m++) {
						if (isValid(i, j, m)) {
							board[i][j] = m;

							if (solve()) {
								return true;
							} else {
								board[i][j] = 0;
							}
						}

					}
					return false;
				}
			}
		}

		return true;
	}

}
