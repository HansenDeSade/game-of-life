package de.hansendesade;

import de.hansendesade.model.WorldModel;
import de.hansendesade.control.GameOfLifeController;
import de.hansendesade.model.ClassicWorldModel;
import de.hansendesade.view.GameOfLifeViewImpl;

import javax.swing.SwingUtilities;

public class GameOfLife {

  private static final int ROWS = 60;
  private static final int COLS = 60;

  public static void main(final String[] args) {
    SwingUtilities.invokeLater(() -> {
      final WorldModel model = new ClassicWorldModel(ROWS, COLS);
      final GameOfLifeController controller = new GameOfLifeController(model);
      new GameOfLifeViewImpl(model, controller);
    });
  }
}
