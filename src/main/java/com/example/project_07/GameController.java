package com.example.project_07;

import backend.Audience;
import backend.FiftyToFifty;
import backend.Game;
import backend.Phone;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents the controller that handling the game GUI
 */
public class GameController {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button answer1;
    @FXML
    private Button answer2;
    @FXML
    private Button answer3;
    @FXML
    private Button answer4;
    @FXML
    private Text questionText;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button half_to_half;
    @FXML
    private Button phone;
    @FXML
    private Button audience;
    @FXML
    private BarChart chart;
    @FXML
    private Pane background;
    @FXML
    private Button endGame;
    @FXML
    private Text winPrice;

    /**
     * top level class in JavaFX container
     */
    private Stage stage;
    /**
     * container for all content in scene graph
     */
    private Scene scene;
    /**
     * the base class for all nodes that have children
     */
    private Parent root;
    /**
     * represent the game object
     */
    private Game game;
    /**
     * contains the pair of button and value for example. answer1 = 'A'
     */
    HashMap<String, String> idButtons = new HashMap();
    /**
     * contains all answers buttons
     */
    ArrayList<Button> buttons = new ArrayList<>();
    /**
     * Contains all nodes from gridPane
     */
    ArrayList<Text> textes = new ArrayList<>();

    /**
     * Method that close the program
     * @param event register the event when program works for example. when user click on the answer
     */
    public void logout(ActionEvent event)
    {
        //Create new object Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Set title
        alert.setTitle("Wyjście z gry");
        //Set HeaderText
        alert.setHeaderText("Opuszczenie programu");
        //Set contentText
        alert.setContentText("Czy chcesz opuścić grę ?");

        //Check what button was clicked and then shutdown the program or program still running
        if(alert.showAndWait().get() == ButtonType.OK) {
            //Get the active stage
            stage = (Stage) scenePane.getScene().getWindow();
            //Shutdown the stage
            stage.close();
        }
    }

    /**
     * Method that delay switching between scenes
     * @param e register the event when program works for example. when user click on the answer
     * @param scene container for all content in scene graph
     */
    private void delaySwitchToScene(ActionEvent e, Integer scene)
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            try {
                if(scene == 1)
                    switchToSceneLose(e);
                else if(scene == 2)
                    switchToSceneWin(e, game.getThresholdMoney());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }


    /**
     * Method that set questions and answers in GUI
     * @param game represent the object of the game
     * @param i represents the current question
     */
    public void setValuesFirst(Game game, int i)
    {
        answer1.setText(game.getQuestions().get(i).getAnswer1());
        answer2.setText(game.getQuestions().get(i).getAnswer2());
        answer3.setText(game.getQuestions().get(i).getAnswer3());
        answer4.setText(game.getQuestions().get(i).getAnswer4());
        questionText.setText(game.getQuestions().get(i).getQuestionContent());
        chart.setVisible(false);
        background.setVisible(false);
    }

