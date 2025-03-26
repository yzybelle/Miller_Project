import java.util.Scanner;

public class GridGame {
    private Space[][] board;
    private Player player;
    private Scanner scanner;

    public GridGame() {
        scanner = new Scanner(System.in);
        createPlayer();
        setupBoard();
        printBoard();
        play();
    }

    private void createPlayer() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);
    }

    // initialize the board instance variable to be a 8x8 board;
    // place new Space object with "_" as the symbol into each board position;
    // place the Player object at lower left corner;
    // initialize and place a Goal object with the symbol "X" in the upper right corner;
    // create several Treasure objects (up to you how many), with symbol of your choice,
    // each with a point value that you decide, and place them throughout the board
    private void setupBoard() {
        board = new Space[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = new Space("_");
        }

        board[7][0] = new Space("M");
        board[0][7] = new Space("X");

        board[5][6] = new Space("O");
        board[4][7] = new Space("O");
        board[2][3] = new Space("O");
        board[0][5] = new Space("O");
        Treasure won = new Treasure("O",5);


    }

    /* prints the 2D array board, showing the symbol for each Space, e.g.
       _______X
       __#_____
       _____#__
       _#______
       ________
       ______#_
       ________
       M___#___
     */
    private void printBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf(board[i][j].getSymbol());

            }
            System.out.println("\n");
        }


    }
    private boolean inBounds(int x, int y){
        if (x<0||y<0||x>=board[0].length||y>= board.length){
            return false;
        }
        return true;
    }

    // plays the game;
    private void play() {

        // WRITE THIS METHOD
        // main game loop:
        // while the player has not yet reached the goal, print the board (complete can call helper method below)
        // then asks user to enter a direction: W, A, S, D (up, left, down, right).
        // if the intended direction is in bounds, move the Player to the new location and fill previous position
        // with a Space object (with "_" symbol).
        // if player moves to a position occupied by a Treasure, add its point value to the players score,
        // and replace that element with a Space object (with "_" symbol).
        // if the player reaches the goal, end the game and print their final score and the number of moves it took
        int row = 7;  // Starting row for player (M)
        int col = 0;  // Starting column for player (M)
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon) {
            printBoard();  // Print the current board
            System.out.print("Which direction do you want to go? (W,A,S,D): ");
            String direction = scanner.nextLine().toUpperCase();  // Make input case-insensitive

            // Move the player based on the input direction
            if (direction.equals("D") && inBounds(row, col + 1)) {
                board[row][col] = new Space("_");  // Clear the old position
                col++;  // Move right
            } else if (direction.equals("A") && inBounds(row, col - 1)) {
                board[row][col] = new Space("_");  // Clear the old position
                col--;  // Move left
            } else if (direction.equals("W") && inBounds(row - 1, col)) {
                board[row][col] = new Space("_");  // Clear the old position
                row--;  // Move up
            } else if (direction.equals("S") && inBounds(row + 1, col)) {
                board[row][col] = new Space("_");  // Clear the old position
                row++;  // Move down
            }

            // Check if the player moves onto a treasure (represented by "O")
            if (board[row][col].getSymbol().equals("O")) {
                // For example, assume "O" is a treasure
                player.addPoints(5);  // Add score for the treasure
                board[row][col] = new Space("_");  // Remove the treasure
            }

            // Place the player at the new position
            board[row][col] = new Space("M");

            // Check if the player has reached the goal
            if (row == 0 && col == 7) {
                gameWon = true;
                System.out.println("Congratulations, you've reached the goal!");
                System.out.println("Your final score is: " + player.getScore());
                System.out.println("You took " + moves + " moves.");
            }

            moves++;
        }





    }
    }