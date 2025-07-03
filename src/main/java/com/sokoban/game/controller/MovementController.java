package com.sokoban.game.controller;

import com.sokoban.game.logic.GameMap;
import com.sokoban.game.model.Box;
import com.sokoban.game.model.Player;
import com.sokoban.game.model.TileType;

public class MovementController {

    public static void tryMovePlayer(GameMap map, int dx, int dy) {
        Player player = map.getPlayer();
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (map.getTile(newX, newY).getType() != TileType.WALL) {
            for (Box box : map.getBoxes()) {
                if (box.getX() == newX && box.getY() == newY) {
                    int boxNewX = box.getX() + dx;
                    int boxNewY = box.getY() + dy;

                    if (map.getTile(boxNewX, boxNewY).getType() != TileType.WALL) {
                        boolean blocked = map.getBoxes().stream().anyMatch(other -> other.getX() == boxNewX && other.getY() == boxNewY);

                        if (!blocked) {
                            box.setPosition(boxNewX, boxNewY);
                            player.setPosition(newX, newY);
                        }
                    }

                    return;
                }
            }

            player.setPosition(newX, newY);
        }
    }
}
