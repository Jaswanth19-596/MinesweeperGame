package src.main.java.model;

public class Board {
    private int [][]mineField;
    private boolean [][]revealed;
    private int [][]finalState;
    private int rows;
    private int cols;
    private int totalMines;
    private int remainingCells;
    private int difficulty;

    public Board(int rows, int cols, int totalMines, int difficulty){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.mineField = new int[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.remainingCells = rows * cols - totalMines;
        this.difficulty = difficulty;
        this.finalState = new int[rows][cols];
    }

    // private void generateMinesweeperBoard(){
    
    //     // Populate the board with cells
    //     int minesPlaced = 0;
    //     while (minesPlaced < totalMines) {
    //         int randRow = (int) (Math.random() * rows);
    //         int randCol = (int) (Math.random() * cols);
            
    //         if (mineField[randRow][randCol] != 100) { // If the cell is not already a mine
    //             mineField[randRow][randCol] = 100; // Mine
    //             minesPlaced++;
    //         }
    //     }

    //     // Fill remaining cells with empty cells
    //     for (int i = 0; i < rows; i++) {
    //         for (int j = 0; j < cols; j++) {
    //             if (mineField[i][j] != 100) {
    //                 mineField[i][j] = 0; // Empty cell
    //             }
    //         }
    //     }

    // }



    // Generates Resultatnt Board based on the Mines placed in the Original Board.
    public void generateResultantBoard(){
        int dimension = mineField.length;

        for(int i = 0;i<dimension;i++){
            for(int j = 0;j< dimension;j++){

                if(mineField[i][j] == 100){
                    finalState[i][j] = 100;
                    continue;
                }

                int count = 0;

                if(i-1 >=0 && j-1 >=0 && mineField[i-1][j-1] == 100){
                    count = count + 1;
                }
                if(i-1 >=0 && j >=0 && mineField[i-1][j] == 100){
                    count = count + 1;
                }
                if(i-1 >=0 && j+1 < dimension && mineField[i-1][j+1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j-1 >=0 && mineField[i][j-1] == 100){
                    count = count + 1;
                }
                if(i >=0 && j+1 < dimension && mineField[i][j+1] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j-1 >=0 && mineField[i+1][j-1] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j >=0 && mineField[i+1][j] == 100){
                    count = count + 1;
                }
                if(i+1 < dimension && j+1 < dimension && mineField[i+1][j+1] == 100){
                    count = count + 1;
                }
                finalState[i][j] = count;
            }
        }
    }

    public void generateMinesForBoard() {
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
            
            if (mineField[randRow][randCol] != 100) { // If the cell is not already a mine
                mineField[randRow][randCol] = 100; // Mine
                minesPlaced++;
            }
        }

        // Fill remaining cells with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineField[i][j] != 100) {
                    mineField[i][j] = 0; // Empty cell
                }
            }
        }
    }

    public void updateGameBoard(int row, int col, boolean visited[][]) {

        // Go in all directions, 
        // For every cell, check if the cell is a number, if it is, updat the mineField and return
        // if the cell is not a number, mark it -1 and explore all the other cells around it
        // make sure that you don't repeat the same cells and don't go out of bounds.
        if(row < 0 || row >= mineField.length || col < 0 || col >= mineField[0].length){
            return;
        }

        if(finalState[row][col] == 100){
            return;
        }
      

        if(visited[row][col] == true){
            return;
        }

        if(finalState[row][col] >= 1){
            mineField[row][col] = finalState[row][col];
            revealed[row][col] = true;
            return;
        }

        if(finalState[row][col] == 0){
            mineField[row][col] = -1;
            revealed[row][col] = true;
        }

        visited[row][col] = true;

        // Explore all directions
        updateGameBoard(row-1, col-1, visited);
        updateGameBoard(row-1, col, visited);
        updateGameBoard(row-1, col+1, visited);
        updateGameBoard(row, col-1, visited);
        updateGameBoard(row, col+1, visited);
        updateGameBoard(row+1, col-1, visited);
        updateGameBoard(row+1, col, visited);
        updateGameBoard(row+1, col+1, visited);
    }

    public int[][] getBoardFinalState(){
        return finalState;
    }

    public int[][] getBoard(){
        return mineField;
    }

}