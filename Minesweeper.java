import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {

    static int mines = 0;



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

    public static void printBoard(int [][]board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char res = '.';
                if(board[i][j] == 0){
                    res = '.';
                }
                else if(board[i][j] == 100){
                    res = '.';
                }
                else if(board[i][j] == -1){
                    res = ' ';
                }
                else{
                    res = String.valueOf(board[i][j]).charAt(0);
                }
                System.out.print(res+ " | ");
            }
            System.out.println();
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
                System.out.print(res+ " | ");
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
                    mines++;
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


    static int getExplored(boolean [][]explored){
        int count = 0;
        for(int i = 0;i<explored.length;i++){
            for(int j = 0;j<explored[0].length;j++){
                if(explored[i][j] == true){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int rows = 5;
        int cols = 5;
        double mineProbability = 0.2; // Probability of a cell containing a mine
        int[][] board = generateMinesweeperBoard(rows, cols, mineProbability);
        int [][]resultantBoard = generateResultantBoard(board);
        printMinesweeperBoard(board);
        System.out.println("################################");
        System.out.println("################################");
        
        printBoard(resultantBoard);


        int difficulty = 0;
        
        
        System.out.println("Enter the Difficulty Level You Want : 1 2 3");
        // Get difficulty from user
        difficulty = sc.nextInt();
        
        // while(difficulty < 0 || difficulty > 3){
        //     System.out.println("Select the difficulty properly\n\n\n");
        //     System.out.println("Enter the Difficulty Level You Want : 1 2 3");
        //     difficulty = sc.nextInt();
        // }

        // //TODO:  Based on difficulty Change the board

        int [][]gameBoard = new int[rows][cols];
        boolean [][]explored = new boolean[rows][cols];
        
        printGameBoard(gameBoard);

        while(true){

            System.out.println("Enter the cell Number : ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            // System.out.println("Resultant Board");
            // System.out.println(Arrays.deepToString(resultantBoard));

            if(resultantBoard[row][col] == 100){
                System.out.println("You Lost!!!");
                break;
            }
            else if(resultantBoard[row][col] >= 1){
                gameBoard[row][col] = resultantBoard[row][col];
                explored[row][col] = true;
            }
            else{
                updateGameBoard(gameBoard, row, col, new boolean[rows][cols], resultantBoard, explored);                
            }
            printBoard(gameBoard);
            int noOfExplored = getExplored(explored);

                System.out.println("No of Explored" + noOfExplored);

                if(rows * cols - noOfExplored == mines){
                    System.out.println("You Win!!!");
                    break;
                }

        }

        System.out.println("You're good to go now");

    }

    static void updateGameBoard(int [][]gameBoard, int row, int col, boolean visited[][], int [][]resultantBoard, boolean [][]explored) {

        // Go in all directions, 
        // For every cell, check if the cell is a number, if it is, updat the gameBoard and return
        // if the cell is not a number, mark it -1 and explore all the other cells around it
        // make sure that you don't repeat the same cells and don't go out of bounds.
        if(row < 0 || row >= gameBoard.length || col < 0 || col >= gameBoard[0].length){
            return;
        }

        if(resultantBoard[row][col] == 100){
            return;
        }
      

        if(visited[row][col] == true){
            return;
        }

        if(resultantBoard[row][col] >= 1){
            gameBoard[row][col] = resultantBoard[row][col];
            explored[row][col] = true;
            return;
        }

        if(resultantBoard[row][col] == 0){
            gameBoard[row][col] = -1;
            explored[row][col] = true;
        }

        visited[row][col] = true;

        // Explore all directions
        updateGameBoard(gameBoard, row-1, col-1, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row-1, col, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row-1, col+1, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row, col-1, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row, col+1, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row+1, col-1, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row+1, col, visited, resultantBoard, explored);
        updateGameBoard(gameBoard, row+1, col+1, visited, resultantBoard, explored);
    }
}
