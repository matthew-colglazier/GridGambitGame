package com.colspan;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Point;


import javax.swing.JPanel;

public class Player extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * A Point object representing the player's x and y coordinates relative to the grid.
     */
    public Point pos;

    /**
     * A boolean value representing whether the player is dead.
     */
    public boolean dead = false;

    /**
     * Constructor for a Player object. Player initialized to be alive and located at the
     * starting position.
     */
    public Player() {
        this.pos = new Point(0, 0);
        this.resetPlayer();
    }

    /**
     * Reset player to start position and set player to not be dead.
     */
    public void resetPlayer() {
        this.dead = false;
        this.pos.x = 5;
        this.pos.y = 5;
    }

    /**
     * Handles all KeyEvents. Implements 'wasd' as arrow keys functionality.
     * @param e a KeyEvent to be handled
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // wasd functionality
        switch (key) {
            case KeyEvent.VK_W -> this.pos.translate(0, -1);
            case KeyEvent.VK_A -> this.pos.translate(-1, 0);
            case KeyEvent.VK_S -> this.pos.translate(0, 1);
            case KeyEvent.VK_D -> this.pos.translate(1, 0);
        }
    }

    /**
     * Checks whether player is leaving the grid and whether player is standing on a death
     * space. If leaving the grid, prevent this action and return player to the screen.
     */
    public void checkLocation() {
        // Prevents the player from leaving the grid.
        if (this.pos.x < 0) {
            this.pos.x = 0;
        }
        if (this.pos.y < 0) {
            this.pos.y = 0;
        }
        if (this.pos.x >= Grid.height) {
            this.pos.x = Grid.height - 1;
        }
        if (this.pos.y >= Grid.width) {
            this.pos.y = Grid.width - 1;
        }

        // Checks whether the player is standing on a death space.
        if (Grid.board[this.pos.x][this.pos.y] == 1) {
            this.dead = true;
        }
    }

    /**
     * Draws the player at the current position on the grid.
     *
     * @param g a Graphics object
     * @param observer an ImageObserver object
     */
    public void draw(Graphics g, ImageObserver observer) {
        Color green = new Color(17, 240, 50);
        g.setColor(green);
        g.fillRect(this.pos.x * Grid.SQUARE_SIZE, this.pos.y * Grid.SQUARE_SIZE,
            Grid.SQUARE_SIZE, Grid.SQUARE_SIZE);
    }
}