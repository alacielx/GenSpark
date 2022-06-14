import java.util.*;
import java.io.*;
import static java.lang.System.exit;

public class hangman {
    static int score;
    static ArrayList<Character> missed, guessed;
    static ArrayList<String> hangman;
    //---------- PLAY ---------//
    public static void playGame(String playStyle) {
        //RESET
        missed = new ArrayList<>();
        guessed = new ArrayList<>();
        score = 0;
        hangman = new ArrayList<>(Arrays.asList("+=======+", "|       |", "|", "|", "|", "|", "------------"));

        String randWord = wordGenerator(playStyle);
        System.out.println(randWord + "\n");

        for(int i =0; i<randWord.length();i++) {
            if(!String.valueOf(randWord.charAt(i)).matches("[a-zA-Z]")){
                guessed.add(randWord.charAt(i));
            }else{
                guessed.add('_');
            }
        }

        while(true) {
            drawHangman(score);

            if(score >= 6){
                System.out.println("Game Over!\nThe answer was: " + randWord);
                return;
            }else{
                if(!guessed.contains('_')){
                    System.out.println("YOU WIN!!");
                    return;
                }
            }

            char guess;
            System.out.print("Guess a letter: ");
            guess = tryChar();
            score += checkGuess(guess,randWord);

        }
    }
    public static String wordGenerator(String playStyle){
        ArrayList<String> wordList = new ArrayList<>();
        Scanner scan = null;
        String filePath = new File("").getAbsolutePath();
        try {
            //phrases
            if(playStyle == "PHRASES") {
                scan = new Scanner(new FileReader(filePath + "/src/randomPhrases.txt"));
            }
            //words
            if(playStyle == "WORDS") {
                scan = new Scanner(new FileReader(filePath + "/src/randomWords.txt"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
            exit(0);
        }

        while(scan.hasNext()){
            String temp = scan.nextLine();
            if(temp.matches("[a-zA-Z '’]+"))
                wordList.add(temp.toUpperCase());
        }
        scan.close();
        Random rand = new Random();
        int randNum = rand.ints(0,wordList.size()).findFirst().getAsInt();
        return wordList.get(randNum);
    }
    public static void drawHangman(int score) {

        switch (score){
            case 1: hangman.set(2,"|        \uD83D\uDE14");
                break;
            case 2: hangman.set(3,"|       |");
                break;
            case 3: hangman.set(3,"|      /|");
                break;
            case 4: hangman.set(3,"|      /|\\");
                break;
            case 5: hangman.set(4,"|      /");
                break;
            case 6: hangman.set(4,"|       /" + " \\");
                break;
        }
        if(!guessed.contains('_') && score >= 1)
            hangman.set(2,"|        \uD83D\uDE0A");
        for(String x : hangman)
            System.out.println(x);
        System.out.println(String.valueOf(guessed).replace(", ",""));
        System.out.println("Missed letters: " + missed);
    }
    public static int checkGuess(char guess,String randWord){

        if(!randWord.contains(String.valueOf(guess))){
            missed.add(guess);
            return 1;
        }

        for(int i = 0; i < randWord.length();i++){
            if(randWord.charAt(i) == guess){
                guessed.set(i,guess);
            }
        }
        return 0;
    }
    public static char tryChar() {
        Scanner input = new Scanner(System.in);
        String check;
        boolean valid;
        do {
            valid = true;
            check = input.nextLine();
            if(check.length() != 1 || !check.matches("[a-zA-Z]+")){
                System.out.print("Please enter a valid character.\nGuess a letter: ");
                valid = false;
            }else{
                if(missed.contains(Character.toUpperCase(check.charAt(0)))
                        || guessed.contains(Character.toUpperCase(check.charAt(0)))){
                    drawHangman(score);
                    System.out.print("Letter already guessed. Guess a different letter: ");
                    valid = false;
                }
            }
        } while (!valid);
        return Character.toUpperCase(check.charAt(0));
    }
}
