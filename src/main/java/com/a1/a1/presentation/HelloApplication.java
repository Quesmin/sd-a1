package com.a1.a1.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene scene;
    private static Stage window;
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("login-view"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, Integer v, Integer v1) throws IOException {
        window.setMinWidth(v);
        window.setMinHeight(v1);
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        System.out.println(HelloApplication.class.getResource(fxml + ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) { launch();}
}