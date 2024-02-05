package de.hansendesade.view;

import de.hansendesade.control.GameOfLifeController;
import de.hansendesade.model.WorldModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

public final class GameOfLifeViewImpl extends JFrame implements GameOfLifeView {

  private static final Color NEUTRAL_BACKGROUND = new Color(255, 230, 230);
  private static final Color CELL_BACKGROUND = new Color(255, 153, 153);
  private static final Color EDGE_COLOR = new Color(255, 179, 179);
  private final WorldModel model;

  private final GameOfLifeController controller;

  private final JPanel cellPanel;

  private final JLabel generationMonitor;

  private boolean pause = true;

  public GameOfLifeViewImpl(final WorldModel model, final GameOfLifeController controller) {
    this.model = model;
    this.controller = controller;

    cellPanel = new JPanel() {
      @Override
      protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        paintWorldModel(g);
      }
    };

    cellPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(final MouseEvent e) {
        int row = e.getY() / (cellPanel.getHeight() / model.getRows());
        int col = e.getX() / (cellPanel.getWidth() / model.getCols());

        controller.switchCellState(row, col);
        cellPanel.repaint();
      }
    });

    final JButton startButton = new JButton("Start");
    startButton.setBackground(CELL_BACKGROUND);
    startButton.setForeground(NEUTRAL_BACKGROUND);
    startButton.addActionListener(e -> {
      if (pause) {
        controller.startSimulation();
        startButton.setText("Pause");
      } else {
        controller.stopSimulation();
        startButton.setText("Continue");
      }
      pause = !pause;
    });

    final JButton resetButton = new JButton("Reset");
    resetButton.setBackground(CELL_BACKGROUND);
    resetButton.setForeground(NEUTRAL_BACKGROUND);
    resetButton.addActionListener(e -> {
      controller.reset();
      pause = true;
      startButton.setText("Start");
    });

    //final JSlider speedControl = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 20);
    final JSlider speedControl = new JSlider(SwingConstants.HORIZONTAL, 10, 2000, 200);
    speedControl.addChangeListener(e -> {
      //controller.changeSpeed((int) (2000 - (speedControl.getValue() - 1) * 19.9));
      controller.changeSpeed(speedControl.getValue());
    });
    speedControl.setBackground(NEUTRAL_BACKGROUND);
    speedControl.setForeground(CELL_BACKGROUND);
    speedControl.setInverted(true);
    Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
    sliderLabels.put(2000, new JLabel("Slow"));
    sliderLabels.put(1000, new JLabel("Moderate"));
    sliderLabels.put(10, new JLabel("Fast"));
    speedControl.setLabelTable(sliderLabels);
    speedControl.setPaintLabels(true);

    generationMonitor = new JLabel("0");
    generationMonitor.setBackground(NEUTRAL_BACKGROUND);
    generationMonitor.setForeground(CELL_BACKGROUND);

    final JPanel buttonPanel = new JPanel();
    buttonPanel.add(generationMonitor);
    buttonPanel.add(startButton);
    buttonPanel.add(resetButton);
    buttonPanel.add(speedControl);
    buttonPanel.setBackground(NEUTRAL_BACKGROUND);

    setTitle("Game of Life");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(getContentPane());
    setLayout(new BorderLayout());
    add(cellPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
    setBackground(NEUTRAL_BACKGROUND);
    getContentPane().setBackground(NEUTRAL_BACKGROUND);
    setResizable(false);

    controller.registerView(this);

    setVisible(true);
  }

  @Override
  public void refresh() {
    cellPanel.repaint();
    generationMonitor.setText(String.valueOf(controller.getCurrentGeneration()));
  }

  private void paintWorldModel(final Graphics g) {
    final int cellWidth = cellPanel.getWidth() / model.getCols();
    final int cellHeight = cellPanel.getHeight() / model.getRows();

    for (int row = 0; row < model.getRows(); row++) {
      for (int col = 0; col < model.getCols(); col++) {
        if (model.isCellAlive(row, col)) {
          g.setColor(CELL_BACKGROUND);
        } else {
          g.setColor(NEUTRAL_BACKGROUND);
        }
        g.fillOval(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
        g.setColor(EDGE_COLOR);
        g.drawOval(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
      }
    }
  }
}
