package com.colspan;

import java.awt.*;

public class EndScreen {
    private static int fontSize = 30;

    /**
     * Draw the 'End Screen'.
     *
     * @param g a Graphics object
     */
    public static void drawEndScreen(Graphics g) {
        String endMessage = "You won!!";
        g.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        g.setColor(new Color(0, 0, 0));
        g.drawString(endMessage, Grid.numBoardCols * Grid.SQUARE_SIZE/3,
            Grid.numBoardRows * Grid.SQUARE_SIZE/2);
    }
}
