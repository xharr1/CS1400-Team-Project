package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class MealGeneratorApp {
    //Harrison 04/18/2021
    public static void main(String[] args) throws IOException{
        FileIO fileIO = new FileIO();
        Scanner input = new Scanner(System.in);
        MealGeneratorArrays mealGeneratorArrays = new MealGeneratorArrays();

        int selection;
        int subMenuSelection;
        String meal;
        String strMonth;
        int intMonth = 0;
        int year = 0;
        int days = 0;
        int week = 0;
        int dayReplaceMeal = 0;
        Object[][] mealPlanArray = new Object[0][];
        //if the meal file does not exist, create it.
        if (!Files.exists(fileIO.getFilePath())){
            try{
                Files.createFile(fileIO.getFilePath());
            }
            catch (IOException e){
                System.out.printf("Could not create file\n" +
                        "check that the program has access to %S\n" +
                        "and that the directory exists.\n", fileIO.getFilePath());
                throw e;
            }
        }
        do {
            //Creates main menu and gets selection from user
            mainMenu();
            System.out.print("Selection: ");
            selection = Integer.parseInt(input.nextLine());
            switch (selection)
            {
                //Switch statement for main menu actions
                //Adds meal to meal list
                case 1:
                    System.out.print("Enter meal to add to list: ");
                    meal = input.nextLine();
                    System.out.print(fileIO.addToFile(meal));
                    break;
                //Removes meal from meal list
                case 2:
                    System.out.print("Enter meal to remove from list: ");
                    meal = input.nextLine();
                    System.out.println(fileIO.removeFromFile(meal));
                    break;
                //Accesses sub menu switch statement for meal plan generation
                case 3:
                    do {
                        System.out.println();
                        generatorMenu();
                        System.out.print("Selection: ");
                        subMenuSelection = Integer.parseInt(input.nextLine());
                        System.out.println();
                        switch (subMenuSelection) {
                            //creates a month long meal plan
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
                                    mealPlanArray = mealGeneratorArrays.genArray(intMonth, year);
                                    printArray(mealPlanArray);
                                } else if (strMonth.equals("0")) {
                                    System.out.println("Invalid Month!");
                                    break;
                                } else {
                                    mealPlanArray = mealGeneratorArrays.genArray(strMonth, year);
                                    if (mealPlanArray == null) {
                                        break;
                                    }
                                    printArray(mealPlanArray);
                                }
                                break;
                            //creates meal plan for set number if days
                            case 2:
                                System.out.print("Enter number of days for meal plan: ");
                                days = Integer.parseInt(input.nextLine());
                                while (days < 1)
                                {
                                    System.out.println("\nInvalid value, please enter a day greater than 1 " +
                                            "Enter a negative number to cancel.\n");
                                    System.out.print("Enter number of days for meal plan: ");
                                    days = Integer.parseInt(input.nextLine());
                                }
                                if (days < 0)
                                {
                                    break;
                                }
                                mealPlanArray = mealGeneratorArrays.genArray(days);
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
                //Prints all meals in meal list
                case 4:
                    System.out.print(Arrays.deepToString(fileIO.readAllObj()) + "\n\n");
                    break;
                //replaces a meal in a previously generated meal plan
                case 5:
                    //Checks if meal plan has been created by verifying meal plan generator values are not 0
                    if (days != 0 || year != 0)
                    {
                        //Initial week input
                        System.out.printf("\nWhat week (1-%d) would you like to replace a meal in: ", mealPlanArray.length);
                        week = Integer.parseInt(input.nextLine());

                        //If week is invalid, requests week again
                        while (week > mealPlanArray.length || week == 0)
                        {
                            System.out.printf("\nInvalid value, please enter a week between 1 and %d. " +
                                    "Enter a negative number to cancel.\n\n", mealPlanArray.length);
                            System.out.printf("What week (1-%d) would you like to replace a meal in: ", mealPlanArray.length);
                            week = Integer.parseInt(input.nextLine());
                        }
                        //Exits if week entered is negative
                        if (week < 0)
                        {
                            break;
                        }

                        //Initial day input
                        System.out.print("What day of the week (1-7) would you like to replace a meal in: ");
                        dayReplaceMeal = Integer.parseInt(input.nextLine());

                        //If day is invalid, requests day again
                        while (dayReplaceMeal > 7 || dayReplaceMeal == 0)
                        {
                            System.out.println("\nInvalid value, please enter a day between 1 and 7. " +
                                    "Enter a negative number to cancel.\n");
                            System.out.print("What day of the week (1-7) would you like to replace a meal in: ");
                            dayReplaceMeal = Integer.parseInt(input.nextLine());
                        }

                        //Exits if day entered is negative
                        if (dayReplaceMeal < 0)
                        {
                            break;
                        }

                        //adjusts day/week entered for array input
                        week--;
                        dayReplaceMeal--;

                        //Replaces meal and prints adjusted meal plan
                        printArray(fileIO.replaceMeal(mealPlanArray, week, dayReplaceMeal));

                    }
                    else
                    {
                        System.out.println("\nPlease generate a meal plan before replacing a meal.\n");
                    }
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
        System.out.println("5 - Replace meal in plan");
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
