package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MealGeneratorArrays {
    public Object[][] genArray(int days){
        FileIO fileIO = new FileIO();
        Random random = new Random();

        Object[] mealArray = fileIO.readAllObj();
        int rowSize = 7;
        ArrayList<Object> mealArrayList = new ArrayList<>(Arrays.asList(mealArray));
        Object[][] mealArrayFinal = new Object[(int)(Math.ceil((days / (double)rowSize)))][rowSize];
        int j;
        for (j = 0; j < days / rowSize ;j++){

            for (int i = 0; i < rowSize; i++){
                int randIndex = random.nextInt(mealArrayList.size());
                mealArrayFinal[j][i] = mealArrayList.remove(randIndex);
                if (mealArrayList.isEmpty()){
                    mealArrayList = new ArrayList<>(Arrays.asList(mealArray));
                }
            }
        }
        if (!(days % rowSize == 0)){
            Arrays.fill(mealArrayFinal[mealArrayFinal.length - 1], (Object)0);
            for (int i = 0; i < days - (j * rowSize); i++ ){
                int randIndex = random.nextInt(mealArrayList.size());
                mealArrayFinal[j][i] = mealArrayList.remove(randIndex);
                if (mealArrayList.size() == 0){
                    mealArrayList = new ArrayList<>(Arrays.asList(mealArray));
                }
            }
        }

        return mealArrayFinal;
    }
    public Object[][] genArray(int month, int day){
        //todo: create method to generate a array with meals for each day of the supplied month.(maybe try to avoid repetition?)
        return null;
    }
    public Object[][] genArray(String month, int day){
        int intMonth;

        month = month.toLowerCase(Locale.ROOT);

        switch (month){
            case "january" :
                intMonth = 1;
                break;
            case "february":
                intMonth = 2;
                break;
            case "march":
                intMonth = 3;
                break;
            case "april":
                intMonth = 4;
                break;
            case "may":
                intMonth = 5;
                break;
            case "june":
                intMonth = 6;
                break;
            case "july":
                intMonth = 7;
                break;
            case "august":
                intMonth = 8;
                break;
            case "september":
                intMonth = 9;
                break;
            case "october":
                intMonth = 10;
                break;
            case "november":
                intMonth = 11;
                break;
            case "december":
                intMonth = 12;
                break;
            default:
                intMonth = 0;
        }
        return genArray(intMonth, day);
    }
}
