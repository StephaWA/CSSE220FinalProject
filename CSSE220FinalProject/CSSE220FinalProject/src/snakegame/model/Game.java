package snakegame.model;

import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import snakegame.event.DirectionChangedEvent.Direction;

public class Game {
    private LinkedList<Point> snake;
    private Point food;
    private Direction direction;
    private boolean gameOver;
    private GameState state;
    private int gridWidth = 21;
    private int gridHeight = 20;
    private int gridSize = 20;

    private int score = 0;
    private int highScore = 0;
    private final String HIGH_SCORE_FILE = "highscore.txt";

    public Game() {
        reset();
    }

    public void reset() {
        snake = new LinkedList<>();
        snake.add(new Point(gridWidth / 2, gridHeight / 2));
        direction = Direction.RIGHT;
        spawnFood();
        gameOver = false;
        score = 0;
        state = GameState.WAITING;
        loadHighScore();
    }

    public void update() {
        if (state != GameState.RUNNING || gameOver) return;

        Point head = new Point(snake.getFirst());

        switch (direction) {
            case UP -> head.translate(0, -1);
            case DOWN -> head.translate(0, 1);
            case LEFT -> head.translate(-1, 0);
            case RIGHT -> head.translate(1, 0);
        }

        if (head.x < 0 || head.x >= gridWidth || head.y < 0 || head.y >= gridHeight || snake.contains(head)) {
            gameOver = true;
            state = GameState.GAME_OVER;
            return;
        }

        snake.addFirst(head);

        if (head.equals(food)) {
            score++;
            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
            spawnFood();
        } else {
            snake.removeLast();
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        Point newFood;
        do {
            newFood = new Point(rand.nextInt(gridWidth), rand.nextInt(gridHeight));
        } while (snake.contains(newFood));
        food = newFood;
    }

    private void loadHighScore() {
        try {
            File file = new File(HIGH_SCORE_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    highScore = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (Exception e) {
            System.err.println("Could not read high score file.");
        }
    }

    private void saveHighScore() {
        try {
            PrintWriter writer = new PrintWriter(HIGH_SCORE_FILE);
            writer.println(highScore);
            writer.close();
        } catch (Exception e) {
            System.err.println("Could not write high score file.");
        }
    }

    public void setDirection(Direction d) {
        if ((d == Direction.UP && direction != Direction.DOWN) ||
            (d == Direction.DOWN && direction != Direction.UP) ||
            (d == Direction.LEFT && direction != Direction.RIGHT) ||
            (d == Direction.RIGHT && direction != Direction.LEFT)) {
            direction = d;
        }
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public GameState getState() {
        return state;
    }

    public void start() {
        if (state == GameState.WAITING) {
            state = GameState.RUNNING;
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}
