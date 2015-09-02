package application.core;

import application.Main;


import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by Thomas on 01-09-15.
 */
public class Game {
    protected int score;
    protected LevelFactory levelFactory;
    protected int levelNumber;
    protected Level level;
    protected Player tPlayer;
    protected boolean tPaused;


    public Game() {
        levelFactory = new LevelFactory();
        levelNumber = 0;
        tPlayer = new Player();

    }

//    private void installEventHandler() {
//        eventHandler =
//        };
//    }



    public void setScore(int value) {
        score += value;
    }

    protected void nextLevel() {
        levelNumber++;
        level = levelFactory.buildLevel(levelNumber);
        Main.loadScene("level");
        Main.primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.ESCAPE) {
                    // show menu
                } else if (code == KeyCode.P) {
                    tPaused = true;
                }
            }
        });
    }

    public Level getLevel() {
        return level;
    }

    public void newGame() {
        levelNumber = 0;
        nextLevel();
    }

    public Player getPlayer() {
        return tPlayer;
    }

    public boolean isPaused(){
        return tPaused;
    }


}
