import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int BOARD_SIZE = 5;
    public static final int OBSTACLES_COUNT = 3;

    public static boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];
    public static int playerX = 0;
    public static int playerY = 0;

    public static void main(String[] args) {
        generateObstacles();
        printBoard();
        System.out.println("Musisz dojść do przeciwległego boku planszy!");
        while (true) {
            System.out.println("Podaj ruch (w, a, s, d): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            movePlayer(input);
            if (checkLose()) {
                System.out.println("Przegrałeś!");
                break;
            }
            if (checkWin()) {
                System.out.println("Wygrałeś!");
                break;
            }
        }
    }

    public static void generateObstacles() {
        Random random = new Random();
        for (int i = 0; i < OBSTACLES_COUNT; i++) {
            int x, y;
            do {
                x = random.nextInt(BOARD_SIZE);
                y = random.nextInt(BOARD_SIZE);
            } while (board[x][y]);
            board[x][y] = true;
        }
    }

    public static void printBoard() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("P ");
                } else if (board[x][y]) {
                    System.out.print("X ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }


    public static void movePlayer(String input) {

        switch (input) {
            case "w":
            case "W":
                if (playerY > 0) {
                    playerY--;
                }
                break;
            case "a":
            case "A":
                if (playerX > 0) {
                    playerX--;
                }
                break;
            case "s":
            case "S":
                if (playerY < BOARD_SIZE - 1) {
                    playerY++;
                }
                break;
            case "d":
            case "D":
                if (playerX < BOARD_SIZE - 1) {
                    playerX++;
                }
                break;
        }
        if(checkWin()){
            System.out.println("Gratulacje, wygrałeś!");
            System.exit(0);
        }
        printBoard();
    }

    public static boolean checkLose() {
        return board[playerX][playerY];
    }

    public static boolean checkWin() {
        return playerX == BOARD_SIZE - 1 && playerY == BOARD_SIZE - 1;
    }
}