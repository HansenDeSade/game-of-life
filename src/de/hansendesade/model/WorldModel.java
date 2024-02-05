package de.hansendesade.model;

public interface WorldModel {

  int getRows();

  int getCols();

  boolean isCellAlive(int row, int col);

  void switchCellState(int row, int col);

  void reset();

  void nextGeneration();
}
