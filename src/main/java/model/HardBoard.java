package src.main.java.model;

public class HardBoard extends Board{
    
    public HardBoard(int rows, int cols) {
        super(rows, cols, rows + (2 * 3), 3);
    }
}