    /**
     * Method that set values in GUI but with delay
     * @param game represent the object of the game
     * @param i represents the current question
     */
    public void setValues(Game game,int i)
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            setValuesFirst(game, i);
        });
        pause.play();
    }

    /**
     * Method that gets all Text components from gridPane
     * @return Arraylist of nodes
     */
    private ArrayList<Text> getNodesByCoordinate() {
        ArrayList<Text> matchingNodes = new ArrayList<>();
        for(Integer i=1; i<15;i++) {
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == i && (GridPane.getColumnIndex(node) == null || GridPane.getColumnIndex(node) == 1)) {
                    if(GridPane.getColumnIndex(node) != null)
                        matchingNodes.add((Text)node);
                }
            }
        }
        return matchingNodes;
    }

    /**
     * Method that start the game
     */
    public void startGame(){
        setButtons();
        textes = getNodesByCoordinate();
        game = new Game();
        setValuesFirst(game, 0);
    }

    /**
     * Method that handling all the answer buttons
     * @param e event that contains the current pressed button
     */
    public void answerButtonService(ActionEvent e){
        Button button = (Button)e.getTarget();
        String userAnswer =  button.getText();
        if (game.checkAnswer(game.getQuestionNumber(), userAnswer)) {
            if (game.getQuestionNumber() == 14) {
                delaySwitchToScene(e, 3);
            } else {
                game.setCurrentMoney();
                goodAnswer(button);
                starUpdate();
                game.incrementQuestion();
                setValues(game, game.getQuestionNumber());
            }
        } else {
            badAnswer(button);
            if (answer1.getText().equals(game.getQuestions().get(game.getQuestionNumber()).getGoodAnswer()))
                goodAnswer(answer1);
            else if (answer2.getText().equals(game.getQuestions().get(game.getQuestionNumber()).getGoodAnswer()))
                goodAnswer(answer2);
            else if (answer3.getText().equals(game.getQuestions().get(game.getQuestionNumber()).getGoodAnswer()))
                goodAnswer(answer3);
            else
                goodAnswer(answer4);

            if(game.getThresholdMoney() == 0)
                delaySwitchToScene(e, 1);
            else
                delaySwitchToScene(e, 2);
        }
    }

    /**
     * Method that updates star in money board
     */
    private void starUpdate(){
        textes.get(13-game.getQuestionNumber()).setText("*");
    }

    /**
     * Method that give user option to leave the game with current money value
     * @param event register the event when program works for example. when user click on the answer
     * @throws IOException appears when the file can't be found
     */
    public void resigneGame(ActionEvent event) throws IOException {
        switchToSceneWin(event, game.getCurrentMoney());
    }

    /**
     * Method that change scene to another when user win money
     * @param event register the event when program works for example. when user click on the answer
     * @param money contains how much money the user win
     * @throws IOException appears when the file can't be found
     */
    public void switchToSceneWin(ActionEvent event, Integer money) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("win_scene.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1024, 768);
        winPrice.setText(money.toString());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that change scene when user lose the game and don't win any money
     * @param event register the event when program works for example. when user click on the answer
     * @throws IOException appears when the file can't be found
     */
    public void switchToSceneLose(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("lose_scene.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that marks the good answer
     * @param btn reference to btn with good answer
     */
    private void goodAnswer(Button btn) {
        btn.setStyle("-fx-background-color: lightgreen");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            btn.setStyle(null);
        });
        pause.play();
    }

    /**
     * Method that mark the bad answer
     * @param btn reference to btn with bad answer that user clicked
     */
    private void badAnswer(Button btn)
    {
        btn.setStyle("-fx-background-color: #f25a5a");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            btn.setStyle(null);
        });
        pause.play();
    }

    /**
     * Method that run lifebuoy 50/50
     * @param event register the event when program works for example. when user click on the answer
     */
    public void halfHelper(ActionEvent event)
    {
        ArrayList<String> removeQuestion = FiftyToFifty.activeHelper(game.getQuestions().get(game.getQuestionNumber()));
        half_to_half.setDisable(true);
        FiftyToFifty.setUsed(true);
        half_to_half.setStyle("-fx-background-color: red");
        if(removeQuestion.contains(answer1.getText()))
            answer1.setText("");
        if(removeQuestion.contains(answer2.getText()))
            answer2.setText("");
        if(removeQuestion.contains(answer3.getText()))
            answer3.setText("");
        if(removeQuestion.contains(answer4.getText()))
            answer4.setText("");
    }

    /**
     * Method that run lifebuoy phone to friend
     * @param event register the event when program works for example. when user click on the answer
     */
    public void phoneHelper(ActionEvent event) //repaired now working good with 50:50
    {
        String answer = Phone.phoneHelp(game.getQuestions().get(game.getQuestionNumber()), game.getQuestionNumber());
        phone.setDisable(true);
        phone.setStyle("-fx-background-color: red");
        if(Objects.equals(answer, answer1.getText()))
            goodAnswer(answer1);
        else if(Objects.equals(answer, answer2.getText()))
            goodAnswer(answer2);
        else if(Objects.equals(answer, answer3.getText()))
            goodAnswer(answer3);
        else if(Objects.equals(answer, answer4.getText()))
            goodAnswer(answer4);
    }

    /**
     * Method that run lifebuoy helping the public
     * @param event register the event when program works for example. when user click on the answer
     */
    public void audienceHelper(ActionEvent event){ //dodać obsługę 50 na 50 żeby były tylko dwa pytania
        ArrayList<Integer> values = Audience.audienceHelp(FiftyToFifty.getUsed());
        audience.setDisable(true);
        chart.setVisible(true);
        background.setVisible(true);
        audience.setStyle("-fx-background-color: red");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        chart.setTitle("Publiczność");
        xAxis.setLabel("Odpowiedź");
        yAxis.setLabel("Procent");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Odpowiedzi");
        if(!FiftyToFifty.getUsed()) {
            int i = 1;
            for(Button b:buttons) {
                if(b.getText().equals(game.getQuestions().get(game.getQuestionNumber()).getGoodAnswer()))
                {
                    series1.getData().add(new XYChart.Data(idButtons.get(b.getId()), values.get(0)));
                }
                else{
                    series1.getData().add(new XYChart.Data(idButtons.get(b.getId()), values.get(i)));
                    i++;
                }
            }
        }
        else{
            for(Button b:buttons){
                if(b.getText().equals(game.getQuestions().get(game.getQuestionNumber()).getGoodAnswer()))
                {
                    series1.getData().add(new XYChart.Data(idButtons.get(b.getId()), values.get(0)));
                }
                else if(b.getText().isEmpty())
                {
                    series1.getData().add(new XYChart.Data(idButtons.get(b.getId()), values.get(2)));
                }
                else{
                    series1.getData().add(new XYChart.Data(idButtons.get(b.getId()), values.get(1)));
                }
            }
        }
        chart.getData().add(series1);
    }

    /**
     * Method that add buttons to ArrayList
     */
    private void setButtons()
    {
        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);
        idButtons.put("answer1","A");
        idButtons.put("answer2","B");
        idButtons.put("answer3","C");
        idButtons.put("answer4","D");
    }
}
