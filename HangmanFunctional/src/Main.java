import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static String playStyle(){
        System.out.println("Play with words or phrases? (Words or Phrases)");
        Scanner input = new Scanner(System.in);
        String playAgain = input.nextLine();
        if(playAgain.equalsIgnoreCase("words")){
            return "WORDS";
        }else {
            if (playAgain.equalsIgnoreCase("phrases")) {
                return "PHRASES";
            } else {
                System.out.println("Enter a valid answer.");
                return playStyle();
            }
        }
    }
    public static boolean playAgain(){
        System.out.println("Do you want to play again? (Yes or No)");
        Scanner input = new Scanner(System.in);
        String playAgain = input.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            return true;
        } else {
            if (!playAgain.equalsIgnoreCase("no")) {
                System.out.println("Enter a valid answer.");
                return playAgain();
            }
            return false;
        }
    }
    public static void main(String[] args) {
        boolean playing = true;
        while(playing) {
            hangman.playGame(playStyle());
            playing = playAgain();
        }
    }
}