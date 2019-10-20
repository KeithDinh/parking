import java.util.Scanner;

public class Main {

    public static void main(String[] argv)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("There are 5 inputs to test, please enter a number from 1 to 5: ");
        int testcase;
        while (true) {
            try {
                testcase = Integer.parseInt(scanner.next());
                if(testcase < 1 || testcase >5)
                    throw new NumberFormatException();
                System.out.println("Test " + Integer.toString(testcase));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Re-enter:  ");
                scanner = new Scanner(System.in);
            }

        }
        Parking_Lot parking = new Parking_Lot(testcase);

    }
}
