package de.hansendesade.control;

import de.hansendesade.model.WorldModel;
import de.hansendesade.view.GameOfLifeView;

import javax.swing.Timer;


/**
 *
 */
public final class GameOfLifeController {
  public static final int INITIAL_SPEED = 10;
  private GameOfLifeView view;
  private final WorldModel model;
  private final Timer timer;
  private int generationMonitor = 0;

  public GameOfLifeController(final WorldModel model) {
    this.model = model;

    timer = new Timer(INITIAL_SPEED, e -> {
      this.model.nextGeneration();
      generationMonitor++;
      view.refresh();
    });
  }

  public void startSimulation() {
    timer.start();
  }

  public void stopSimulation() {
    timer.stop();
  }

  public void reset() {
    model.reset();
    generationMonitor = 0;
    view.refresh();
    timer.stop();
  }

  public void switchCellState(final int row, final int col) {
    model.switchCellState(row, col);
  }

  public void registerView(final GameOfLifeView view) {
    this.view = view;
  }

  public void changeSpeed(final int value) {
    timer.setDelay(value);
  }

  public int getCurrentGeneration() {
    return generationMonitor;
  }
}
