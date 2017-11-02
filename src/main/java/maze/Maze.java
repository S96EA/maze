package maze;

import processing.core.PApplet;

import java.util.*;

public class Maze {
    PApplet app;
    final int nRow;
    final int nColumn;
    Cell[][] cells;
    List<Cell> unVisitedCells = new LinkedList<>();
    Random random = new Random();

    public Maze(PApplet app, int nRow, int nColumn) {
        this.app = app;
        this.nRow = nRow;
        this.nColumn = nColumn;
        cells = new Cell[nRow][nColumn];
        final float cellWidth = (float) app.width / nColumn;
        final float cellHeight = (float) app.height / nRow;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nColumn; j++) {
                cells[i][j] = new Cell(cellWidth, cellHeight, i, j);
                unVisitedCells.add(cells[i][j]);
            }
        }

        //maze algorithm
        Cell cell = cells[0][0];
        cell.visited = true;
        unVisitedCells.remove(cell);

        while (unVisitedCells.size() != 0) {
            Cell neighbor = getRandomUnvisitedNeighbor(cell);
            if (neighbor != null) {
                removeBorder(cell, neighbor);
                cell = neighbor;
            } else {
                cell = getRandomUnvisitedCell();
            }
        }
    }

    private void removeBorder(Cell cell, Cell neighbor) {
        if (neighbor.indexRow - cell.indexRow > 0) {
            neighbor.upBorder = false;
            cell.downBorder = false;
            if ((cell = getCellSafely(neighbor.indexRow - 1, neighbor.indexCulomn)) != null) {
                cell.downBorder = false;
            }
        } else if (neighbor.indexRow - cell.indexRow < 0) {
            neighbor.downBorder = false;
            cell.upBorder = false;
            if ((cell = getCellSafely(neighbor.indexRow + 1, neighbor.indexCulomn)) != null) {
                cell.upBorder = false;
            }
        } else if (neighbor.indexCulomn - cell.indexCulomn > 0) {
            neighbor.leftBorder = false;
            cell.rightBorder = false;
            if ((cell = getCellSafely(neighbor.indexRow, neighbor.indexCulomn - 1)) != null) {
                cell.rightBorder = false;
            }
        } else if (neighbor.indexCulomn - cell.indexCulomn < 0) {
            neighbor.rightBorder = false;
            cell.leftBorder = false;
            if ((cell = getCellSafely(neighbor.indexRow, neighbor.indexCulomn + 1)) != null) {
                cell.leftBorder = false;
            }
        }
    }

    private Cell getCellSafely(int i, int j) {
        if (i < 0 || j < 0 || i >= nRow || j >= nColumn) {
            return null;
        }
        return cells[i][j];
    }


    private int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private Cell getRandomUnvisitedNeighbor(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        Cell neighbor;
        for (; ; ) {
            for (int i = 0; i < 4; i++) {
                if ((neighbor = getCellSafely(cell.indexRow + direction[i][0]
                        , cell.indexCulomn + direction[i][1])) != null && !neighbor.visited) {
                    neighbors.add(neighbor);
                }
            }
            if (neighbors.size() > 0) break;
            for (; ; ) {
                neighbor = getCellSafely(cell.indexRow + direction[random.nextInt(4)][0],
                        cell.indexCulomn + direction[random.nextInt(4)][1]);
                if (neighbor != null) {
                    cell = neighbor;
                    break;
                }
            }
        }
        neighbor = neighbors.get(random.nextInt(neighbors.size()));
        neighbor.visited = true;
        unVisitedCells.remove(neighbor);
        return neighbor;
    }

    public void draw() {
        Arrays.stream(cells).forEach(rcells -> Arrays.stream(rcells).forEach(cell -> cell.draw(app)));
    }

    public Cell getRandomUnvisitedCell() {
        if (unVisitedCells.size() == 0) {
            return null;
        }
        Cell cell = unVisitedCells.get(random.nextInt(unVisitedCells.size()));
        cell.visited = true;
        unVisitedCells.remove(cell);
        return cell;
    }
}
