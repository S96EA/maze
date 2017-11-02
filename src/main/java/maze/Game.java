package maze;

import processing.core.PApplet;

public class Game extends PApplet {

    Maze maze;

    public static void main(String[] args) {
        PApplet.main("maze.Game");
    }

    @Override
    public void settings() {
        size(830, 830);
    }

    @Override
    public void setup() {
        maze = new Maze(this, 150, 150);

    }

    @Override
    public void draw() {
        background(255);
        stroke(0);
        strokeWeight(1);
        maze.draw();
    }

    @Override
    public void keyPressed() {
        if (key == 'e')
            saveFrame("maze.png");
    }
}