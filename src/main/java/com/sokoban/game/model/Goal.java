package com.sokoban.game.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Goal extends Entity {

    public Goal(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int tileSize) {
        int px = x * tileSize;
        int py = y * tileSize;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(218, 165, 32)); // Goldenrod
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(px + 6, py + 6, tileSize - 12, tileSize - 12);
    }
}
