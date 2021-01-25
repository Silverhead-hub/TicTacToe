package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static char[][] cells = new char[3][3];
    public static int[][] array = new int[3][3];
    public static int step = 3;
    public static int checkWins = 0;
    public static int countMove = 0;



    public static int hor_1;
    public static int hor_2;
    public static int hor_3;

    public static int ver_1;
    public static int ver_2;
    public static int ver_3;

    public static int dia_1;
    public static int dia_2;

    public static int X_Wins = 0;
    public static int O_Wins = 0;


    public static void main(String[] args) {
        initializationCells();
        printTable();

        while (makeMove() == 1 && checkWins == 0) {
            convertToIntArray();
            sumLine();
            checkTable();
            
            if(checkWins == 1){
                break;
            }
        }

    }

    public static void initializationCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public static void printTable() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void convertToIntArray() {
        countMove = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 'X') {
                    array[i][j] = 1;
                    countMove++;

                }
                if (cells[i][j] == 'O') {
                    array[i][j] = -1;
                    countMove++;

                }
                if (cells[i][j] == ' ') {
                    array[i][j] = 0;

                }
            }
        }
    }

    public static void sumLine() {
        hor_1 = array[0][0] + array[0][1] + array[0][2];
        hor_2 = array[1][0] + array[1][1] + array[1][2];
        hor_3 = array[2][0] + array[2][1] + array[2][2];

        ver_1 = array[0][0] + array[1][0] + array[2][0];
        ver_2 = array[0][1] + array[1][1] + array[2][1];
        ver_3 = array[0][2] + array[1][2] + array[2][2];

        dia_1 = array[0][0] + array[1][1] + array[2][2];
        dia_2 = array[2][0] + array[1][1] + array[0][2];
    }

    public static void checkTable() {

        checkHorizontal();
        checkVertical();
        checkDiagonal();

        if(X_Wins == 0 && O_Wins == 0 && countMove == 9){
            System.out.println("Draw");
            checkWins = 1;
        } else if (X_Wins == 1) {
            System.out.println("X wins");
            checkWins = 1;
        } else if (O_Wins == 1) {
            System.out.println("O wins");
            checkWins = 1;
        } else
           checkWins = 0;

    }

    public static void checkHorizontal() {
        if (hor_1 == 3 || hor_2 == 3 || hor_3 == 3) {
            X_Wins = 1;
        }
        if (hor_1 == -3 || hor_2 == -3 || hor_3 == -3) {
            O_Wins = 1;
        }
    }

    public static void checkVertical() {
        if (ver_1 == 3 || ver_2 == 3 || ver_3 == 3) {
            X_Wins = 1;
        }
        if (ver_1 == -3 || ver_2 == -3 || ver_3 == -3) {
            O_Wins = 1;
        }
    }

    public static void checkDiagonal() {
        if (dia_1 == 3 || dia_2 == 3) {
            X_Wins = 1;
        }
        if (dia_1 == -3 || dia_2 == -3) {
            O_Wins = 1;
        }
    }

    public static int makeMove() {

        Scanner scanner = new Scanner(System.in);

        int one = 0;
        int two = 0;
        try {
            System.out.print("Enter the coordinates: ");
            one = scanner.nextInt();
            two = scanner.nextInt();
            if ((one < 1 || one > 3) || (two < 1 || two > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                return 1;
            }
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            return 1;
        }


        one -= 1;
        two -= 1;
        if (cells[one][two] == 'X' || cells[one][two] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
        }
        else {
            if (step % 2 != 0) {
                cells[one][two] = 'X';
            }
            else {
                cells[one][two] = 'O';
            }
            step++;
            printTable();
        }
        return 1;
    }
}





