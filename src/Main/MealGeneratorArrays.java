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
            Arrays.fill(mealArrayFinal[mealArrayFinal.length - 1], 0);
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
        FileIO fileIO = new FileIO();
        Random random = new Random();

        LocalDate test = LocalDate.of(year,month,1);

        int days = (test.lengthOfMonth() - (7 - test.getDayOfWeek().getValue()));

        Object[] mealArray = fileIO.readAllObj();

        ArrayList<Object> mealArrayList = new ArrayList<>(Arrays.asList(mealArray));

        Object[][] mealMonth = new Object[(int)(Math.ceil(((test.lengthOfMonth() + test.getDayOfWeek().getValue()) / (double)7)))][7];
        Arrays.fill(mealMonth[0], 0);
        for (int i = test.getDayOfWeek().getValue(); i < 7; i++){
            int randIndex = random.nextInt(mealArrayList.size());
            mealMonth[0][i] = mealArrayList.remove(randIndex);
        }

        Object[][] withoutStart = genArray(days);
        for (int i = 1; i < mealMonth.length; i++){
            System.arraycopy(withoutStart[i - 1], 0, mealMonth[i], 0, 7);
        }
        return mealMonth;
    }
    public Object[][] genArray(String month, int year){
        int intMonth;

        month = month.toLowerCase(Locale.ROOT);

        switch (month) {
            case "january" -> intMonth = 1;
            case "february" -> intMonth = 2;
            case "march" -> intMonth = 3;
            case "april" -> intMonth = 4;
            case "may" -> intMonth = 5;
            case "june" -> intMonth = 6;
            case "july" -> intMonth = 7;
            case "august" -> intMonth = 8;
            case "september" -> intMonth = 9;
            case "october" -> intMonth = 10;
            case "november" -> intMonth = 11;
            case "december" -> intMonth = 12;
            default -> {
                System.out.println("Invalid month!");
                return null;
            }
        }
        return genArray(intMonth, year);
    }
}
