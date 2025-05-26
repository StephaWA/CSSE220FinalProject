package snakegame.view;

import snakegame.model.Game;

import javax.swing.*;

public class UIManager {
    private JFrame frame;
    private GameRenderer renderer;

    public UIManager(Game game) {
        frame = new JFrame("Snake Game");
        renderer = new GameRenderer(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 440);
        frame.add(renderer);
        frame.setResizable(false);
    }

    public void showUI() {
        frame.setVisible(true);
    }

    public void repaint() {
        renderer.repaint();
    }

    public void addKeyListener(java.awt.event.KeyListener listener) {
        frame.addKeyListener(listener);
    }
}
