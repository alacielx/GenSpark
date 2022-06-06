import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Object test;

    public static int tryInt() {
        Object x = null;
        boolean valid = true;
        do {
            try {
//                    x = test;
                x = Integer.parseInt(input.nextLine());
                valid = true;
            } catch (Exception err) {
                System.out.println("Invalid input. Try again.");
                valid = false;
            }
        } while (!valid);
        return (int)x;
    }

    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you, you see two caves.\n" +
                "In one cave, the dragon is friendly and will share his treasure with you.\n" +
                "The other dragon is greedy and hungry and will eat you on sight.\n\n" +
                "Which cave will you go into? (1 or 2)");

        int opt = 0;
        opt = tryInt();

        if (opt == 1) {
            System.out.println("You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!\n[YOU DIED]");
        }
        else {
            if (opt == 2) {
                System.out.println("You approach the cave...\n" +
                        "It is dark and spooky...\n" +
                        "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                        "Hands you a mighty sword!\n[YOU GAIN AN ALLY]");
              }
                else {
                    System.out.println("Wrong choice...\n" +
                        "You fall down a hole!");
                }
            }
    }
}