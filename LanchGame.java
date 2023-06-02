import java.util.*;

class TicTacToe {
	static char[][] board;

	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}

	static void initBoard() {
		//
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	static void displayBoard() {
		System.out.println("---------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " |  ");
			}
			System.out.println();
			System.out.println("---------------");

		}
		// System.out.println("----------------");

	}

	static void playMark(int row, int col, char mark) {

		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = mark;
		} else {
			System.out.println("invalid position");
		}

	}

	// check whether coloum win b/w players
	static boolean checkCWin() {
		for (int j = 0; j <= 2; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	// check whether row win b/w players
	static boolean checkRWin() {
		for (int i = 0; i <= 2; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	// check diganals match whether any player won
	static boolean checkDiagWin() {
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	// chech whether game will draw b/w players
	static boolean checkDarw() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;

	}

}

abstract class Player {

	String name;
	char mark;

	abstract void makeMove();

	boolean isVaildeMove(int row, int col) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			if (TicTacToe.board[row][col] == ' ') {

				return true;
			}
		}
		return false;

	}
}

class HumanPlayer extends Player {
	public HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("enter row and col");
			row = scan.nextInt();
			col = scan.nextInt();
		} while (!isVaildeMove(row, col));

		TicTacToe.playMark(row, col, mark);

	}

}

class AiPlayer extends Player {

	// Ai player can play randommly
	AiPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		} while (!isVaildeMove(row, col));
		TicTacToe.playMark(row, col, mark);
	}
}

public class LanchGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToe t = new TicTacToe();
		HumanPlayer p1 = new HumanPlayer("sekhar", 'x');
		AiPlayer p2 = new AiPlayer("ai", 'O');
		Player cp;
		cp = p1;

		while (true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.displayBoard();
			if (TicTacToe.checkCWin() || TicTacToe.checkRWin() || TicTacToe.checkDiagWin()) {
				System.out.println(cp.name + "has won");
				break;

			} else if (TicTacToe.checkDarw()) {
				System.out.println("Game is a draw");
				break;

			} else {
				if (cp == p1) {
					cp = p2;
				} else {
					cp = p1;
				}
			}
		}

	}

}
