package org.openjfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String username = null;

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stageInitial) throws IOException {
        stage = stageInitial;
//        scene = new Scene(loadFXML("login"));
        scene = new Scene(loadFXML("initialization"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
        stage.show();

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

}