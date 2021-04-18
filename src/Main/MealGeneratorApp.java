package Main;

import java.util.Arrays;

public class MealGeneratorApp {

    public static void main(String[] args) {

        System.out.printf("%s\n",FileIO.addToFile("Foie Gras"));


        FileIO fileIO = new FileIO();
        System.out.println(Arrays.toString(fileIO.readAllObj()));
        System.out.println(fileIO.readRandObj());
        MealGeneratorArrays mealGeneratorArrays = new MealGeneratorArrays();
        Object[][] mealDayArray = mealGeneratorArrays.genArray(50);
        for (Object[] objects : mealDayArray) {
            System.out.println(Arrays.toString(objects));
        }
    }
    private static void mainMenu() {
        //TODO: create main menu
    }
    private static void generatorMenu(){
        //TODO: create main menu
    }
}
