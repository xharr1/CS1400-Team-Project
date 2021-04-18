package Main;

import java.time.LocalDate;
import java.util.*;

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
    public Object[][] genArray(int month, int year){
        //todo: create method to generate a array with meals for each day of the supplied month.(maybe try to avoid repetition?)
        FileIO fileIO = new FileIO();
        Random random = new Random();

        LocalDate test = LocalDate.of(year,month,1);

        int days = (test.lengthOfMonth() - (7 - test.getDayOfWeek().getValue()));

        Object[] mealArray = fileIO.readAllObj();

        ArrayList<Object> mealArrayList = new ArrayList<>(Arrays.asList(mealArray));
        System.out.println((int)(Math.ceil(((test.lengthOfMonth() + test.getDayOfWeek().getValue()) / (double)7))));

        Object[][] mealMonth = new Object[(int)(Math.ceil(((test.lengthOfMonth() + test.getDayOfWeek().getValue()) / (double)7)))][7];
        Arrays.fill(mealMonth[0], 0);
        for (int i = test.getDayOfWeek().getValue(); i < 7; i++){
            int randIndex = random.nextInt(mealArrayList.size());
            mealMonth[0][i] = mealArrayList.remove(randIndex);
        }

        Object[][] withoutStart = genArray(days);
        for (int i = 1; i < mealMonth.length; i++){
            for (int j = 0; j < 7; j++){
                mealMonth[i][j] = withoutStart[i - 1][j];
            }
        }

        return mealMonth;
    }
    public Object[][] genArray(String month, int year){
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
        return genArray(intMonth, year);
    }
}
