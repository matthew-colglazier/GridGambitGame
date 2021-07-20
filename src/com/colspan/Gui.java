package com.colspan;

import javax.swing.*;
import java.awt.*;

/**
 * Main game class.
 *
 * Credit to https://learncodebygaming.com/blog/how-to-make-a-video-game-in-java-2d-basics
 * for the starter code.
 */
public class Gui {

    /**
     * Executes game setup using JFrame.
     */
    public static void initGame() {
        JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grid grid = new Grid();
        frame.add(grid);
        frame.addKeyListener(grid);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); //Sets to screen center
        frame.setVisible(true);
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGame();
            }
        });
    }
}