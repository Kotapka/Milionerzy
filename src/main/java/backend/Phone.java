package backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * Phone class represent the lifebuoy phone to friend
 * @version 1.0
 */
public class Phone {

    /**
     * Static method that return the right question
     * @param question contains object question
     * @param number contains number of the current question
     * @return string with good answer
     */
    public static String phoneHelp(Questions question, int number)
    {
        if(number < 5)
            return question.getGoodAnswer();
        else{
            ArrayList<String> questions = new ArrayList<>();
            questions.add(question.getAnswer1());
            questions.add(question.getAnswer2());
            questions.add(question.getAnswer3());
            questions.add(question.getAnswer4());
            return questions.get(randomNumber());
        }
    }

    /**
     * Method that generates random numbers
     * @return random number in bound 0 - 3
     */
    private static Integer randomNumber()
    {
        Random random = new Random();
        return random.nextInt(4);
    }
}
