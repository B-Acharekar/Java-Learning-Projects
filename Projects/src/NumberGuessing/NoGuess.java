package NumberGuessing;

import java.util.Scanner;
import java.util.Random;

public class NoGuess {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        
        int NoToGuess = random.nextInt(100)+1;
        int attempts = 0;
        int userGuess = 0;
        
        System.out.println("Welcome to number guessing game");
        System.out.println("Try to guess a number from 1 to 100");
        while (userGuess != NoToGuess) {
        	System.out.print("\nEnter your guess: ");
        	userGuess = scan.nextInt();
        	attempts++;
        	if(userGuess < NoToGuess) {
        		System.out.print("Too Low ");
        	}
        	else if (userGuess>NoToGuess) {
        		System.out.print("Too High!!");
        	} else {
        		System.out.print("Correct! You guessed  it in " + attempts +" attempts.");
        	}
        
        }
        scan.close();
	}
}
