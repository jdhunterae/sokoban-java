package com.sokoban.game.analysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.sokoban.game.io.LevelLoader;
import com.sokoban.game.logic.GameMap;

public class LevelGrader {

    public static void main(String[] args) {
        runAll();
    }

    public static void runAll() {
        System.out.println("--- Level Grader Report ---");
        try {
            List<Path> levelFiles = getLevelFiles("levels");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("build/reports/level_analysis_report.txt"))) {
                writer.write("Level\tSolvable\tWidth\tHeight\tGoals\tBoxes\tPlayerPresent\n");

                for (Path path : levelFiles) {
                    String filename = path.getFileName().toString();
                    GameMap map = null;
                    boolean loaded = false;

                    try {
                        map = LevelLoader.loadLevel("levels");
                        loaded = true;
                    } catch (IOException e) {
                        System.out.println("[ERROR] Could not load: " + filename);
                    }

                    if (loaded && map != null) {
                        int width = map.getWidth();
                        int height = map.getHeight();
                        int goals = map.getGoals().size();
                        int boxes = map.getBoxes().size();
                        boolean playerPresent = map.getPlayer() != null;
                        boolean solvable = (goals > 0 && boxes >= goals && playerPresent); // placeholder for now

                        writer.write(String.format("%s\t%s\t%d\t%d\t%d\t%d\t%s\n",
                                filename,
                                solvable ? "Yes" : "No",
                                width, height, goals, boxes,
                                playerPresent ? "Yes" : "No"));
                    }
                }

                System.out.println("Analysis complete. Report saved to level_analysis_report.txt");
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Failed to scan levels: " + e.getMessage());
        }
    }

    public static List<Path> getLevelFiles(String resourceFolder) throws IOException, URISyntaxException {
        ClassLoader loader = LevelGrader.class.getClassLoader();
        URL url = loader.getResource(resourceFolder);
        if (url == null) {
            throw new IOException("Resource folder not found: " + resourceFolder);
        }

        Path dirPath = Paths.get(url.toURI());
        return Files.list(dirPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().matches("level_\\d{2}\\.txt"))
                .collect(Collectors.toList());
    }
}
