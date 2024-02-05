package de.hansendesade.model;

public final class ClassicWorldModel implements WorldModel {

  private final int rows;
  private final int cols;
  private boolean[][] cells;

  public ClassicWorldModel(final int rows, final int cols) {
    this.rows = rows;
    this.cols = cols;
    this.cells = new boolean[rows][cols];
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public boolean isCellAlive(final int row, final int col) {
    return cells[row][col];
  }

  public void switchCellState(final int row, final int col) {
    cells[row][col] = !cells[row][col];
  }

  public void reset() {
    cells = new boolean[rows][cols];
  }

  public void nextGeneration() {
    boolean[][] nextGeneration = new boolean[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        final int aliveNeighbors = countAliveNeighbors(row, col);
        nextGeneration[row][col] = applyRules(cells[row][col], aliveNeighbors);
      }
    }

    cells = nextGeneration;
  }

  private boolean applyRules(final boolean currentlyAlive, final int aliveNeighbors) {
    if (currentlyAlive && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
      return true;
    } else {
      return !currentlyAlive && aliveNeighbors == 3;
    }
  }

  private int countAliveNeighbors(final int row, final int col) {
    int aliveNeighbors = 0;

    for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
      for (int colIndex = -1; colIndex <= 1; colIndex++) {
        int neighborRow = row + rowIndex;
        int neighborCol = col + colIndex;
        if (!(rowIndex == 0 && colIndex == 0) &&
                neighborRow >= 0 &&
                neighborRow < rows &&
                neighborCol >= 0 &&
                neighborCol < cols &&
                cells[neighborRow][neighborCol]) {
          aliveNeighbors++;
        }
      }
    }
    return aliveNeighbors;
  }
}
