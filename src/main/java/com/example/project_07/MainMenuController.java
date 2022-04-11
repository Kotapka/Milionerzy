package com.example.project_07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * Controller for the Main menu scene
 */
public class MainMenuController {
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

    @FXML
    private AnchorPane scenePane;

    /**
     * Method that change scene to start a game
     * @param event register the event when program works for example. when user click on the answer
     * @throws IOException appears when the file can't be found
     */
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("game.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1024, 768);
        String css = this.getClass().getResource("/CSS/game_scene.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.startGame();
    }

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
}