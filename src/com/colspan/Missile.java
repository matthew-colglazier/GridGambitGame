package com.colspan;

import java.util.Random;

/**
 * Class representing a Missile object & its functionality in GridGambit.
 */
public class Missile {

    /**
     *
     */
    public boolean complete = false;

    /**
     * A boolean representing whether the missile has been deleted.
     */
    public boolean deleted = false;

    /**
     * The current coordinates of the missile.
     */
    private int currX, currY;

    /**
     * The starting coordinates of the missile.
     */
    private int startX, startY;

    /**
     * The coordinates that the missile will travel to.
     */
    private int targetX, targetY;

    /**
     * Int denoting which direction the missile is traveling (as determined by which side
     * of the screen it starts on). 0 -> North; 1 -> South; 2 -> East; 3 -> West
     */
    private int travelDirection;

    /**
     * Ints denoting the direction & magnitude of the missile's movement along the x-axis
     * and the y-axis, respectively.
     */
    private int xUpdate, yUpdate;

    /**
     *
     */
    private int decayCount = 0;

    /**
     * A Random object.
     */
    private Random rand = new Random();

    /**
     * Constructor for the Missile Class. Generates target coordinates, start coordinates,
     * and approach direction, initializes private fields accordingly, and initializes the
     * current coordinates of the missile to its start coordinates.
     *
     * @param playerX The current x-coordinate of the Player.
     * @param playerY The current y-coordinate of the Player.
     */
    public Missile(int playerX, int playerY) {
        // Generate target coordinates within a 5x5 square around the player's coordinates
        int randOffsetX = rand.nextInt(3);
        int randOffsetY = rand.nextInt(3);
        this.targetX = playerX + randOffsetX;
        this.targetY = playerY + randOffsetY;

        // If the missile would be traveling off the side of the grid, move back into the
        // grid.
        if (this.targetX <= 0) {
            this.targetX = 1;
        }
        if (this.targetX >= Grid.numBoardCols - 1) {
            this.targetX = Grid.numBoardCols - 2;
        }
        if (this.targetY <= 0) {
            this.targetY = 1;
        }
        if (this.targetY >= Grid.numBoardRows - 1) {
            this.targetY = Grid.numBoardRows - 2;
        }

        // Randomly generate the direction from which the missile will approach its target
        // coordinates.
        this.travelDirection = rand.nextInt(4);

        // Initialize the starting coordinates and movement based on the travelDirection
        if (this.travelDirection == 0) {
            this.startX = this.targetX;
            this.startY = 1;
            this.xUpdate = 0;
            this.yUpdate = 1;
        } // travel North
        else if (this.travelDirection == 1) {
            this.startX = this.targetX;
            this.startY = Grid.numBoardRows - 2; //need to clarify height/width to x/y correspondence
            this.xUpdate = 0;
            this.yUpdate = -1;
        } // travel South
        else if (travelDirection == 2) {
            this.startX = 1;
            this.startY = targetY;
            this.xUpdate = 1;
            this.yUpdate = 0;
        } // travel East
        else { // travelDirection == 3
            this.startX = Grid.numBoardCols - 2;
            this.startY = targetY;
            this.xUpdate = -1;
            this.yUpdate = 0;
        } // travel West

        // Initialize the missile's current position to its starting position
        this.currX = this.startX;
        this.currY = this.startY;
    }

    /**
     * Updates the position of the missile, updates the state of the missile once it reaches
     * its target coordinates, and converts spaces that the missile passes over into death
     * spaces.
     */
    public void update() {
        // Check if the missile has reached its target coordinates and set the missile to
        // be complete if it has
        if (this.currX == this.targetX && this.currY == this.targetY) {
            this.complete = true;

            // Unless the missile landed on the key, make the missile's final position a
            // death space
            if (Grid.board[this.currX][this.currY] != 2) { //
                Grid.board[this.currX][this.currY] = 1;
            }
        }

        // Do not update the missile's position if it has completed its flight to its
        // target coordinates
        if (this.complete) {
            return;
        }

        // Unless the missile is on top of the key, make the missile's current position a
        // death space
        if (Grid.board[this.currX][this.currY] != 2) {
            Grid.board[this.currX][this.currY] = 1;
        }

        // Update missile's position
        this.currX = this.currX + this.xUpdate;
        this.currY = this.currY + this.yUpdate;
    }

    /**
     * Counts down until the missile trail is ready to be cleared; Once ready, calls
     * deleteTrail to clear the trail.
     */
    public void decayTrail() { //will need to add check such that we don't clear red spaces that are part of the level layout
        decayCount++;
        if (decayCount == 5) {
            deleteTrail();
        }
    }

    /**
     *
     */
    public void deleteTrail() {
        for (int x = startX; x != targetX; x += xUpdate) {
            if (Grid.board[x][startY] == 1) {
                Grid.board[x][startY] = 0;
            }
        }
        for (int y = startY; y != targetY; y += yUpdate) {
            if (Grid.board[startX][y] == 1) {
                Grid.board[startX][y] = 0;
            }
        }
        deleted = true;
    }

}
