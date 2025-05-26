package snakegame;

import snakegame.controller.GameLoopController;
import snakegame.controller.InputManager;
import snakegame.model.Game;
import snakegame.view.UIManager;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        UIManager uiManager = new UIManager(game);
        InputManager inputManager = new InputManager(game);
        uiManager.addKeyListener(inputManager);
        uiManager.showUI();

        GameLoopController loop = new GameLoopController(game, uiManager);
        loop.start();
    }
}
