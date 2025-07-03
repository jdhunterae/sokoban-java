package com.sokoban.game.model;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int tileSize) {
        int px = x * tileSize;
        int py = y * tileSize;

        g.setColor(new Color(50, 100, 200)); // Steel blue
        g.fillOval(px + 4, py + 4, tileSize - 8, tileSize - 8);

        g.setColor(new Color(0, 0, 100));
        g.drawOval(px + 4, py + 4, tileSize - 8, tileSize - 8);
    }
}
