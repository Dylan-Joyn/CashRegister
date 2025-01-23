import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Register {
    private static final Denomination[] DENOMINATIONS = {
            new Denomination("HundredThousandNote", 100000.00, "bill", "src/images/HundredThousandNote.png"),
            new Denomination("Hundred Dollar Bill", 100.00, "bill", "src/images/HundredNote.png"),
            new Denomination("Fifty Dollar Bill", 50.00, "bill", "src/images/FiftyNote.png"),
            new Denomination("Twenty Dollar Bill", 20.00, "bill", "src/images/TwentyNote.png"),
            new Denomination("Ten Dollar Bill", 10.00, "bill", "src/images/TenNote.png"),
            new Denomination("Five Dollar Bill", 5.00, "bill", "src/images/FiveNote.png"),
            new Denomination("One Dollar Bill", 1.00, "bill", "src/images/OneNote.png"),
            new Denomination("Quarter", 0.25, "coin", "src/images/Quarter.png"),
            new Denomination("Dime", 0.10, "coin", "src/images/Dime.png"),
            new Denomination("Nickel", 0.05, "coin", "src/images/Nickel.png"),
            new Denomination("Penny", 0.01, "coin", "src/images/Penny.png")
    };

    public Purse makeChange(double amt) {
        Purse purse = new Purse();
        double remaining = amt;

        Arrays.sort(DENOMINATIONS, Comparator.comparingDouble(Denomination::amt).reversed());

        for (Denomination den : DENOMINATIONS) {
            int count = (int)(remaining / den.amt());
            if (count > 0) {
                purse.add(den, count);
                remaining -= count * den.amt();
                remaining = Math.round(remaining * 100.0) / 100.0;
            }
        }

        return purse;
    }

    public static void main(String[] args) {
        Register register = new Register();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Making Change Calculator");

        while (true) {
            System.out.print("\nEnter amount of money $:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            double amount = Double.parseDouble(input);
            if (amount < 0) {
                System.out.println("Please enter a positive amount.");
                continue;
            }

            Purse change = register.makeChange(amount);
            System.out.println("\nChange for $" + String.format("%.2f", amount) + ":");

            System.out.println("Bills:");
            change.getCash().forEach((den, count) -> {
                if (den.form().equals("bill") && count > 0) {
                    System.out.printf("  $%-6.2f x %d = $%.2f%n",
                            den.amt(), count, den.amt() * count);
                }
            });

            System.out.println("\nCoins:");
            change.getCash().forEach((den, count) -> {
                if (den.form().equals("coin") && count > 0) {
                    System.out.printf("  $%-6.2f x %d = $%.2f%n",
                            den.amt(), count, den.amt() * count);
                }
            });

            System.out.printf("Total: $%.2f%n", change.getValue());
        }

        scanner.close();
    }
}
