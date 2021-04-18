package Main;

import java.io.*;
import java.util.Scanner;

public class FileIO {
    private String fileLocation = "C:\\Users\\Harrison\\Desktop\\mealTest\\MealPlanner.txt"; //todo: assign default file location.
    private File mealFile = new File(fileLocation); //todo: add filename


    public String addToFile(String meal) throws FileNotFoundException, IOException {
        //todo: add method to add meals to file.
        //Harrison 04/16/2021

        String mealReturn;

        //Create file reader and writer
        Scanner reader = new Scanner(new FileReader(fileLocation));


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

    public String readRandObj(){
        //todo: add method to read and return a random object from the file.
        return null;
    }

    public String[] readAllObj(){
        //todo: add method to read and return all objects in file as a array.
        return null;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }


}
