import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("You are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)");

        Scanner input = new Scanner(System.in);
        int opt = 0;
        try {
            opt = Integer.parseInt(input.nextLine());
        }catch(Exception err){
            System.out.println("Not a number...\n" +
                    "You fall down a hole!");
            return;
        }

        if (opt == 1) {
            System.out.println("You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!");
        }
        else {
            if (opt == 2) {
                System.out.println("You approach the cave...\n" +
                        "It is dark and spooky...\n" +
                        "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                        "Hands you a mighty sword!");
              }
                else {
                    System.out.println("Wrong choice...\n" +
                        "You fall down a hole!");
                }
            }
    }
}