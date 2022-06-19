import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        while(true) {
            String playStyle;
            System.out.println("Play with words or phrases? (Words or Phrases)");
            Scanner input = new Scanner(System.in);
            while(true){
                String playAgain = input.nextLine();
                if(playAgain.equalsIgnoreCase("words")){
                    playStyle="WORDS";
                    break;
                }else{
                    if(playAgain.equalsIgnoreCase("phrases")) {
                        playStyle = "PHRASES";
                        break;
                    }
                    else{
                        System.out.println("Enter a valid answer. (Words or Phrases)");
                    }
                }
            }

            hangman.playGame(playStyle);

            System.out.println("Do you want to play again? (Yes or No)");
            while(true){
                String playAgain = input.nextLine();
                if(playAgain.equalsIgnoreCase("yes")){
                    break;
                }else{
                    if(playAgain.equalsIgnoreCase("no"))
                        exit(0);
                        else{
                            System.out.println("Enter a valid answer. (Yes or No)");
                        }
                    }
            }
        }
    }
}