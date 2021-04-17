package Main;

import java.util.Arrays;

public class MealGeneratorApp {

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        System.out.println(Arrays.toString(fileIO.readAllObj()));
        System.out.println(fileIO.readRandObj());
    }
    private static void mainMenu() {
        //TODO: create main menu
    }
    private static void generatorMenu(){
        //TODO: create main menu
    }
}
