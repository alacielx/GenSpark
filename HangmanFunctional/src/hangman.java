import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class hangman {
    static List<Character> missed;
    static HashMap guessed;
    static String randWord = "", name = "";
    static Scanner input = new Scanner(System.in);
    //---------- PLAY ---------//
    public static void playGame(String playStyle) {
        //RESET
        missed = new ArrayList<>();
        guessed = new HashMap<Character,Integer>();

        System.out.println("What is your name?");
        name = input.nextLine();


        randWord = wordGenerator(playStyle);
        Arrays.stream(randWord.split("")).forEach(x->{if(x.matches("[a-zA-Z]")){guessed.put(Character.toUpperCase(x.charAt(0)),0);}else{guessed.put(x.charAt(0),1);}});
        guess();

        if(!guessed.containsValue(0)){
            System.out.println(name.toUpperCase()+ " YOU WON!");
            checkScore();
            addScore();
        }else{
            System.out.println("Game Over!\nThe answer was: " + randWord);
        }
    }
    public static void guess(){
        drawHangman(missed.size());
        drawLetters();
        if(missed.size() < 6 && guessed.containsValue(0)){
            char guess;
            System.out.print("Guess a letter: ");
            guess = tryChar();
            if(guessed.containsKey(guess)){
                guessed.put(guess,1);
            }else{
                missed.add(guess);
            }
            guess();
        }
    }
    public static String wordGenerator(String playStyle){
        List<String> wordList;
        Stream<String> fileStream = null;

        try {
            //words
            if(playStyle.equals("WORDS")) {
                fileStream = Files.lines(Paths.get(new File("").getAbsolutePath() + "/src/randomWords.txt"));
            }
            //phrases
            if(playStyle.equals("PHRASES")) {
                fileStream = Files.lines(Paths.get(new File("").getAbsolutePath() + "/src/randomPhrases.txt"));
            }
        }catch (IOException e) {
            System.out.println("No file found");
            exit(0);
        }

        wordList = fileStream.filter(x->x.matches("[a-zA-Z '’]+")).collect(Collectors.toList());
        Random rand = new Random();
        return wordList.stream().map(x->x.toUpperCase()).skip(rand.nextInt(wordList.size())).findFirst().get();
    }
    public static void drawHangman(int score) {
        Stream<String> fileStream = null;
        List<String> hangman = new ArrayList();

        try {
            fileStream = Files.lines(Paths.get(new File("").getAbsolutePath() + "/src/hangmanArt.txt"));
        } catch (IOException e) {
            System.out.println("No hangman art found.");
        }

        fileStream.forEach(x->hangman.add(x));
        System.out.println(hangman.get(0+(7*score)));
        System.out.println(hangman.get(1+(7*score)));
        System.out.println(hangman.get(2+(7*score)));
        System.out.println(hangman.get(3+(7*score)));
        System.out.println(hangman.get(4+(7*score)));
        System.out.println(hangman.get(5+(7*score)));
        System.out.println(hangman.get(6+(7*score)));
    }
    public static void drawLetters(){
        System.out.print("[");
        Arrays.stream(randWord.split("")).map(x->(int)guessed.get(x.charAt(0))==1 ? x : "_").forEach(System.out::print);
        System.out.println("]");
        System.out.println("Missed letters: " + missed);
    }
    public static void addScore(){
        try {
            File scoresFile = new File(new File("").getAbsolutePath() + "/src/hangmanScores.txt");

            FileWriter scores = new FileWriter((scoresFile),true);
            if(scoresFile.length() != 0)
                scores.write("\r\n");

            scores.write("Name: " + name + " | Score: " + missed.size());
            scores.close();
        } catch (IOException e) {
            try {
                File newFile = new File("hangmanScores.txt");
                newFile.createNewFile();
                addScore();
            } catch (IOException ex) {
                addScore();
            }
        }
    }
    public static void checkScore(){
        Stream<String> fileStream = null;
        List<Object[]> highScores = new ArrayList();

        File scores = new File(new File("").getAbsolutePath() + "/src/hangmanScores.txt");

        if(scores.length()==0) {
            System.out.println("New High Score!! You missed " + missed.size() + " letters.");
            return;
        }
        try {
            fileStream = Files.lines(scores.toPath());
        } catch (IOException e) {
            checkScore();
        }

        try{
            fileStream.forEach(x-> highScores.add(new Object[] {x.split(" \\| ")[0].split(": ")[1], x.split(" \\| ")[1].split(": ")[1]}));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Scores not in correct format. \"Name: _ | Score: _\"");
            return;
        }

        int highScore = (int)Collections.min((List)highScores.stream().map(x->Integer.parseInt(x[1].toString())).collect(Collectors.toList()));

        if(missed.size() < highScore){
            System.out.println("New High Score!! You missed " + missed.size() + " letters.");
        }else{
            if(missed.size() == highScore){
                System.out.println("You tied the High Score! You missed " + missed.size() + " letters.");
            }else{
                System.out.println("The current High Score is " + highScore + " missed letters. You missed " + missed.size() + " letters.");
            }
        }

    }
    public static char tryChar() {
        String check;

        check = input.nextLine().toUpperCase();
        if(check.length() != 1 || !check.matches("[a-zA-Z]+")){
            //drawHangman(missed.size());
            System.out.print("Please enter a valid character. Guess a letter: ");
            check = String.valueOf(tryChar());
        }else{
            if(missed.contains(check.charAt(0))
                    || guessed.containsKey(check.charAt(0)) && (int)guessed.get(check.charAt(0))==1){
                //drawHangman(missed.size());
                System.out.print("Letter already guessed. Guess a different letter: ");
                check = String.valueOf(tryChar());
            }
        }
        return check.charAt(0);
    }
}
