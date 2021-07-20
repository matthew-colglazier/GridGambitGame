package com.colspan;

import java.awt.*;

public class StartScreen {
    private static int fontSize = 30;

    /**
     * Draw the 'Start Screen'.
     *
     * @param g a Graphics object
     */
    public static void drawStartScreen(Graphics g) {
        String startMessage = "Press Enter to Begin Game!";
        g.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        g.setColor(new Color(0, 0, 0));
        g.drawString(startMessage, Grid.numBoardCols * Grid.SQUARE_SIZE/20,
            Grid.numBoardRows * 3 * Grid.SQUARE_SIZE/5);
    }
}
