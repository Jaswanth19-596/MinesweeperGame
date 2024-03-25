package src.main.java.gameplay;
import java.util.*;

public class GamePlay {
    private Scanner sc;
    public ArrayList<String> winningStrings;

    private final int boardDimension = 9;

    public GamePlay(){
        sc = new Scanner(System.in);
        winningStrings = new ArrayList<>();
        winningStrings.add("Wooohoooo !!! You have won the game!!");
        winningStrings.add("Chose the wrong cell......!! You Lost");
    }

    public void displayMessage(String message){
        System.out.println(message);
    }


    public void displayBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Print column numbers
        System.out.print("   ");
        for (int j = 0; j < cols; j++) {
            System.out.printf(" %d  ", j);
        }

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

    private void printSeparator() {
        for (int i = 0; i < boardDimension+1; i++) {
            System.out.print("----");
        }
        System.out.println();
    }

    public int[] getPlayerMove(){

        System.out.println("Enter Your Next Move : (row col)");
        int row = sc.nextInt();
        int col = sc.nextInt();

        // Recursively call the same function till user enters correct cell numbers
        if(row < 0 || row >= 9 || col < 0 || col >= 9){
            return getPlayerMove();
        }

        return new int[]{row, col};
    }

    public int getDifficulty(){
        System.out.println("Enter the Difficulty : (1, 2, 3)");
        int difficulty = sc.nextInt();
        while(difficulty > 3 || difficulty<=0){
            System.out.println("Enter the Difficulty within these three values : (1, 2, 3)");
            difficulty = sc.nextInt();
        }
        return difficulty;
    }

    public void closeScanner(){
        sc.close();
    }
}
