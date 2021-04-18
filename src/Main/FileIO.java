package Main;


import java.io.IOException;
import java.nio.file.*;
import java.util.Random;
import java.util.stream.Stream;

import java.io.*;
import java.util.Scanner;

public class FileIO {

    private String fileLocation = "C:\\Users\\Harrison\\Desktop\\mealTest\\MealPlanner.txt"; //todo: assign default file location.
    private String fileName = "MealsTest.txt";//TODO add getters and setters.
    private Path filePath =  Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + fileName);

    public String addToFile(String meal){
        //todo: add method to add meals to file.
        //Harrison 04/16/2021

        String mealReturn;

        //Create file reader and writer
        Scanner reader = null;
        try {
            reader = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //TROUBLESHOOTING: Read lines to make sure meal is not already in file
        boolean mealInFile = false;
        String mealLine;
        while (reader.hasNextLine() && !mealInFile)
        {
            mealInFile = reader.nextLine().indexOf(meal) > 0;
//            mealLine = reader.nextLine();
/*            if (mealLine.equals(meal))
            {
                mealInFile = true;
                System.out.print(mealInFile);

            }*/
        }



        if (!mealInFile)
        {
            try {
                FileWriter writer = new FileWriter(fileLocation, true);
                writer.write(meal + "\n");
                writer.close();

            }
            catch (IOException e)
            {
                System.out.println("An error occured");
                e.printStackTrace();
            }
            mealReturn = "Meal added to list";
        }
        else
        {
            mealReturn = "Meal is already in list.";
        }

        return mealReturn;

    }

    public void removeFromFile(String meal){
        //todo: add method to remove meals from file.

    }

    public  Object readRandObj(){
        Object[] mealArray = readAllObj();
        Random random = new Random();
        if (mealArray != null) {
            int randIndex = random.nextInt(mealArray.length);
            return mealArray[randIndex];
        }
        return null;
    }

    public Object[] readAllObj() {

        try{
            Stream<String> mealStream = Files.lines(filePath);
            return mealStream.toArray();
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
