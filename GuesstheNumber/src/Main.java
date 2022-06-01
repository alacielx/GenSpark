import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        char cont = 'y';
            while(cont == 'y') {

                System.out.println("Hello what is your name?");

                Scanner input = new Scanner(System.in);
                String name = input.nextLine();

                System.out.println("Hello " + name +
                        " I am thinking of a number between 1 and 20.\nTake a guess.");

                Random rnd = new Random();
                int num = rnd.nextInt(20) + 1;

                int c;
                for (c = 1; c <= 6; c++) {
                    int guess = Integer.parseInt(input.nextLine());

                    if (guess == num) {
                        System.out.println("Congrats " + name + " you guessed the number in "
                                + c + " guesses!!");
                        break;
                    }

                    if (guess < num && c < 6) {
                        System.out.println("Your guess is too low.\nTake another guess.");
                    } else {
                        if (guess > num && c < 6) {
                            System.out.println("Your guess is too high.\nTake another guess.");
                        }
                    }
                }

                if (c > 6){
                    System.out.println("You weren't able to guess the number in 6 guesses.");
                }

                System.out.println("Do you wanna play again?" + "\n(y or n)");

                cont = (char)input.nextLine().charAt(0);
                }

    }
}