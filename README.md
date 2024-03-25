# Minesweeper Game

## Introduction

This Minesweeper Game is a Java application that allows users to play the classic Minesweeper game. The game follows the traditional rules where the player needs to clear the board without detonating any mines.

## How to Run

To run the game, follow these steps:

1. Open your command line interface.

2. Navigate to the directory containing the `src` folder. (Make sure you are outside of the src folder.)

3. Compile the Java source code by executing the following command:

```bash
    javac src/main/java/driver/MineSweeperGame.java
```

4. After compiling, run the compiled Java program by executing the following command:

```bash
    java src.main.java.driver.MineSweeperGame
```

## Gameplay Instructions

1. Upon running the game, you will be prompted to select the difficulty level: Easy, Medium, or Hard.

2. Once the difficulty level is selected, the game board will be generated accordingly, and mines will be randomly distributed across the board.

3. The game board will be displayed, and you will be asked to input the row and column numbers to select a cell.

4. If the selected cell contains a mine, the game ends, and you lose.

5. If the selected cell does not contain a mine, its adjacent cells' mine counts will be displayed. If there are no adjacent mines, the neighboring cells will be automatically revealed recursively.

6. The game continues until either all non-mine cells are revealed (in which case you win), or a mine is selected (in which case you lose).

## Additional Notes

- The game utilizes a command-line interface for input and output.
- The game displays messages for winning and losing scenarios.
- Upon completing the game, the user will be prompted to close the game.

Enjoy playing Minesweeper!
