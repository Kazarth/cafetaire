package View;

import Control.Callback;
import Entities.Food;
import Entities.Ingredient;
import Entities.Supplier;

import java.util.Scanner;

/**
 * An extension of Frame, used by cashiers etc.
 *
 *TODO:
 * Currently cannot add names longer than one word. Issue lies with Scanner, which won't be around for long.
 */
public class Cashier extends Frame {
    public Cashier(Callback callback) {
        super(callback);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            int choice;

            for (int i=0; i<10; i++) {
                System.out.println();
            }

            System.out.println("1 View inventory.");
            System.out.println("2 Add to inventory.");
            System.out.println("3 Remove from inventory.");
            System.out.println("4 Exit");

            choice = scanner.nextInt();

            for (int i=0; i<10; i++) {
                System.out.println();
            }

            switch (choice) {
                case 1:
                    view();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    System.out.println("Sure you want to remove something?");
                    remove();
                    break;
                case 4:
                    System.out.println("Have a pleasant day.");
                    exit = true;
                default:
                    break;
            }
        }
    }

    private void view() {
        Scanner scanner = new Scanner(System.in);
        boolean cancel = false;

        while (!cancel) {
            String choice;

            System.out.println("What kind of data would you like to view?");
            System.out.println("1 Ingredient.");
            System.out.println("2 Food.");
            System.out.println("3 Supplier.");
            System.out.println("4 Cancel.");

            choice = scanner.next();

            for (int i=0; i<10; i++) {
                System.out.println();
            }

            switch (choice) {
                case "1":
                    System.out.println("Which ingredient would you like to inspect?");
                    Ingredient ingredient = (Ingredient)getValue(0, scanner.next());

                    if (ingredient == null) {
                        System.out.println("No currently no such ingredient in storage.");
                    } else {
                        System.out.println("There are " + ingredient.getQuantity() + " packages of " + ingredient.getType());
                    }

                    break;
                case "2":
                    System.out.println("What type of food would you like to inspect?");
                    Food food = (Food)getValue(1, scanner.next());

                    if (food == null) {
                        System.out.println("There's currently no such product in inventory.");
                    } else {
                        System.out.println("There are currently " + food.getQuantity() + " " + food.getType() + "s in storage.");
                    }

                    break;
                case "3":
                    System.out.println("Which supplier would you like to check?");
                    Supplier supplier = (Supplier)getValue(2, scanner.next());

                    if (supplier == null) {
                        System.out.println("No such supplier catalogued.");
                    } else {
                        System.out.println("Supplier " + supplier.getName() + " delivers on " + supplier.getDate() + "s.");
                    }

                    break;
                case "4":
                    cancel = true;
                    break;
                default:
                    for (int i=0; i<10; i++) {
                        System.out.println();
                    }

                    break;
            }
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        boolean cancel = false;

        while (!cancel) {
            String choice;

            System.out.println("1 Add an ingredient.");
            System.out.println("2 Add food.");
            System.out.println("3 Add a supplier.");
            System.out.println("4 Cancel.");

            choice = scanner.next();

            for (int i=0; i<10; i++) {
                System.out.println();
            }

            switch (choice) {
                case "1":
                    System.out.println("Enter a name:");
                    increaseValue(0, scanner.next());
                    System.out.println("Ingredient added.");
                    break;
                case "2":
                    System.out.println("Enter a name:");
                    increaseValue(1, scanner.next());
                    System.out.println("Food added.");
                    break;
                case "3":
                    System.out.println("Enter a name:");
                    increaseValue(2, scanner.next());
                    System.out.println("Supplier added.");
                    break;
                case "4":
                    cancel = true;
                    break;
                default:
                    for (int i=0; i<10; i++) {
                        System.out.println();
                    }
                    break;
            }
        }
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        boolean cancel = false;

        while (!cancel) {
            String choice;

            System.out.println("1 Remove an ingredient.");
            System.out.println("2 Remove food.");
            System.out.println("3 Remove a supplier.");
            System.out.println("4 Cancel.");

            choice = scanner.next();

            for (int i=0; i<10; i++) {
                System.out.println();
            }

            switch (choice) {
                case ("1"):
                    System.out.println("Enter a name:");
                    if (decreaseValue(0, scanner.next())) {
                        System.out.println("Ingredient removed.");
                    } else {
                        System.out.println("No such ingredient to remove.");
                    }
                    break;
                case ("2"):
                    System.out.println("Enter a name:");
                    if (decreaseValue(1, scanner.next())) {
                        System.out.println("Food removed.");
                    } else {
                        System.out.println("No such food to remove.");
                    }
                    break;
                case ("3"):
                    System.out.println("Enter a name:");
                    if (decreaseValue(2, scanner.next())) {
                        System.out.println("Supplier removed.");
                    } else {
                        System.out.println("No such supplier to remove.");
                    }
                    break;
                case "4":
                    cancel = true;
                    break;
                default:
                    for (int i=0; i<10; i++) {
                        System.out.println();
                    }
                    break;
            }
        }
    }
}
