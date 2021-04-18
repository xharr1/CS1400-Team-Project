package Main;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MealGeneratorApp {

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();

/*        System.out.println(Arrays.toString(fileIO.readAllObj()));
        System.out.println(fileIO.readRandObj());
        MealGeneratorArrays mealGeneratorArrays = new MealGeneratorArrays();
        Object[][] mealDayArray = mealGeneratorArrays.genArray(50);
        for (Object[] objects : mealDayArray) {
            System.out.println(Arrays.toString(objects));
        }*/

        try {
            System.out.println(fileIO.removeFromFile("Foie Gras"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(fileIO.addToFile("Foie Gras"));
        System.out.println(fileIO.addToFile("Chicken Parm"));
    }
    private static void mainMenu() {
        //TODO: create main menu
    }
    private static void generatorMenu(){
        //TODO: create main menu
    }
}
