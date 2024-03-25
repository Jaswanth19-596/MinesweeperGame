package src.main.java.driver;

import src.main.java.gameplay.GamePlay;
import src.main.java.model.Board;
import src.main.java.model.EasyBoard;
import src.main.java.model.HardBoard;
import src.main.java.model.MediumBoard;


public class MineSweeperGame {

    static final int dimension = 9;
    public static void main(String[] args) {

        // Takes care of the Input and Output of the Game
        GamePlay g = new GamePlay();

        // Get the difficulty of the game
        int difficulty = g.getDifficulty();

        // Create a board
        Board board;

        // Set the number of mines based on difficulty level
        switch (difficulty) {
            case 0: // EASY
                board = new EasyBoard(dimension, dimension);
                break;
            case 1: // MEDIUM
                board = new MediumBoard(dimension, dimension);
                break;
            case 2: // HARD
                board = new HardBoard(dimension, dimension);
                break;
            default:
                System.out.println("Invalid difficulty level. Setting to EASY by default.");
                board = new EasyBoard(dimension, dimension);
        }

        // Generates the Board for the game
        board.generateMinesForBoard();

        // Generates the resultant board
        board.generateResultantBoard();        
        g.displayBoard(board.getBoardFinalState());
        while(true){
            
            // Displays the board to the user
            g.displayBoard(board.getBoard());


            // Ask the user to Enter the cell Number
            int []move = g.getPlayerMove();
            int row = move[0], col = move[1];

            // If the Cell contains a Mine -> Player Lost
            if(board.getBoardFinalState()[row][col] == 100){
                g.displayMessage(g.winningStrings.get(1));
                break;
            }
            // If the cell's neighbours has mine
            else if(board.getBoardFinalState()[row][col]>=1){
                board.getBoard()[row][col] = board.getBoardFinalState()[row][col];
                board.getRevealed()[row][col] = true;
            }
            // If either the cell or it's neighbouts has mine 
            else{
                board.updateGameBoard(row, col, new boolean[dimension][dimension]);
            }
            
            int explored = board.getExploredCount(board.getRevealed());
            

            System.out.println(dimension * dimension - explored + " "+board.getTotalMines());
            // If the user explored all the cells -> User win
            if(dimension * dimension - explored == board.getTotalMines()){
                g.displayMessage(g.winningStrings.get(1));
                break;
            }
        }

        g.closeScanner();
    }
}

