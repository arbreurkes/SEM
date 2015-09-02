package application.controllers;

import application.Main;
import application.core.Alien;
import application.core.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelController {

    @FXML
    private Canvas gameCanvas;

    ArrayList<Alien> tAliens = Main.game.getLevel().getAliens();;

    @FXML
    private void initialize() {

        gameCanvas.setWidth(Main.getWidth());
        gameCanvas.setHeight(Main.getHeight());

        draw();

//        Task task = new Task<Void>() {
//            @Override
//            public Void call() throws Exception {
//                int i = 0;
//                while (true) {
//                    final int finalI = i;
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                        }
//                    });
//                    i++;
//                    Thread.sleep(1000);
//                }
//            }
//        };
//        Thread th = new Thread(task);
//        th.setDaemon(true);
//        th.start();
    }

    protected void draw() {

        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                long time = System.nanoTime() - lastTime;
                lastTime = System.nanoTime();
                gc.clearRect(0, 0, Main.getWidth(), Main.getHeight());
                drawAliens(gc);
                drawPlayer(gc);
                drawProjectiles(gc);
            }
        }.start();
    }

    protected void drawAliens(GraphicsContext gc) {
        for (Alien alien: tAliens) {
            alien.move();
            gc.drawImage( alien.getImage(), alien.getX(), alien.getY() );
        }
    }

    protected void drawPlayer(GraphicsContext gc) {
        Player player = Main.game.getPlayer();
        gc.drawImage( player.getImage(), player.getX(), player.getY() );
    }

    protected void drawProjectiles(GraphicsContext gc) {}



}
