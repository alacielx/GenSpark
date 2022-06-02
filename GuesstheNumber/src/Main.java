import java.util.Scanner;
import java.util.Random;

public class Main {
    public static int tryInt(){
        Scanner input = new Scanner(System.in);
        boolean valid = true;
        int x = 0;

        do {
            try {
                x = Integer.parseInt(input.nextLine());
                valid = true;
            } catch (Exception err) {
                System.out.println("Invalid input.Try again.");
                valid = false;
            }
        }while(!valid);

        return x;
    };

    public static void main(String[] args) {
        char cont = 'y';
            while(cont == 'y') {

                Scanner input = new Scanner(System.in);
                String name = "";
                int c, guess = 0, num;
                boolean valid = false;

                while(name.equals(""))
                {
                    System.out.println("Hello what is your name?");
                    name = input.nextLine();
                }

                System.out.println("Hello " + name +
                        " I am thinking of a number between 1 and 20.\nTake a guess.");

                Random rnd = new Random();
                num = rnd.nextInt(20) + 1;

                for (c = 1; c <= 6; c++) {

                    guess = tryInt();

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

                do{
                    try{
                        cont = (char)input.nextLine().charAt(0);
                        valid = true;
                    }catch(Exception err){
                        System.out.println("Invalid input.Try again.");
                        valid = false;
                    }
                }while(!valid);
                }

    }
}