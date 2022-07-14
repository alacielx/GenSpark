package com.example.humansvsgoblinsjavafx;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene. Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Main extends Application {
    public static Stage mainStage;
    private static Pane map = new Pane();
    private static Label stats = new Label();
    private static int windowSize = 640;
    private static int mapSize = 20;
    private static int tileSize = windowSize / mapSize;
    private Group tileGroup = new Group();
    private static Human player = new Human();
    private Human p2 = new Human();
    private static List<Entity> entities = new ArrayList<>(){{add(player);}};
    private static Main.rand rand = new Main.rand();
    private Parent createContent2() throws IOException {
        map.setPrefSize (windowSize, windowSize + 60);

        //Create tiles
        map.getChildren().addAll(tileGroup);

        for(int x = 0; x < mapSize; x++){
            for(int y = 0; y < mapSize; y++){
                Land tile = new Land(x,y);
                tileGroup.getChildren().add(tile);
            }
        }

        stats.setText("Hello I'm the stats");
        stats.setFont(Font.font(stats.getFont().toString(),20));
        stats.setTranslateX(0);
        stats.setTranslateY(mapSize * tileSize);
        map.getChildren().add(stats);

        //Create Goblins
        int goblinCount = rand.ints(5, 9);
//        if(goblinCount > (getMapSize() / 2) * getMapSize())
//            goblinCount = (getMapSize() /2) * getMapSize();
        for(int i = 0; i < goblinCount; i++){
            entities.add(new Goblin());
        }

        //Draw all entities
        for(Entity x : entities){
            map.getChildren().add(x);
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();

        return map;
    }
    private void update() {
        List deadEntities = Land.getDeadEntities();
        entities.removeAll(deadEntities);
        map.getChildren().removeAll(deadEntities);

        setStats();
    }

    public void start (Stage stage) throws Exception {
        mainStage = stage;
        Scene scene = new Scene(createContent2());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case A:
                player.regen(player.moveLeft() == true ? 1 : 0);
                player.checkPos();
                if(!player.isDead()){
                    Land.moveGoblins();
                    player.checkPos();
                }
                break;
            case D:
                player.regen(player.moveRight() == true ? 1 : 0);
                player.checkPos();
                if(!player.isDead()){
                    Land.moveGoblins();
                    player.checkPos();
                }
                break;
            case W:
                player.regen(player.moveUp() == true ? 1 : 0);
                player.checkPos();
                if(!player.isDead()){
                    Land.moveGoblins();
                    player.checkPos();
                }
                break;
            case S:
                player.regen(player.moveDown() == true ? 1 : 0);
                player.checkPos();
                if(!player.isDead()){
                    Land.moveGoblins();
                    player.checkPos();
                }
                break;
        }
        });

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void addEntity(Entity e){
        entities.add(e);
        map.getChildren().add(e);
    }
    public void setStats(){
        stats.setText(player.getStats());
    }
    public static void endGame() {
        mainStage.close();
    }
    public static int getWindowSize(){return windowSize;}
    public static int getMapSize(){return mapSize;}
    public static Pane getMap(){return map;}
    public static int getTileSize(){return tileSize;}
    public static List<Entity> getEntities(){return entities;}
    public static Human getPlayer(){return player;}
    public void showMessage() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(new Stage());
        VBox dialogVbox = new VBox(20);
        Group textList = new Group();
        dialogVbox.getChildren().add(textList);

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        textList.getChildren().add(new Text("YO"));
        pause.milli(500);
        textList.getChildren().add(new Text("Yoooo2"));
        pause.milli(500);
        textList.getChildren().add(new Text("YOooooooooo3"));
    }
    public static class rand{
        public int ints(int origin, int end) {
            Random rand = new Random();
            int result;
            result = rand.ints(origin, end + 1).findFirst().getAsInt();
            return result;
        }
    }
    public static class pause{
        public static void milli(int milliseconds){
            try {
                TimeUnit.MILLISECONDS.sleep(milliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}
        }
    }
}
