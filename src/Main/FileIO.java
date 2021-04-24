package Main;


import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import java.io.*;

public class FileIO {

    private final String fileName = "MealsTest.txt";

    public Path getFilePath() {
        return filePath;
    }

    private final Path filePath =  Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + fileName);
    private static ArrayList<Object> mealArray = new ArrayList<>();

    public String addToFile(String meal){
        //Harrison 04/16/2021

        String mealReturn;

        //Create file reader
        Scanner reader = null;
        try {
            reader = new Scanner(new FileReader(String.valueOf(filePath)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        //Read lines to make sure meal is not already in file
        boolean mealInFile = false;
        String mealLine;
        while ((reader != null && reader.hasNextLine()) && !mealInFile)
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
                System.out.println("An error occurred");
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

    public String fileToString(String filePath) {
        String input;
        StringBuilder sb;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    sb.append(input).append("\n");
                } else {
                    sb.append(input);
                }
            }
            return sb.toString();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public String removeFromFile(String meal){
        //Harrison 04/18/2021

        String mealFileText = fileToString(String.valueOf(filePath)).
                replaceAll(meal + "\n", "").
                replaceAll(meal,"").trim();

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(String.valueOf(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Objects.requireNonNull(pw).append(mealFileText);
        pw.flush();

        return "Meal Removed from list\n\n";
    }

    public Object[][] replaceMeal(Object[][] mealArray, int week, int day) {
        mealArray[week][day] = returnRandomObj();
        return mealArray;
    }

    public Object[] readAllObj() {
        //method to read all items in the file.
        //is it worth it to convert this to a array list?
        try{
            Stream<String> mealStream = Files.lines(filePath);
            return mealStream.toArray();
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public Object returnRandomObj(){
        Random random = new Random();
        //check if arraylist is empty, if it is refill it.
        if (mealArray.isEmpty()){
            mealArray = new ArrayList<>(Arrays.asList(readAllObj()));
        }
        //return a random object, removing it from the arraylist to avoid meal repetition.
        int randObject = random.nextInt(mealArray.size());
        return mealArray.remove(randObject);
    }
}
