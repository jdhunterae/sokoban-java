package com.sokoban.game.io;

import org.junit.jupiter.api.Test;

import com.sokoban.game.logic.GameMap;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class LevelLoaderTest {
    @Test
    public void testLoadValidLevel() throws IOException {
        GameMap map = LevelLoader.loadLevel("levels/level_01.txt");

        assertNotNull(map);
        assertEquals(5, map.getWidth());
        assertEquals(5, map.getHeight());
        assertNotNull(map.getPlayer());
        assertFalse(map.getBoxes().isEmpty());
        assertFalse(map.getGoals().isEmpty());
    }

    @Test
    public void testLoadMissingLevelThrows() {
        assertThrows(IOException.class, () -> {
            LevelLoader.loadLevel("levels/nonexistent_level.txt");
        });
    }
}
