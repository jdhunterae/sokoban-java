package com.sokoban.game.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.sokoban.game.logic.GameMap;
import com.sokoban.game.model.Box;
import com.sokoban.game.model.Goal;
import com.sokoban.game.model.Player;
import com.sokoban.game.model.Tile;
import com.sokoban.game.model.TileType;

public class LevelLoader {

    public static GameMap loadLevel(String resourcePath) throws IOException {
        InputStream is = LevelLoader.class.getClassLoader().getResourceAsStream(resourcePath);

        if (is == null) {
            throw new IOException("Level file not found in resources: " + resourcePath);
        }

        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            lines = reader.lines().toList();
        }

        int width = lines.get(0).length();
        int height = lines.size();
        GameMap map = new GameMap(width, height);

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);

                switch (c) {
                    case '#' -> map.setTile(x, y, new Tile(TileType.WALL));
                    case '.' -> {
                        map.setTile(x, y, new Tile(TileType.GOAL));
                        map.addGoal(new Goal(x, y));
                    }
                    case '$' -> {
                        map.setTile(x, y, new Tile(TileType.FLOOR));
                        map.addBox(new Box(x, y));
                    }
                    case '@' -> {
                        map.setTile(x, y, new Tile(TileType.FLOOR));
                        map.setPlayer(new Player(x, y));
                    }
                    case ' ' -> map.setTile(x, y, new Tile(TileType.FLOOR));
                    default -> {
                        System.err.println("Unknown tile character: " + c + " at (" + x + ", " + y + ")");
                        map.setTile(x, y, new Tile(TileType.FLOOR));
                    }
                }
            }
        }

        return map;
    }
}
