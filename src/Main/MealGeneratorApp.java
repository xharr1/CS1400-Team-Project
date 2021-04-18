package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MealGeneratorApp {

    public static void main(String[] args) {
        try {
            System.out.printf("%s\n",FileIO.addToFile("Foie Gras"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void mainMenu() {
        //TODO: create main menu
    }
    private static void generatorMenu(){
        //TODO: create main menu
    }
}
