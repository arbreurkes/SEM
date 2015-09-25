package application.core;

import application.controllers.ScoreComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for HighScoreMangager.
 * @author Daphne van Tetering.
 */
public class HighScoreManager {
    private ArrayList<Score> scores;
    private static final String highScoreFile = "highScores.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * Constructor for HighScoreManager.
     */
    public HighScoreManager(){
        scores = new ArrayList<Score>();
    }

    /**
     * Load scores from files, sort in descending order and return.
     * @return ArrayList of scores currently saved in file.
     */
    public ArrayList<Score> getScores(){
        loadScoreFile();
        sort();
        return scores;
    }

    /**
     * Sort arrayList in descending order.
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
        Collections.reverse(scores);
    }

    /**
     * Add new score to list.
     * @param newPlayer name of player the score belongs to.
     * @param newScore score.
     */
    public void addScores(String newPlayer, int newScore) {
        loadScoreFile();
        scores.add(new Score(newPlayer, newScore));
        sort();
        updateScoreFile();
    }

    /**
     * Load scores from file.
     */
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(highScoreFile));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Update scores and write to file.
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(highScoreFile));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
