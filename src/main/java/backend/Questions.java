package backend;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that represents one Question
 * @author Konrad Jop, Barłomiej Kotapka
 * @version 1.0
 */
public class Questions {
    /**
     * variable that contains the content of the question
     */
    private String questionContent;
    /**
     * variable that contains one of the answers
     */
    private String answer1;
    /**
     * variable that contains one of the answers
     */
    private String answer2;
    /**
     * variable that contains one of the answers
     */
    private String answer3;
    /**
     * variable that contains one of the answers
     */
    private String answer4;
    /**
     * variable that contains good answers
     */
    private String goodAnswer;

    /**
     * Constructor of question class
     * @param questionContent contains the content of the question
     * @param answer1 that contains one of the answers
     * @param answer2 that contains one of the answers
     * @param answer3 that contains one of the answers
     * @param answer4 that contains one of the answers
     * @param goodAnswer that contains good answers
     */
    public Questions(String questionContent, String answer1, String answer2, String answer3, String answer4,String goodAnswer) {
        this.questionContent = questionContent;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.goodAnswer = goodAnswer;
    }

    /**
     * Method that prepare questions for the game
     * @return ArrayList that contains questions after changes
     */
    public static ArrayList<Questions> readyQuestions() {
        ArrayList<Questions> readyQuestions = new ArrayList<>();
        ArrayList<String> questionsBeforeChanges = FileOperation.packingQuestions();

        for(String i:questionsBeforeChanges)
        {
            String[] splittingString = i.split("[;]");
            ArrayList<String> tab = new ArrayList<>();
            tab.add(splittingString[1]);
            tab.add(splittingString[2]);
            tab.add(splittingString[3]);
            tab.add(splittingString[4]);
            Collections.shuffle(tab);
            readyQuestions.add(new Questions(splittingString[0], tab.get(0), tab.get(1), tab.get(2), tab.get(3), splittingString[4]));
            tab.clear();
        }
        return readyQuestions;
    }

    /**
     * Override method toString that
     * @return ready to use string
     */
    @Override
    public String toString() {
        return "Questions{" +
                "questionContent='" + questionContent + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                '}';
    }

    /**
     * Getter
     * @return content of the question
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * Getter
     * @return content of answer 1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Getter
     * @return content of answer 2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Getter
     * @return content of answer 3
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * Getter
     * @return content of answer 4
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * Getter
     * @return content of good answer
     */
    public String getGoodAnswer() {
        return goodAnswer;
    }
}
