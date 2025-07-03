package com.sokoban.game.model;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public void draw(Graphics g, int x, int y, int tileSize) {
        switch (type) {
            case FLOOR ->
                g.setColor(new Color(180, 180, 180));
            case WALL ->
                g.setColor(new Color(60, 60, 60));
            case GOAL ->
                g.setColor(new Color(200, 190, 160));
        }

        g.fillRect(x, y, tileSize, tileSize);

        g.setColor(new Color(0, 0, 0, 64)); // Semi-transparent black border
        g.drawRect(x, y, tileSize, tileSize);

        // Top-left highlight
        g.setColor(new Color(255, 255, 255, 30));
        g.drawLine(x, y, x + tileSize - 1, y); // top
        g.drawLine(x, y, x, y + tileSize - 1); // left

        // Bottom-right shadow
        g.setColor(new Color(0, 0, 0, 60));
        g.drawLine(x + tileSize - 1, y, x + tileSize - 1, y + tileSize - 1); // right
        g.drawLine(x, y + tileSize - 1, x + tileSize - 1, y + tileSize - 1); // bottom
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
}
