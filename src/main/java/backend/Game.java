package backend;

import java.util.ArrayList;

/**
 * Class that represent the whole game
 * @version 1.0
 */
public class Game {
    /**
     * Contains all questions for one game
     */
    private ArrayList<Questions> questions;
    /**
     * Represent the number of question
     */
    private int questionNumber = 0;
    /**
     * Array that contains the value of money you can win
     */
    private final Integer[] money = {100,200,300,500,1000,2000,4000,8000,16000,32000,64000,12500,250000,500000,1000000};
    /**
     * Represent how much money you currently have on your account
     */
    private Integer currentMoney = 0;
    /**
     * Represent the money of the threshold you get
     */
    private Integer thresholdMoney = 0;

    /**
     * Constructor of class Game
     */
    public Game()
    {
        questions = Questions.readyQuestions();
    }

    /**
     * Getter for questions
     * @return ArrayList of questions
     */
    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    /**
     * Method that check is answer is correct
     * @param id represent the id of answer from 0 to 3
     * @param answer contains the user answer
     * @return true when answer is correct and false if not
     */
    public boolean checkAnswer(int id,String answer){
        return questions.get(id).getGoodAnswer().equals(answer);
    }

    /**
     * Method that set how much money the user win
     */
    public void setCurrentMoney() {
        if(questionNumber ==4) thresholdMoney = money[questionNumber];
        else if(questionNumber ==9) thresholdMoney = money[questionNumber];
        else if(questionNumber ==14) thresholdMoney = money[questionNumber];
        currentMoney = money[questionNumber];
    }

    /**
     * Getter
     * @return value of questionNumber
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Method that increment the question
     */
    public void incrementQuestion()
    {
        questionNumber++;
    }

    /**
     * Getter
     * @return value that represent how much money you have
     */
    public Integer getCurrentMoney() {
        return currentMoney;
    }

    /**
     * Getter
     * @return value that represent you current threshold
     */
    public Integer getThresholdMoney() {return thresholdMoney;}
}
