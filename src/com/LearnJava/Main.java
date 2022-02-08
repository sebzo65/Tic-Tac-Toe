package com.LearnJava;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static int[] userInput = new int[2];
    public static char[][] updateGameMatrix = new char[3][3];
    public static boolean playing = true;
    public static int counter = 0;


    public static void main(String[] args) {
        //Create board, fill it with '_' & display the empty board full of '_'
        char[][] gameMatrix = new char[3][3];
        fillMatrix(gameMatrix);
        displayBoard(gameMatrix);
        fillMatrix(updateGameMatrix);

        do {
            boolean xWins = isTheWinner(updateGameMatrix, 'X');
            boolean oWins = isTheWinner(updateGameMatrix, 'O');

            if (xWins) {
                System.out.println("X wins");
                playing = false;

            } else if (oWins) {
                System.out.println("O wins");
                playing = false;

            } else if (!isGameFinished(updateGameMatrix)) {
                reply();
            } else {
                System.out.println("Draw");
                playing = false;
            }
        } while (playing);
    }


    //Output an empty grid at the beginning of the game
    //Modify placeChar function so that userInput in matrix = symbol.
    //Then in a different function have the player value ('O' or 'X') substitute for symbol
    //variable
    //End the game when someone wins or there is a draw


//        System.out.println(getState(gameMatrix));

    //Method to display tic-tac-toe grid
    public static void displayBoard (char[][] matrix) {
        final char zero = matrix[0][0]; //coordinates(1,1)
        final char one = matrix[0][1]; //coordinates(1,2)
        final char two = matrix[0][2]; //coordinates(1,3)
        final char three = matrix[1][0]; //coordinates(2,1)
        final char four = matrix[1][1]; //coordinates(2,2)
        final char five = matrix[1][2]; //coordinates(2,3)
        final char six = matrix[2][0]; //coordinates(3,1)
        final char seven = matrix[2][1]; //coordinates(3,2)
        final char eight = matrix[2][2]; //coordinates(3,3)

//        System.out.printf("Enter cells: %s", str);
        System.out.println();
        System.out.println("---------");
        System.out.println("| " + zero + " " + one + " " + two + " |");
        System.out.println("| " + three + " " + four + " " + five + " |");
        System.out.println("| " + six + " " + seven + " " + eight + " |");
        System.out.println("---------");
    }

    //Method to populate Matrix with Tic-Tac-Toe String values
    static void fillMatrix (char[][] matrix) {
//        int k = 0;
//        System.out.println("Game Values:");
        for (int i = 0; i < 3; i++) {
            System.out.println();
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = '_';
//                k++;
//                System.out.print(matrix[i][j] + " ");
            }
        }
    }

    //Method to check whether cell is occupied
    private static void placeChar (char[][] matrix, int[] userInput, char symbol) {
        if (matrix[userInput[0] - 1][userInput[1] - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            reply();
        } else {
            matrix[userInput[0] - 1][userInput[1] - 1] = symbol;
            counter++;
            displayBoard(matrix);
            reply();

        }

    }

    //Method to determine what symbol it is & alternate turns & display updated grid
    private static void takeTurnsUpdate () {
        if (counter % 2 == 0) {
            placeChar(updateGameMatrix, userInput, 'X');
        } else {
            placeChar(updateGameMatrix, userInput, 'O');
        }

    }

    //Method to provide feedback about valid inputs

    private static void reply() {
        System.out.print("Enter the coordinates: ");
        try {
            userInput[0] = scanner.nextInt();
            userInput[1] = scanner.nextInt();
            if (userInput[0] > 3 || userInput[0] < 1 || userInput[1] > 3 || userInput[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                takeTurnsUpdate();
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            reply();
        }
    }

    //Method to check conditions for either o or x to win
    private static boolean isTheWinner(char[][] matrix, char symbol) {
        return (matrix[0][0] == symbol && matrix[0][1] == symbol && matrix[0][2] == symbol) ||
                (matrix[1][0] == symbol && matrix[1][1] == symbol && matrix[1][2] == symbol) ||
                (matrix[2][0] == symbol && matrix[2][1] == symbol && matrix[2][2] == symbol) ||

                (matrix[0][0] == symbol && matrix[1][0] == symbol && matrix[2][0] == symbol) ||
                (matrix[0][1] == symbol && matrix[1][1] == symbol && matrix[2][1] == symbol) ||
                (matrix[0][2] == symbol && matrix[1][2] == symbol && matrix[2][2] == symbol) ||

                (matrix[0][0] == symbol && matrix[1][1] == symbol && matrix[2][2] == symbol) ||
                (matrix[0][2] == symbol && matrix[1][1] == symbol && matrix[2][0] == symbol);
    }

    //Method that checks the state of the game and outputs the relevant outcome
//    private static void getState (char[][] matrix) {
////        boolean xWins = isTheWinner(matrix, 'X');
////        boolean oWins = isTheWinner(matrix, 'O');
//
////        if (xWins && oWins || checkImpossible(matrix)) {
//////            currentState = "Impossible";
////            System.out.println("Impossible");
////        } else if (xWins) {
//////            currentState = "X wins";
////            System.out.println("X wins");
////        } else if (oWins) {
//////            currentState = "O wins";
////            System.out.println("O wins");
////        } else if (!isGameFinished(matrix)) {
////            System.out.println("Not finished");
////        } else {
//////            currentState = "Draw";
////            System.out.println("Draw");
////        }
//    }

    //Method to check whether there is an impossible situation in the game
    //i.e. Difference between o's and x's is greater than 1
//    private static boolean checkImpossible (char[][] matrix) {
//        int len = matrix.length;
//        int counterX = 0;
//        int counterO = 0;
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                if (matrix[i][j] == 'X') {
//                    counterX++;
//                } else if (matrix[i][j] == 'O') {
//                    counterO++;
//                }
//            }
//        }
//        return Math.abs(counterX - counterO) > 1;
//    }

    //Check whether the game is finished, by checking for any empty spaces
    private static boolean isGameFinished (char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar == '_') {
                    return false;
                }
            }
        }
        return true;
    }

}
