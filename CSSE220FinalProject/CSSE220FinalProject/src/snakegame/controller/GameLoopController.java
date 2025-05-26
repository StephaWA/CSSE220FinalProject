package snakegame.controller;

import snakegame.model.Game;
import snakegame.model.GameState;
import snakegame.view.UIManager;

public class GameLoopController {
    private Game game;
    private UIManager uiManager;
    private boolean running;

    public GameLoopController(Game game, UIManager uiManager) {
        this.game = game;
        this.uiManager = uiManager;
    }

    public void start() {
        running = true;
        Thread loop = new Thread(() -> {
            while (running) {
                if (game.getState() == GameState.RUNNING) {
                    game.update();
                }
                uiManager.repaint();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        loop.start();
    }
}
