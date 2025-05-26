package snakegame.view;

import snakegame.model.Game;
import snakegame.model.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameRenderer extends JPanel {
    private Game game;

    public GameRenderer(Game game) {
        this.game = game;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int gridSize = game.getGridSize();
        int boxSize = panelWidth / game.getGridWidth();
        int topBarHeight = 40;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, panelWidth, panelHeight);

        drawScoreBar(g, panelWidth, topBarHeight);

        GameState state = game.getState();

        if (state == GameState.WAITING) {
            g.setColor(Color.WHITE);
            g.drawString("Press SPACE to start", panelWidth / 2 - 60, panelHeight / 2);
            return;
        }

        if (state == GameState.GAME_OVER) {
            g.setColor(Color.WHITE);
            g.drawString("Game Over - Press R to Restart", panelWidth / 2 - 80, panelHeight / 2);
        }

        drawSnake(g, boxSize, topBarHeight);
        drawFood(g, boxSize, topBarHeight);
    }

    private void drawScoreBar(Graphics g, int width, int height) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Score: " + game.getScore(), 10, 25);
        g.drawString("High Score: " + game.getHighScore(), width - 140, 25);
    }

    private void drawSnake(Graphics g, int boxSize, int yOffset) {
        g.setColor(Color.GREEN);
        List<Point> body = game.getSnake();
        for (Point p : body) {
            g.fillRect(p.x * boxSize, p.y * boxSize + yOffset, boxSize, boxSize);
        }
    }

    private void drawFood(Graphics g, int boxSize, int yOffset) {
        g.setColor(Color.RED);
        Point food = game.getFood();
        g.fillRect(food.x * boxSize, food.y * boxSize + yOffset, boxSize, boxSize);
    }
}
