package com.sokoban.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.sokoban.game.controller.MovementController;
import com.sokoban.game.io.LevelLoader;
import com.sokoban.game.io.LevelManager;
import com.sokoban.game.logic.GameMap;
import com.sokoban.game.model.GameState;

public class GamePanel extends JPanel {

    private static final int TILE_SIZE = 40;
    private final LevelManager levelManager;
    private GameMap gameMap;
    private GameState gameState;
    private boolean pendingWinMessage = false;
    private String currentLevel = "levels/level_01.txt";

    public GamePanel() {
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);

        levelManager = new LevelManager(10);
        loadLevel(levelManager.getCurrentLevelPath());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
                repaint();
            }
        });

        Timer timer = new Timer(1000 / 60, e -> repaint());
        timer.start();
    }

    private void handleKeyPress(KeyEvent e) {
        switch (gameState) {
            case RUNNING:
                int dx = 0,
                 dy = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_W ->
                        dy = -1;
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S ->
                        dy = 1;
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A ->
                        dx = -1;
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D ->
                        dx = 1;
                }

                MovementController.tryMovePlayer(gameMap, dx, dy);
                if (gameMap.isSolved()) {
                    gameState = GameState.INPUT_LOCKED;
                    pendingWinMessage = true;
                    Timer delay = new Timer(150, evt -> {
                        gameState = GameState.WON;
                        ((Timer) evt.getSource()).stop();
                    });
                    delay.setRepeats(false);
                    delay.start();
                }
                break;
            case WON:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> {
                        if (levelManager.hasNextLevel()) {
                            levelManager.advanceLevel();
                            loadLevel(levelManager.getCurrentLevelPath());
                            gameState = GameState.RUNNING;
                        } else {
                            System.out.println("You've completed all levels!");
                            gameState = GameState.ERROR;
                        }
                    }
                    case KeyEvent.VK_R ->
                        loadLevel(currentLevel);
                    case KeyEvent.VK_ESCAPE ->
                        System.exit(0);
                }
            case ERROR:
            case INPUT_LOCKED:
                // TODO: Allow retry or exit options here
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameMap == null) {
            return;
        }

        int levelWidth = gameMap.getWidth() * TILE_SIZE;
        int levelHeight = gameMap.getHeight() * TILE_SIZE;

        int offsetX = (getWidth() - levelWidth) / 2;
        int offsetY = (getHeight() - levelHeight) / 2;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(offsetX, offsetY);
        gameMap.draw(g2d, TILE_SIZE);
        g2d.dispose();

        if (gameState == GameState.WON) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Level Complete!", 60, getHeight() / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Enter: Next Level (not yet implemented)", 60, getHeight() / 2 + 10);
            g.drawString("R: Retry Level", 60, getHeight() / 2 + 30);
            g.drawString("ESC: Exit Game", 60, getHeight() / 2 + 50);
        }
    }

    private void loadLevel(String path) {
        try {
            gameMap = LevelLoader.loadLevel(path);

            currentLevel = path;
            gameState = GameState.RUNNING;
            pendingWinMessage = false;
        } catch (IOException e) {
            System.err.println("Ran into an error while loading the level at: " + path);
            // e.printStackTrace(); // Uncomment for details
            gameState = GameState.ERROR;
        }
    }
}
