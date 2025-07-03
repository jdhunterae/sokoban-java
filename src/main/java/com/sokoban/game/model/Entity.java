package com.sokoban.game.model;

import java.awt.Graphics;

public abstract class Entity {

    protected int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g, int tileSize);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
