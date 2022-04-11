package com.example.project_07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of the GUI
 */
public class App extends Application {
    /**
     * Represent the scene
     */
    private static Scene scene;

    /**
     * Method that start the whole application
     * @param stage top level class in JavaFX container
     * @throws IOException appears when the files can't be found
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main_menu.fxml"));
            scene = new Scene(fxmlLoader.load(), 1024, 768);
            //adding icon logo in main stage
            stage.setTitle("Milionerzy");
            Image icon = new Image("file:src/main/resources/img/logo.jpg");
            //attach main_menu.css file
            stage.getIcons().add(icon);
            String css = this.getClass().getResource("/CSS/main_menu.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            //set the OnCloseRequest to start the logout method
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that gives option to close the whole game clicking on X icon
     * @param stage top level class in JavaFX container
     */
    public void logout(Stage stage) //Method that shutdown the application
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyjście z gry");
        alert.setHeaderText("Opuszczenie programu");
        alert.setContentText("Czy chcesz opuścić grę ?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    /**
     * Method that starts the whole program
     * @param args arguments given when the program starting
     */
    public static void main(String[] args) {
        launch();
    }
}