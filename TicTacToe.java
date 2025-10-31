import java.util.Scanner;

public class TicTacToe {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY = ' ';
    private static char[][] board = new char[3][3];
    private static char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBoard();
        System.out.println("🎮 Welcome to Tic Tac Toe!");
        System.out.println("Player X and Player O take turns.");

        boolean gameEnded = false;
        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] and column[1-3]): ");
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("🏆 Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("😮 It's a draw!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("❌ Invalid move! Try again.");
            }
        }
        sc.close();
    }

    // Initialize empty board
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the board
    private static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Validate move
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY);
    }

    // Check win condition
    private static boolean checkWin(char player) {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }

        // Diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    // Check draw
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }
}
