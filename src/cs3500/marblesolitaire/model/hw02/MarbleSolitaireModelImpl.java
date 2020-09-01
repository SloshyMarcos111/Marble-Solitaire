package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaire;

/**
 * Represents a Marble Solitaire game with editable arm thickness that uses the Marble object
 * as movable objects.
 */
public class MarbleSolitaireModelImpl extends AbstractMarbleSolitaire {

  /**
   * Constructor takes no arguments and sets a board with arm thickness 3 and blank in the middle.
   */
  public MarbleSolitaireModelImpl() {

    this.armThickness = 3;
    int dim = calcDim();
    int middle = dim / 2;

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(middle, middle, board);
  }

  /**
   * Constructor creates a marble solitaire board with arm thickness set to 3. The blank space can
   * be placed with the parameters as input.
   *
   * @param sRow the row (from 0) where the blank space is placed.
   * @param sCol the column (from 0) where the blank space is placed.
   * @throws IllegalArgumentException when arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    int dim = calcDim();

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(sRow, sCol, board);
  }

  /**
   * Constructor creates a marble solitaire board with a desired arm thickness.
   * @param armThickness the desired arm thickness of the board, which is the height and width of
   *                     the board's arms.
   * @throws IllegalArgumentException when the arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number.");
    }

    this.armThickness = armThickness;
    int dim = calcDim();

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(dim / 2, dim / 2, board);
  }

  /**
   * Constructor creates a marble solitaire board with desired arm thickness and blank position.
   * @param armThickness the desired arm thickness of the board, which is the height and width of
   *                     the board's arms.
   * @param sRow         the row (from 0) where the blank space is placed.
   * @param sCol         the column (from 0) where the blank space is placed.
   * @throws IllegalArgumentException when arm thickness is not a positive odd number, and when the
   *                                  blank position is invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 0) {
      throw new IllegalArgumentException("Arm thickness must be an positive odd number.");
    }

    this.armThickness = armThickness;
    int dim = calcDim();

    Marble[][] board = setBoard(armThickness);
    this.board = setBlank(sRow, sCol, board);
  }

}



