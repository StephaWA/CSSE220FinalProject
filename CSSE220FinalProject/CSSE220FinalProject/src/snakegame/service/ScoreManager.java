package snakegame.service;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        score = 0;
    }

    public void increaseScore() {
        score += 10;
    }

    public int getScore() {
        return score;
    }
}
