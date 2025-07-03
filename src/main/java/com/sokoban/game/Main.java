package com.sokoban.game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sokoban");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Use level_01.txt by default; can be updated for level progression
            GamePanel panel = new GamePanel();
            frame.add(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
