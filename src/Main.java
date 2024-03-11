import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player 1, what's your name? ");
        String firstPlayer = scanner.nextLine();
        System.out.print("Player 2, what's your name? ");
        String secondPlayer = scanner.nextLine();

        int rowNumber = 3;
        int columnNumber = 3;
        char[][] board = new char[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            for(int j = 0; j < columnNumber; j++) {
                board[i][j] = '-';
            }
        }

        boolean isPlayer1 = true;
        boolean gameEnded = false;

        while(!gameEnded) {
            drawBoard(board);

            char symbol = ' ';
            if(isPlayer1)
                symbol = 'X';
            else
                symbol = 'O';

            if(isPlayer1)
                System.out.println(firstPlayer + "'s turn (X)");
            else
                System.out.println(secondPlayer + "'s turn (O)");

            int row = 0;
            int column = 0;

            while(true) {
                System.out.println("Enter a row (1, 2 or 3): ");
                row = scanner.nextInt();
                System.out.println("Enter a column (1, 2 or 3): ");
                column = scanner.nextInt();

                if (row < 0 || column < 0 || row > 2 || column > 2) {
                    System.out.println("Values are out of bounds!");
                } else if (board[row - 1][column - 1] != '-') {
                    System.out.println("Someone has already made a move!");
                } else
                    break;
            }

            board[row - 1][column - 1] = symbol;

            if(hasWon(board) == 'X') {
                System.out.println(firstPlayer + " has won!");
                gameEnded = true;
            } else if (hasWon(board) == 'O') {
                System.out.println(secondPlayer + " has won!");
                gameEnded = true;
            } else {
                if (isFull(board))
                    System.out.println("It's a tie!");
                else
                    isPlayer1 = !isPlayer1;
            }
        }
        drawBoard(board);
    }

    public static void drawBoard(char[][] board)
    {
        for (char[] array : board) {
            for(char element : array) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        //row
        for(int i = 0; i < 3; i++){
            if(board[i][0] == board[i][1] &&
                    board[i][0] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }

        //column
        for(int j = 0; j < 3; j++){
            if(board[0][j] == board[1][j] &&
                    board[0][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j];
            }
        }

        //diagonals
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }

        if(board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        //nobody
        return '-';
    }

    public static boolean isFull(char[][] board) {
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
}
