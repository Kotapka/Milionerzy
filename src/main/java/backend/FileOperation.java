package backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represent read operation on files
 */
public class FileOperation {
    /**
     * field that contains how many files is going to be read
     */
    private static final int NUMBER_OF_FILES = 15;

    /**
     * Method that get the questions from the files
     * @return ArrayList with questions
     */
    public static ArrayList<String> packingQuestions () {
        ArrayList<String> questions = new ArrayList<>();
        for(int i=1;i<=NUMBER_OF_FILES;i++) {
            try {
                String url = "src/main/resources/questions/"+ i +".csv";
                int lines = (int) Files.lines(Path.of(url)).count();
                String chosenLine = Files.readAllLines(Paths.get(url)).get(generateRandomNumber(lines));
                questions.add(chosenLine);
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
        return questions;
    }

    /**
     * Method that generate random number in bound
     * @param line numbers of line in the file
     * @return random number
     */
    private static int generateRandomNumber(int line){
        Random rn = new Random();
        int answer = rn.nextInt(line);
        return answer;
    }
}