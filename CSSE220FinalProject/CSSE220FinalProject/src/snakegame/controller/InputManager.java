package snakegame.controller;


import snakegame.event.DirectionChangedEvent.Direction;
import snakegame.model.Game;
import snakegame.model.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputManager extends KeyAdapter {
    private Game game;

    public InputManager(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            game.start();
        }

        if (key == KeyEvent.VK_R) {
            game.reset();
        }

        if (game.getState() != GameState.RUNNING) return;

        switch (key) {
            case KeyEvent.VK_UP -> game.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> game.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> game.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> game.setDirection(Direction.RIGHT);
        }
    }
}
