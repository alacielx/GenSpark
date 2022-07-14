package com.example.humansvsgoblinsjavafx;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupMessageBox {
    @FXML
    private Label text;
    @FXML
    private Button closeButton;
    public static Label staticText;
    private static Button staticCloseButton;
    public double i = 0;
    public static Stage newWindow;

    @FXML
    protected void initialize(){
        text.setFont(Font.font(text.getFont().toString(),20));
        staticText = text;
        staticCloseButton = closeButton;
    }
    @FXML
    protected void onHelloButtonClick() {
        newWindow.close();
        if(Main.getPlayer().isDead() || Land.getGoblinCount() == 0){
            Main.endGame();
        }
    }
    public void newWindow(int width, int height) throws IOException {
        newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(PopupMessageBox.class.getResource("popupwindow.fxml"));
        Scene secondScene = null;
        secondScene = new Scene(fxmlLoader.load(), width, height);
        staticText.setLayoutX(14);
        staticText.setLayoutY(14);
        staticText.setPrefSize(width - 28,height - 28 - staticCloseButton.getPrefHeight() - 14);
        staticCloseButton.setLayoutX(width/2 - staticCloseButton.getPrefWidth()/2);
        staticCloseButton.setLayoutY(height - staticCloseButton.getPrefHeight() - 14);



        newWindow.setTitle("Message Output");
        newWindow.setScene(secondScene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();

        newWindow.show();
    }
    public void update() {
        i+= 0.016;
        if(i >= 1){
            i=0;
        }
    }
    public void addText(String message){
        staticText.setText(staticText.getText() + message + "\n");
    }

}