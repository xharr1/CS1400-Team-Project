package Main;


import java.io.IOException;
import java.nio.file.*;
import java.util.Random;
import java.util.stream.Stream;

import java.io.*;
import java.util.Scanner;

public class FileIO {

    private String fileName = "MealsTest.txt";
    private Path filePath =  Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + fileName);

    public String addToFile(String meal){
        //Harrison 04/16/2021

        String mealReturn;

        //Create file reader
        Scanner reader = null;
        try {
            reader = new Scanner(new FileReader(String.valueOf(filePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Read lines to make sure meal is not already in file
        boolean mealInFile = false;
        String mealLine;
        while (reader.hasNextLine() && !mealInFile)
        {
//            mealInFile = reader.nextLine().indexOf(meal) > 0;
            mealLine = reader.nextLine();
            if (mealLine.equals(meal))
            {
                mealInFile = true;
            }
        }

        //if meal is in file, ignore, else add to file. Create return value
        if (!mealInFile)
        {
            try {
                FileWriter writer = new FileWriter(String.valueOf(filePath), true);
                writer.write("\n" + meal);
                writer.close();

            }
            catch (IOException e)
            {
                System.out.println("An error occured");
                e.printStackTrace();
            }
            mealReturn = "Meal added to list\n\n";
        }
        else
        {
            mealReturn = "Meal is already in list\n\n";
        }

        return mealReturn;

    }

    public String fileToString(String filePath) throws FileNotFoundException {
        String input = null;
        Scanner scanner = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (scanner.hasNextLine())
        {
            input = scanner.nextLine();
            if (scanner.hasNextLine())
            {
                sb.append(input + "\n");
            }
            else
            {
                sb.append(input);
            }
        }

        return sb.toString();
    }

    public String removeFromFile(String meal) throws FileNotFoundException {
        //todo: add method to remove meals from file.
        //Harrison 04/18/2021

        String mealFileText = fileToString(String.valueOf(filePath)).
                replaceAll(meal + "\n", "").
                replaceAll(meal,"").trim();

        PrintWriter pw = new PrintWriter(String.valueOf(filePath));

        pw.append(mealFileText);
        pw.flush();

        return "Meal Removed from list\n\n";
    }

    public Object[][] replaceMeal(Object[][] mealArray, String meal) {
        return null;
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
