package com.tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String turn = "X";
        String[][] board = new String[3][3];
        int k = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // to fill the default values in the board
                board[i][j] = Integer.toString(k);
                k++;
            }
        }
        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard(board);
        System.out.println(
                "X will play first. Enter a slot number to place X in:");
        int counter = 0;
        while (true) {
            int numInput;
            try {
                numInput = input.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println(
                            "Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input; re-enter slot number:");
                continue;
            }
            numInput--;// because board is zero indexed
            if (!board[numInput / 3][numInput % 3].equalsIgnoreCase(Integer.toString(numInput + 1))) {
                System.out.println(
                        "Slot already taken; re-enter slot number:");
            } else {
                board[numInput / 3][numInput % 3] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                    counter++;
                } else {
                    turn = "X";
                }
            }
            printBoard(board);
            // print board;

            if (isCompletelyFilledColumn(board) || isCompletelyFilledRow(board)
                    || isCompletelyFilledDiagonal(board)) {
                String winner;
                if (turn.equals("X"))
                    winner = "O";
                else
                    winner = "X";
                System.out.println(
                        "Congratulations! " + winner
                                + "'s have won! Thanks for playing.");
                break;
            } else if (counter == 5) {
                /*
                 * if counter is equal to 5 it means player one has moved 5times and player2 4
                 * times
                 * therefore no cells are left then it's a draw
                 */
                System.out.println(
                        "It's a draw! Thanks for playing.");
                break;
            }
            System.out.println(
                    turn + "'s turn; enter a slot number to place "
                            + turn + " in:");
        }

    }

    private static boolean isCompletelyFilledRow(String[][] board) {
        for (int i = 0; i < 3; i++) {
            String lastMove = board[i][0];
            if (board[i][1].equals(lastMove) && board[i][2].equals(lastMove))
                return true;
        }
        return false;
    }

    private static boolean isCompletelyFilledColumn(String[][] board) {
        for (int i = 0; i < 3; i++) {
            String lastMove = board[0][i];
            if (board[1][i].equals(lastMove) && board[2][i].equals(lastMove))
                return true;
        }
        return false;
    }

    private static boolean isCompletelyFilledDiagonal(String[][] board) {
        // since it is a 3*3 board it contains just 2 diagonal
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return true;
        else return board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]);
    }

    private static void printBoard(String[][] board) {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0][0] + " | "
                + board[0][1] + " | " + board[0][2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[1][0] + " | "
                + board[1][1] + " | " + board[1][2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[2][0] + " | "
                + board[2][1] + " | " + board[2][2]
                + " |");
        System.out.println("|---|---|---|");
    }

}