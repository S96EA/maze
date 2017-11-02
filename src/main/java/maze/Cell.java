package maze;

import processing.core.PApplet;

public class Cell {
    final int indexRow;
    final int indexCulomn;

    final float width;
    final float height;
    final float positionX;
    final float positionY;

    boolean leftBorder = true;
    boolean rightBorder = true;
    boolean upBorder = true;
    boolean downBorder = true;

    boolean visited;

    public Cell(float width, float height, int indexRow, int indexCulomn) {
        this.width = width;
        this.height = height;

        this.indexRow = indexRow;
        this.indexCulomn = indexCulomn;

        this.positionX = indexCulomn * width;
        this.positionY = indexRow * height;

    }

    public void draw(PApplet app) {
        if (leftBorder) {
            app.line(positionX, positionY, positionX, positionY + height);
        }
        if (rightBorder) {
            app.line(positionX + width, positionY, positionX + width, positionY + height);
        }
        if (upBorder) {
            app.line(positionX, positionY, positionX + width, positionY);
        }
        if (downBorder) {
            app.line(positionX, positionY + height, positionX + width, positionY + height);
        }
    }
}
