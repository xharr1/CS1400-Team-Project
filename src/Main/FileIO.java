package Main;


import java.io.IOException;
import java.nio.file.*;
import java.util.Random;
import java.util.stream.Stream;

public class FileIO {

    private String fileName = "MealsTest.txt";//TODO add getters and setters.

    private Path filePath =  Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + fileName);

    //adding and removeing from file are basicaly the same thing.
    public void addToFile(String meal){
        //todo: add method to add meals to file.
    }

    public void removeFromFile(String meal){
        //todo: add method to remove meals from file.
    }

    public  Object readRandObj(){
        Object[] mealArray = readAllObj();
        Random random = new Random();
        if (mealArray != null) {
            int randIndex = random.nextInt(mealArray.length - 1);
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
