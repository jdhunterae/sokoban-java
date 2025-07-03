package com.sokoban.game.model;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends Entity {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int tileSize) {
        int px = x * tileSize;
        int py = y * tileSize;

        g.setColor(new Color(205, 133, 63)); // SaddleBrown
        g.fillRect(px + 2, py + 2, tileSize - 4, tileSize - 4);

        g.setColor(new Color(139, 69, 19)); // Darker border
        g.drawRect(px + 2, py + 2, tileSize - 4, tileSize - 4);
    }
}