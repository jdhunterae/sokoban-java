package com.sokoban.game.io;

public class LevelManager {

    private int currentLevel = 1;
    private final int maxLevel;

    public LevelManager(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void restart() {
        currentLevel = 1;
    }

    public void advanceLevel() {
        if (hasNextLevel()) {
            currentLevel++;
        }
    }

    public void resetLevel() {
        // TODO: do nothing to current level, just reload the same path
    }

    public String getCurrentLevelPath() {
        return String.format("levels/level_%02d.txt", currentLevel);
    }

    public boolean hasNextLevel() {
        return currentLevel < maxLevel;
    }
}
