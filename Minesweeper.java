import java.util.Scanner;

public class Minesweeper {
    public static void printMinesweeperBoard(int [][]board) {

        // Print the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print((board[i][j] == 100 ? '@' : '.')+ " | ");
            }
            System.out.println();
            printSeparator(board[0].length);
        }
    }

    public static void printGameBoard(int [][]board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char res = '.';
                if(board[i][j] == 0){
                    res = '.';
                }
                else if(board[i][j] == 100){
                    res = '@';
                }
                else{
                    res = String.valueOf(board[i][j]).charAt(0);
                }
                System.out.print(res);
            }
            System.out.println();
            printSeparator(board[0].length);
        }
    }


    // private static char [][] generateResult()


    private static int[][] generateMinesweeperBoard(int rows, int cols, double mineProbability) {
        int[][] board = new int[rows][cols];

        // Populate the board with cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Math.random() < mineProbability) {
                    board[i][j] = 100; // Mine
                } else {
                    board[i][j] = 0; // Empty cell
                }
            }
        }
        return board;
    }

    private static void printSeparator(int cols) {
        for (int i = 0; i < cols*2; i++) {
            System.out.print("- ");
        }
        System.out.println();
    }

    private static int [][]generateResultantBoard(int [][]board){
        int rows = board.length;
        int cols = board[0].length;
        int [][] resultantBoard = new int[rows][cols];

        for(int i = 0;i<rows;i++){
            for(int j = 0;j< cols;j++){

                if(board[i][j] == 100){
                    resultantBoard[i][j] = 100;
                    continue;
                }


                int count = 0;

                if(i-1 >=0 && j-1 >=0 && board[i-1][j-1] == 100){
                    count = count + 1;
                }
                if(i-1 >=0 && j >=0 && board[i-1][j] == 100){
                    count = count + 1;
                }
                if(i-1 >=0 && j+1 < cols && board[i-1][j+1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j-1 >=0 && board[i][j-1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j+1 < cols && board[i][j+1] == 100){
                    count = count + 1;
                }
                if(i+1 < rows && j-1 >=0 && board[i+1][j-1] == 100){
                    count = count + 1;
                }
                if(i+1 < rows && j >=0 && board[i+1][j] == 100){
                    count = count + 1;
                }
                if(i+1 < rows && j+1 < cols && board[i+1][j+1] == 100){
                    count = count + 1;
                }
                resultantBoard[i][j] = count;

            }
        }

        return resultantBoard;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int rows = 5;
        int cols = 5;
        double mineProbability = 0.2; // Probability of a cell containing a mine
        int[][] board = generateMinesweeperBoard(rows, cols, mineProbability);
        int [][]resultantBoard = generateResultantBoard(board);
        printMinesweeperBoard(board);
        printGameBoard(resultantBoard);


        int difficulty = 0;
        
        
        System.out.println("Enter the Difficulty Level You Want : 1 2 3");
        // Get difficulty from user
        difficulty = sc.nextInt();
        
        while(difficulty < 0 || difficulty > 3){
            System.out.println("Select the difficulty properly\n\n\n");
            System.out.println("Enter the Difficulty Level You Want : 1 2 3");
            difficulty = sc.nextInt();
        }

        //TODO:  Based on difficulty Change the board

        int [][]gameBoard = new int[rows][cols];
        printMinesweeperBoard(gameBoard);
        printGameBoard(gameBoard);


        



        System.out.println("You're good to go now");










    }
}
