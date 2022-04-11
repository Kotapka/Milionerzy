package backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represent lifebuoy 50/50
 * @version 1.0
 */
public class FiftyToFifty{
    /**
     * variable that show if the lifebuoy was used
     */
    private static boolean isUsed = false;
    /**
     * ArrayList that contains answers for one question
     */
    private static ArrayList<String> answer = new ArrayList<>();

    /**
     * Static method that from 4 answers gets 2 false answer
     * @param question represent the actually active question
     * @return ArrayList with only false answers
     */
    public static ArrayList<String> activeHelper(Questions question) {
        answer.add(question.getAnswer1());
        answer.add(question.getAnswer2());
        answer.add(question.getAnswer3());
        answer.add(question.getAnswer4());
        answer.remove(question.getGoodAnswer());
        answer.remove(answer.get(randomNumber()));
        return answer;
    }

    /**
     * Setter that set used value
     * @param used contains if lifebuoy was used
     */
    public static void setUsed(boolean used){
        isUsed = used;
    }

    /**
     * Getter
     * @return value of isUsed
     */
    public static boolean getUsed(){
        return isUsed;
    }

    /**
     * Method that generates random number in bound 0 - 3
     * @return random number
     */
    private static Integer randomNumber()
    {
        Random random = new Random();
        return random.nextInt(3);
    }
}
