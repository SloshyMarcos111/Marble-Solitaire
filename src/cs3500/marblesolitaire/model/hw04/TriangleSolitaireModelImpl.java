package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Representation of a triangle shaped marble solitaire model.
 * Uses features from AbstractMarbleSolitaire.
 */
public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaire
    implements MarbleSolitaireModel  {

  /**
   * Default constructor for triangle shaped board. Uses arm thickness of 5 (number of rows)
   * and a default blank space at 0,0.
   */
  public TriangleSolitaireModelImpl() {

    this.armThickness = 5;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(0, 0, board);
  }

  /**
   * Constructor that takes in the number of desired rows and creates a triangle shaped board.
   * @param rows the desired number of rows.
   */
  public TriangleSolitaireModelImpl(int rows) {

    this.armThickness = rows;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(0, 0, board);
  }

  /**
   * Constructor that takes in the desired row and column numbers of the blank space and uses
   * an arm length of 5.
   * @param row the blank space row (starting from 0).
   * @param col the blank space column (starting from 0).
   */
  public TriangleSolitaireModelImpl(int row, int col) {

    this.armThickness = 5;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(row, col, board);
  }

  /**
   * Constructor that takes the desired dimension and blank space row and column numbers and
   * creates a board with these parameters.
   * @param dimension is the number of rows.
   * @param row is the blank space row (starting from 0).
   * @param col is the blank space column (starting from 0).
   */
  public TriangleSolitaireModelImpl(int dimension, int row, int col) {

    this.armThickness = dimension;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(row, col, board);
  }
}
