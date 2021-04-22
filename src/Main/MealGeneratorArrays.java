package Main;

import java.time.LocalDate;
import java.util.*;
//A improvement would be to figure out a way for all of the code to reference one array that gets refilled.
public class MealGeneratorArrays {
    public Object[][] genArray(int days){
        //create instances.
        FileIO fileIO = new FileIO();
        Random random = new Random();
        //number of columns, using 7 because there are 7 days in a week.
        int numColumns = 7;
        ArrayList<Object> mealArrayList = new ArrayList<>(Arrays.asList(fileIO.readAllObj()));
        Object[][] mealArrayFinal = new Object[(int)(Math.ceil(days / (double)numColumns))][numColumns];
        int j;
        for (j = 0; j < days / numColumns ;j++){

            for (int i = 0; i < numColumns; i++){
                //put a random meal from the array list to the 2d array, removing it to avoid meal repetition.
                int randIndex = random.nextInt(mealArrayList.size());
                mealArrayFinal[j][i] = mealArrayList.remove(randIndex);
                //check if arraylist is empty, if it is refill it.
                if (mealArrayList.isEmpty()){
                    mealArrayList = new ArrayList<>(Arrays.asList(fileIO.readAllObj()));
                }
            }
        }
        //create last row of the 2d array.
        if (!(days % numColumns == 0)){
            Arrays.fill(mealArrayFinal[mealArrayFinal.length - 1], 0);
            //days - (j * numColumns) is the remaining days that have not been covered by the main loop above.
            for (int i = 0; i < days - (j * numColumns); i++ ){
                int randIndex = random.nextInt(mealArrayList.size());
                mealArrayFinal[j][i] = mealArrayList.remove(randIndex);
                if (mealArrayList.isEmpty()){
                    mealArrayList = new ArrayList<>(Arrays.asList(fileIO.readAllObj()));
                }
            }
        }

        return mealArrayFinal;
    }
    public Object[][] genArray(int month, int year){
        //creating instances.
        FileIO fileIO = new FileIO();
        Random random = new Random();
        LocalDate startOfMonth = LocalDate.of(year,month,1);
        //create starting variables.
        int dayOfWeek = startOfMonth.getDayOfWeek().getValue();
        //calculates number of days that will be needed after the first row is created.
        //(7 - dayOfWeek) is how meany meals are on the first row.
        int days = (startOfMonth.lengthOfMonth() - (7 - dayOfWeek));
        //calculates number of rows needed for the array. dayOfWeek is added to account for the starting 0s.
        int numRows = (int)(Math.ceil(((startOfMonth.lengthOfMonth() + dayOfWeek) / 7.0)));

        ArrayList<Object> mealArrayList = new ArrayList<>(Arrays.asList(fileIO.readAllObj()));
        //create the 2d array
        Object[][] mealMonth = new Object[numRows][7];
        //create first line of the 2d array putting 0s where the month has not started yet.
        Arrays.fill(mealMonth[0], 0);
        for (int i = dayOfWeek; i < 7; i++){
            //put a random meal from the array list to the 2d array, removing it to avoid meal repetition.
            int randIndex = random.nextInt(mealArrayList.size());
            mealMonth[0][i] = mealArrayList.remove(randIndex);
            //check if arraylist is empty, if it is refill it.
            if (mealArrayList.isEmpty()){
                mealArrayList = new ArrayList<>(Arrays.asList(fileIO.readAllObj()));
            }
        }
        //create 2d array for the remaining days
        Object[][] withoutStart = genArray(days);
        //merge the two 2d arrays into the final 2d array.
        for (int i = 1; i < mealMonth.length; i++){
            System.arraycopy(withoutStart[i - 1], 0, mealMonth[i], 0, 7);
        }
        return mealMonth;
    }

    public Object[][] genArray(String month, int year){
        //converts String month to int month and uses genArray
        int intMonth;

        month = month.toLowerCase(Locale.ROOT);

        switch (month) {
            case "january":
            case "jan":
                intMonth = 1;
                break;
            case "february":
            case "feb":
                intMonth = 2;
                break;
            case "march":
            case "mar":
                intMonth = 3;
                break;
            case "april":
            case "apr":
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
            case "aug":
                intMonth = 8;
                break;
            case "september":
            case "sept":
                intMonth = 9;
                break;
            case "october":
            case "oct":
                intMonth = 10;
                break;
            case "november":
            case "nov":
                intMonth = 11;
                break;
            case "december":
            case "dec":
                intMonth = 12;
                break;
            default:
                System.out.println("Invalid month!");
                return null;
        }
        return genArray(intMonth, year);
    }
}
