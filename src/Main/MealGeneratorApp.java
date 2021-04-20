package Main;

import java.util.Arrays;
import java.util.Scanner;

public class MealGeneratorApp {
    //Harrison 04/18/2021
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        Scanner input = new Scanner(System.in);

        int selection;
        int subMenuSelection;
        String meal;
        String strMonth;
        int intMonth = 0;
        int year;
        int days;

        MealGeneratorArrays mealGeneratorArrays = new MealGeneratorArrays();

        do {
            mainMenu();
            System.out.print("Selection: ");
            selection = Integer.parseInt(input.nextLine());
            switch (selection)
            {
                case 1:
                    System.out.print("Enter meal to add to list: ");
                    meal = input.nextLine();
                    System.out.print(fileIO.addToFile(meal));
                    break;
                case 2:
                    System.out.print("Enter meal to remove from list: ");
                    meal = input.nextLine();
                    System.out.println(fileIO.removeFromFile(meal));
                    break;
                case 3:
                    do {
                        System.out.println();
                        generatorMenu();
                        System.out.print("Selection: ");
                        subMenuSelection = Integer.parseInt(input.nextLine());
                        System.out.println();
                        switch (subMenuSelection) {
                            case 1:
                                System.out.print("Enter month as number (1-12) or string (January-December): ");
                                strMonth = input.nextLine();
                                System.out.print("Enter year: ");
                                year = Integer.parseInt(input.nextLine());
                                System.out.println();
                                char[] chars = strMonth.toCharArray();
                                for (char c : chars) {
                                    if (Character.isDigit(c)) {
                                        intMonth = Integer.parseInt(strMonth);
                                    }
                                }
                                if (!(intMonth > -1 && intMonth < 13)) {
                                    System.out.println("Invalid Month!");
                                    break;
                                }
                                if (intMonth != 0) {
                                    Object[][] mealPlanArray = mealGeneratorArrays.genArray(intMonth, year);
                                    printArray(mealPlanArray);
                                } else if (strMonth.equals("0")) {
                                    System.out.println("Invalid Month!");
                                    break;
                                } else {
                                    Object[][] mealPlanArray = mealGeneratorArrays.genArray(strMonth, year);
                                    if (mealPlanArray == null) {
                                        break;
                                    }
                                    printArray(mealPlanArray);
                                }
                                break;
                            case 2:
                                System.out.print("Enter number of days for meal plan: ");
                                days = Integer.parseInt(input.nextLine());
                                Object[][] mealPlanArray = mealGeneratorArrays.genArray(days);
                                printArray(mealPlanArray);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid input, select another value");
                                break;
                        }
                    }
                    while (subMenuSelection != 0);
                    break;
                case 4:
                    System.out.print(Arrays.deepToString(fileIO.readAllObj()) + "\n\n");
                    break;
                case 5:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid input, select another value\n");
                    break;
            }
        } while (selection != 0);

    }
    private static void mainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1 - Add meal to list");
        System.out.println("2 - Remove meal from list");
        System.out.println("3 - Generate meal plan");
        System.out.println("4 - Print all meals in list");
        System.out.println("0 - Exit");
    }
    private static void generatorMenu(){
        System.out.println("Meal Plan Menu:");
        System.out.println("1 - Meal plan for whole month");
        System.out.println("2 - Meal plan for set number of days");
        System.out.println("0 - Exit to Main Menu");
    }
    private static void printArray(Object[][] mealPlanArray){
        //method to print array in tab delimited format.
        System.out.println();
        System.out.println(Arrays.deepToString(mealPlanArray).
                replaceAll("], ", "\n").
                replaceAll("\\[", "").
                replaceAll("]]", "").
                replaceAll("\\[", "").
                replaceAll(", ", "\t"));
        System.out.println();
    }
}
