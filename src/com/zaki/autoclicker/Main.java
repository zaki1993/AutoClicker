package com.zaki.autoclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCREEN_WIDTH = 640;

    private static final int SCREEN_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("autoclicker.fxml"));

        primaryStage.setWidth(SCREEN_WIDTH);
        primaryStage.setMaxWidth(SCREEN_WIDTH);
        primaryStage.setHeight(SCREEN_HEIGHT);
        primaryStage.setMaxHeight(SCREEN_HEIGHT);
        primaryStage.setMaximized(false);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
