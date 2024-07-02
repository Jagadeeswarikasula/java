import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxRange = 100; 
        int randomNumber = random.nextInt(maxRange) + 1; 
        int maxAttempts = 10; 
        int attempts = 0;
        boolean hasWon = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("Can you guess it within " + maxAttempts + " attempts?");

        while (!hasWon && attempts < maxAttempts) {
            System.out.print("Enter your guess (1-100): ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                hasWon = true;
            }
        }

        if (hasWon) {
            System.out.println("Congratulations! You've guessed the number " + randomNumber + " in " + attempts + " attempts.");
        } else {
            System.out.println("Sorry, you've run out of attempts. The number was " + randomNumber + ".");
        }

        scanner.close();
    }
}
