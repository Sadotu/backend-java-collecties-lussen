import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] alphabetic = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero"};
        Integer[] numeric = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        boolean play = true;

        Translator numberWord = new Translator(alphabetic, numeric);

        while(play) {
            play = keepPlaying(scanner, play);
            if(!play) {break;}
            String printableString = numToText(scanner, numberWord);
            System.out.println(printableString);
        }

        HashSet<Integer> secretNumber = randomNumberGenerator();
        String stringNumber = hashSetToString(secretNumber);
        boolean guessAgain = true;
        int attempts = 0;
        System.out.println("take a guess");

        while (guessAgain && attempts < 10) {
            attempts++;
            System.out.println("Attempt: " + attempts + "/10");
            String guessString = makeGuess(scanner);
            String printableString = feedback(stringNumber, guessString);
            System.out.println(printableString);
            if(printableString.equals("Guessed!")){guessAgain = false;}
        }

        // Een Hashset is geen goede keuze voor deze opdracht omdat het geen volgorde garandeert voor de elementen.
        // Dit betekent dat de volgorde van de nummers in de hashset kan veranderen elke keer dat we hem omzetten naar een string.
        // Dit maakt het moeilijker om te controleren of de gebruiker het juiste nummer op de juiste plek heeft geraden.
        // Een betere keuze zou zijn om een ArrayList te gebruiken, die wel een vaste volgorde heeft voor de elementen.

    }

    public static boolean keepPlaying(Scanner scanner, boolean playing) {
        System.out.println("Type 'x' om te stoppen of 'v' om te vertalen");
        String value = scanner.nextLine();
        if(value.equals("x"))
            playing = false;
        else if (value.equals("v"))
            playing = true;
        else {
            System.out.println("Invalid input, please input 'x' or 'v'.");
            playing = keepPlaying(scanner, playing);
        }
        return playing;
    }

    public static String numToText(Scanner scanner, Translator numberWord) {
        System.out.println("Type een cijfer in van 0 t/m 9");
        String str = null;

        try {
            int value = scanner.nextInt();
            if (value < 10) {
                String result = numberWord.translate(value);
                str = "De vertaling van " + value + " is " + result;
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Invalid input, please input a number from 0 to 9");
            scanner.nextLine();
            numToText(scanner, numberWord);
        }
        return str;
    }

    public static String makeGuess(Scanner scanner) {
        String input = scanner.nextLine();
        if (!input.matches("^[0-9]{4}$")) {
            System.out.println("Invalid input, must be 4 digits and only consist of numbers.");
            input = makeGuess(scanner);
        }
        return input;


        /*try {
            input = scanner.nextLine();
            if (input.length() != 4) {
                throw new Exception("Input must have 4 characters.");
            }
            int number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, all characters must be numbers.");
            input = makeGuess(scanner);
        } catch (Exception e) {
            System.out.println("Invalid input, " + e.getMessage());
            input = makeGuess(scanner);
        }*/
    }


    public static HashSet<Integer> randomNumberGenerator() {
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < 4) {
            int number = random.nextInt(10);
            set.add(number);
        }
        return set;
    }

    public static String hashSetToString(HashSet<Integer> set) {
        StringBuilder sb = new StringBuilder();
        for (int number : set) {
            sb.append(number);
        }
        return sb.toString();
    }

    /*
     Deze methode is voor de bonus opdracht.
     */
    public static String feedback(String stringNumber, String guessString) {
        StringBuilder feedback = new StringBuilder();
        if (Objects.equals(guessString, stringNumber)) {
            feedback.delete(0, 4);
            feedback.append("Guessed!");
        } else {
            for (int i = 0; i < 4; i++) {
                if (guessString.substring(i, i + 1).equals(stringNumber.substring(i, i + 1))) {
                    feedback.append("+");
                } else if (stringNumber.contains(guessString.substring(i, i + 1))) {
                    feedback.append("0");
                } else {
                    feedback.append("X");
                }
            }
        }
        return feedback.toString();
    }
}
