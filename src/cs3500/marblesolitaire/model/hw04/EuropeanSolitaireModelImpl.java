package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Marble;

/**
 * Representation of a European style marble solitaire model.
 * Uses features from AbstractMarbleSolitaire.
 */
public class EuropeanSolitaireModelImpl extends AbstractMarbleSolitaire {

  /**
   * Constructor for a default European marble solitaire board with a side length of 3 and
   * the blank space in the middle of the board.
   */
  public EuropeanSolitaireModelImpl() {

    this.armThickness = 3 ;

    int dim = calcDim();
    int middle = dim / 2;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(middle, middle, board);
  }

  /**
   * Constructor for a European marble solitaire board with a desired side length that must be odd
   * and positive, and a default blank space in the middle of the board.
   * @param sideLength is the desired side length dimension of the board. It must be an odd and
   *                   positive integer.
   */
  public EuropeanSolitaireModelImpl(int sideLength) {

    this.armThickness = sideLength;

    int dim = calcDim();
    int middle = dim / 2;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(middle, middle, board);
  }

  /**
   * Constructor for a European marble solitaire board with a default side length of 3 and a
   * blank space in the specified location.
   * @param row is the blank space row (starting from 0).
   * @param col is the blank space column (starting from 0).
   */
  public  EuropeanSolitaireModelImpl(int row, int col) {

    this.armThickness = 3 ;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(row, col, board);
  }

  /**
   * Constructor for a European marble solitaire board with a desired side length that must be odd
   * and positive, and a blank space in the specified location.
   * @param sideLength is the desired side length dimension of the board. It must be an odd and
   *                   positive integer.
   * @param row is the blank space row (starting from 0).
   * @param col is the blank space column (starting from 0).
   */
  public  EuropeanSolitaireModelImpl(int sideLength, int row, int col) {

    this.armThickness = sideLength;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(row, col, board);
  }
}
