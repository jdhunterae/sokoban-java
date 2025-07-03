package com.sokoban.game.logic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sokoban.game.model.Box;
import com.sokoban.game.model.Goal;
import com.sokoban.game.model.Player;
import com.sokoban.game.model.Tile;

public class GameMap {

    private final Tile[][] tiles;
    private final List<Box> boxes;
    private final List<Goal> goals;
    private Player player;

    public GameMap(int width, int height) {
        tiles = new Tile[height][width];
        boxes = new ArrayList<>();
        goals = new ArrayList<>();
    }

    public void draw(Graphics g, int tileSize) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x] != null) {
                    tiles[y][x].draw(g, x * tileSize, y * tileSize, tileSize);
                }
            }
        }

        for (Goal goal : goals) {
            goal.draw(g, tileSize);
        }

        for (Box box : boxes) {
            box.draw(g, tileSize);
        }

        if (player != null) {
            player.draw(g, tileSize);
        }
    }

    public void setTile(int x, int y, Tile tile) {
        if (x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) {
            System.err.println("Invalid tile coordinates: (" + x + "," + y + ")");
            return;
        } else if (tile == null) {
            System.err.println("Attempted to set a null tile at: (" + x + "," + y + ")");
            return;
        }

        tiles[y][x] = tile;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) {
            System.err.println("Invalid tile coordinates: (" + x + "," + y + ")");
            return null;
        }

        return tiles[y][x];
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public boolean isSolved() {
        for (Goal goal : goals) {
            boolean covered = false;

            for (Box box : boxes) {
                if (box.getX() == goal.getX() && box.getY() == goal.getY()) {
                    covered = true;
                    break;
                }
            }

            if (!covered) {
                return false;
            }
        }

        return true;
    }
}
