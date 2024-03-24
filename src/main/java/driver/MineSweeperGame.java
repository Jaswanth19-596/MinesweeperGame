package src.main.java.driver;

import src.main.java.gameplay.GamePlay;

import java.util.Arrays;
import java.util.Scanner;

public class MineSweeperGame {

    static int mines = 0;

    static final int dimension = 9;



    public static void printMinesweeperBoard(int [][]board) {

        // Print the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print((board[i][j] == 100 ? '@' : '.')+ " | ");
            }
            System.out.println();
            printSeparator();
        }
    }

    // public static void printBoard(int [][]board) {
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[0].length; j++) {
    //             char res = '.';
    //             if(board[i][j] == 0){
    //                 res = '.';
    //             }
    //             else if(board[i][j] == 100){
    //                 res = '.';
    //             }
    //             else if(board[i][j] == -1){
    //                 res = ' ';
    //             }
    //             else{
    //                 res = String.valueOf(board[i][j]).charAt(0);
    //             }
    //             System.out.print(res+ " | ");
    //         }
    //         System.out.println();
    //     }
    // }
    
    public static void printBoard(int[][] board) {
    int rows = board.length;
    int cols = board[0].length;

    // Print column numbers
    System.out.print("   ");
    for (int j = 0; j < cols; j++) {
        System.out.printf(" %d  ", j);
    }
    // System.out.println();
    // System.out.print("   ");
    // for (int j = 0; j < cols; j++) {
    //     System.out.print("---");
    // }
    System.out.println();
    printSeparator();

    // Print board content with row numbers
    for (int i = 0; i < rows; i++) {
        System.out.printf("%-2d| ", i);
        for (int j = 0; j < cols; j++) {
            char res;
            if (board[i][j] == 0 || board[i][j] == 100) {
                res = '.';
            } else if (board[i][j] == -1) {
                res = ' ';
            } else {
                res = Character.forDigit(board[i][j], 10);
            }
            System.out.print(res + " | ");
        }
        System.out.println();
        printSeparator();

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
            printSeparator();
        }
    }


    // private static char [][] generateResult()


    // private static int[][] generateMinesweeperBoard(int rows, int cols, double mineProbability, int difficulty) {
    //     int[][] board = new int[rows][cols];

    //     // Populate the board with cells
    //     for (int i = 0; i < rows; i++) {
    //         for (int j = 0; j < cols; j++) {
    //             if (Math.random() < mineProbability) {
    //                 board[i][j] = 100; // Mine
    //                 mines++;
    //             } else {
    //                 board[i][j] = 0; // Empty cell
    //             }
    //         }
    //     }
    //     return board;
    // }
    private static int[][] generateMinesweeperBoard(int rows, int cols, double mineProbability, int difficulty) {
        int[][] board = new int[rows][cols];
        int totalMines = 0;

        // Set the number of mines based on difficulty level
        switch (difficulty) {
            case 0: // EASY
                totalMines = rows;
                break;
            case 1: // MEDIUM
                totalMines = rows + 1 * 3;
                break;
            case 2: // HARD
                totalMines = rows + 2 * 3;
                break;
            default:
                System.out.println("Invalid difficulty level. Setting to EASY by default.");
                totalMines = rows;
                break;
        }

        // Populate the board with cells
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int randRow = (int) (Math.random() * rows);
            int randCol = (int) (Math.random() * cols);
            
            if (board[randRow][randCol] != 100) { // If the cell is not already a mine
                board[randRow][randCol] = 100; // Mine
                minesPlaced++;
            }
        }

        // Fill remaining cells with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != 100) {
                    board[i][j] = 0; // Empty cell
                }
            }
        }

        return board;
    }


    private static void printSeparator() {
        for (int i = 0; i < dimension+1; i++) {
            System.out.print("----");
        }
        System.out.println();
    }

    private static int [][]generateResultantBoard(int [][]board){
        int dimension = board.length;
        int [][] resultantBoard = new int[dimension][dimension];

        for(int i = 0;i<dimension;i++){
            for(int j = 0;j< dimension;j++){

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
                if(i-1 >=0 && j+1 < dimension && board[i-1][j+1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j-1 >=0 && board[i][j-1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j+1 < dimension && board[i][j+1] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j-1 >=0 && board[i+1][j-1] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j >=0 && board[i+1][j] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j+1 < dimension && board[i+1][j+1] == 100){
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

        GamePlay g = new GamePlay();

        int difficulty = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Difficulty Level You Want : 1 2 3");
        difficulty = sc.nextInt();

        double mineProbability = 0.2; // Probability of a cell containing a mine
        int[][] board = generateMinesweeperBoard(dimension, dimension, mineProbability, difficulty);
        int [][]resultantBoard = generateResultantBoard(board);
        printMinesweeperBoard(board);
        System.out.println("################################");
        System.out.println("################################");
        
        printBoard(resultantBoard);


        
        
        // Get difficulty from user
        
        // while(difficulty < 0 || difficulty > 3){
        //     System.out.println("Select the difficulty properly\n\n\n");
        //     System.out.println("Enter the Difficulty Level You Want : 1 2 3");
        //     difficulty = sc.nextInt();
        // }

        // //TODO:  Based on difficulty Change the board

        int [][]gameBoard = new int[dimension][dimension];
        boolean [][]explored = new boolean[dimension][dimension];
        
        printBoard(gameBoard);

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
                updateGameBoard(gameBoard, row, col, new boolean[dimension][dimension], resultantBoard, explored);                
            }
            printBoard(gameBoard);
            int noOfExplored = getExplored(explored);


                if(dimension * dimension - noOfExplored == mines){
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

