package com.zaki.autoclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.Timer;

public class Controller implements NativeKeyListener {

    private Timer timer;

    public Controller() {
        timer = new Timer();
    }

    @FXML
    private TextField xPos;

    @FXML
    private TextField yPos;

    @FXML
    private TextField interval;

    @FXML
    private Button startBtn;

    @FXML
    private void onStart(ActionEvent ae) {

        Scene scene = ((Button) ae.getSource()).getScene();
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            onStart(stage);
            registerHooks();
        }
    }

    private void registerHooks() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        }
        catch (Exception ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
        }
    }

    private void onStart(Stage stage) {

        boolean areComponentsInitialized = xPos != null && yPos != null && interval != null && startBtn != null;

        if (areComponentsInitialized) {
            boolean isInputNotEmpty = !"".equals(xPos.getText()) && !"".equals(yPos.getText()) && !"".equals(interval.getText());
            if (isInputNotEmpty) {
                try {
                    int x = Integer.parseInt(xPos.getText());
                    int y = Integer.parseInt(yPos.getText());
                    int i = Integer.parseInt(interval.getText());

                    Rectangle2D screen = Screen.getPrimary().getBounds();
                    boolean isInputValid = x > 0 && x < screen.getMaxX() && y > 0 && y < screen.getMaxY() && i > 0;
                    if (isInputValid) {
                        if (stage != null) {
                            stage.setIconified(true);
                            Clicker c = new Clicker(x, y);
                            if (timer != null) {
                                timer.schedule(c, 0, i);
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO
                }
            }
        }
    }

    @FXML
    public void onStop(ActionEvent ae) {

        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = new Timer();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        if (nativeKeyEvent.getRawCode() == KeyCode.F6.getCode()) {
            onStop(null);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
