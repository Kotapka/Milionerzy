package backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the lifebuoy helping the public
 * @version 1.0
 */
public class Audience {

    /**
     * Static method that generate percent values for each question
     * @param isFiftyToFiftyUsed check if lifebuoy fiftyToFifty was used before
     * @return ArrayList with integer values of each question
     */
    public static ArrayList<Integer> audienceHelp(boolean isFiftyToFiftyUsed)
    {
        ArrayList<Integer> percent = new ArrayList<>();
        if(!isFiftyToFiftyUsed) {
            int p = randomNumber(50, 100);
            percent.add(p);
            int p2 = randomNumber(0, 100 - p);
            percent.add(p2);
            int p3 = randomNumber(0, 100 - (p + p2));
            percent.add(p3);
            int p4 = 100 - (p + p2 + p3);
            percent.add(p4);
        }
        else{
            int p = randomNumber(60, 100);
            percent.add(p);
            percent.add(100 - p);
            percent.add(0);
            percent.add(0);
        }
        return percent;
    }

    /**
     * Method that generates random number in bound
     * @param origin beginning range
     * @param bound end range
     * @return number from range
     */
    private static Integer randomNumber(int origin, int bound)
    {
        Random random = new Random();
        return random.nextInt(origin,bound);
    }
}
