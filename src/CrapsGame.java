import java.util.Random;
import java.util.Scanner;

public class CrapsGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random generator = new Random();
        boolean playAgain = true;

        System.out.println("Welcome to Craps!");

        while (playAgain) {
            playGame(generator, in);
            playAgain = askToPlayAgain(in);
        }

        System.out.println("Thanks for playing!");
        in.close();
    }

    // Plays one complete game of craps
    public static void playGame(Random generator, Scanner in) {
        int point = 0;
        int sum = 0;
        boolean gameOver = false;

        // Initial roll
        System.out.print("\nPress Enter to make the initial roll...");
        in.nextLine();
        sum = rollDice(generator);

        if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println("Craps! You crapped out and lost.");
            gameOver = true;
        } else if (sum == 7 || sum == 11) {
            System.out.println("Natural! You win.");
            gameOver = true;
        } else {
            point = sum;
            System.out.println("The point is now " + point + ".");
        }

        // Subsequent rolls until the point is made or a 7 is thrown
        while (!gameOver) {
            System.out.print("\nPress Enter to roll again...");
            in.nextLine();
            sum = rollDice(generator);

            if (sum == point) {
                System.out.println("Made point and won!");
                gameOver = true;
            } else if (sum == 7) {
                System.out.println("Got a seven and lost.");
                gameOver = true;
            } else {
                System.out.println("Trying for point (" + point + ")...");
            }
        }
    }

    // Rolls two dice, displays each die and the sum, and returns the sum
    public static int rollDice(Random generator) {
        int die1 = generator.nextInt(6) + 1;
        int die2 = generator.nextInt(6) + 1;
        int sum = die1 + die2;

        System.out.printf("Die 1: %d  Die 2: %d  Sum: %d%n", die1, die2, sum);
        return sum;
    }

    // Loops until the user enters a valid Y or N
    public static boolean askToPlayAgain(Scanner in) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("\nPlay again? [Y/N]: ");
            input = in.nextLine().trim();

            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
                valid = true;
            } else {
                System.out.println("Invalid entry. You must enter Y or N.");
            }
        }

        return input.equalsIgnoreCase("Y");
    }
}
