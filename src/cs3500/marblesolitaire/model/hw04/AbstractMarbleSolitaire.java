package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Abstract class that provides the common features between different marble solitaire type boards.
 * Added in assignment 4.
 */
public class AbstractMarbleSolitaire implements MarbleSolitaireModel {

  public int armThickness;
  public Marble[][] board;

  /**
   * Takes a specified arm thickness and board selector to construct a marble solitaire
   * MarbleSolitaireModelImpl.
   * @param armThickness the specified desired arm thickness of the MarbleSolitaireModelImpl.
   * @return the complete and ready to use marble solitaire MarbleSolitaireModelImpl (a 2D array
   *         with valid and invalid spaces marked appropriately).
   */
  protected Marble[][] setBoard(int armThickness) throws IllegalArgumentException {

    //Added in assignment 4 to specify which board to set.
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Arm Thickness must be a positive number.");
    }

    Marble[][] boardToReturn;

    if (this instanceof MarbleSolitaireModelImpl) {
      boardToReturn = setBoardDefault(armThickness);
    }
    else if (this instanceof  EuropeanSolitaireModelImpl) {
      boardToReturn = setBoardEuro(armThickness);
    }
    else {
      boardToReturn = setBoardTriangle(armThickness);
    }

    return boardToReturn;
  }

  /* Assignment 4 edits:
  Changed from a copy constructor to a normal method. Now takes a board and armThickness as
  parameters instead of an instance of MarbleSolitaireModelImpl alone. Changed to also return
  a board of type Marble[][].
   */
  /**
   * Used for copying target MarbleSolitaireModelImpl object.
   * @param another the target MarbleSolitaireModelImpl object to copy.
   */
  public Marble[][] marbleSolitaireModelCopy(Marble[][] another, int anotherArmThickness) {
    this.board = another;
    this.armThickness = anotherArmThickness;

    return this.board;
  }

  //Assignment 4 edits:
  //Changed the original setBoard method to this now that more board styles have been
  // introduced.
  /**
   * Used to set the default board.
   * @param armThickness is the desired arm thickness of the board.
   * @return a set default board with the desired arm thickness.
   * @throws IllegalArgumentException when arm thickness is not a odd number.
   */
  private Marble[][] setBoardDefault(int armThickness) throws IllegalArgumentException {

    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm Thickness must be an odd number.");
    }

    int i;
    int j;
    int dim = calcDim();

    Marble[][] board = new Marble[dim][dim];
    //Assign all Marble.INPLAY to array for ease of converting the rest to Marble.OOFB
    for (Marble[] row : board) {
      Arrays.fill(row, Marble.INPLAY);
    }

    for (i = 0; i < armThickness - 1; i++) {
      for (j = 0; j < armThickness - 1; j++) {
        board[i][j] = Marble.OOFB;
      }
    }

    for (i = 0; i < armThickness - 1; i++) {
      for (j = 2 * armThickness - 1; j < dim; j++) {
        board[i][j] = Marble.OOFB;
      }
    }

    for (i = (2 * armThickness - 1); i < dim; i++) {
      for (j = 0; j < armThickness - 1; j++) {
        board[i][j] = Marble.OOFB;
      }
    }

    for (i = (2 * armThickness - 1); i < dim; i++) {
      for (j = (2 * armThickness - 1); j < dim; j++) {
        board[i][j] = Marble.OOFB;
      }
    }

    return board;
  }

  //Assignment 4 edits:
  //Added.
  /**
   * Sets a European style marble solitaire board.
   * @param sideLength is the desired side length for the board.
   * @return a European style marble solitaire board.
   */
  private Marble[][] setBoardEuro(int sideLength) {

    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm Thickness must be an odd number.");
    }

    int i;
    int j;
    int dim = calcDim();

    Marble[][] board = new Marble[dim][dim];
    //Assign all Marble.INPLAY to array for ease of converting the rest to Marble.OOFB
    for (Marble[] row : board) {
      Arrays.fill(row, Marble.INPLAY);
    }

    for (i = 0; i <= sideLength; i++) {
      for (j = 0; j <= (sideLength - 2 - i); j++) {
        board[i][j] = Marble.OOFB;
        board[i][dim - 1 - j] = Marble.OOFB;
        board[dim - 1 - i][j] = Marble.OOFB;
        board[dim - 1 - i][dim - 1 - j] = Marble.OOFB;
      }
    }

    return board;
  }

  //Assignment 4 edits:
  //Added.
  /**
   * Sets the triangle version of marble solitaire with a given row number. Does not format board
   * string to look like an equilateral triangle.
   * @param rows the desired row number.
   * @return the board of type Marble[][] with all spaces of the triangle as Marble.INPLAY "O".
   */
  private Marble[][] setBoardTriangle(int rows) {

    int i;
    int j;

    Marble[][] board = new Marble[rows][rows];

    //Assign all Marble.OOFB to array for ease of converting the rest to Marble.OOFB
    for (Marble[] row : board) {
      Arrays.fill(row, Marble.OOFB);
    }

    for (i = 0; i < rows; i++) {

      for (j = 0; j < i + 1; j++) {

        board[i][j] = Marble.INPLAY;
      }
    }

    return board;
  }

  /* Assignment 4 edits:
  Changed to now use the new method for copying instead of a copy constructor. Copy is now
  a Marble[][] board type instead of an instance of MarbleSolitaireModelImpl. Still returns a
  Marble type (a space in the Marble[][] board).
   */
  /**
   * Used for getting a copy of the object mark at the given coordinates.
   * @param row the row index (from 0).
   * @param col the column index (from 0).
   * @return the object mark copy at the given parameters.
   */
  public Marble getMarkAt(int row, int col) {
    //Uses the copy constructor.
    Marble[][] copy = marbleSolitaireModelCopy(this.board, this.armThickness);

    return copy[row][col];
  }

  //Assignment 4 edits:
  //Simplified the logic to what it is now.
  /**
   * Places blank space at desired location if valid. Throws an exception if otherwise.
   *
   * @param sRow  blank desired row number (from 0).
   * @param sCol  blank desired column number (from 0).
   * @param board the board to be altered.
   * @return the board with blank placed at desired location.
   */
  protected Marble[][] setBlank(int sRow, int sCol, Marble[][] board) {

    int dim = determineDim();

    if (sRow >= 0 && sCol >= 0 && sRow < dim && sCol < dim
        && board[sRow][sCol].equals(Marble.INPLAY)) {

      board[sRow][sCol] = Marble.TAKEN;

      return board;
    }

    throw new IllegalArgumentException(String
        .format("Invalid empty cell position (%d,%d)", sRow, sCol));
  }

  /**
   * Determines the 2D array dimensions using the arm thickness.
   * @return the dimensions of the 2D in int form.
   */
  protected int calcDim() {
    return ((this.armThickness * 2) + (this.armThickness - 2));
  }

  /**
   * Determines if the direction between two positions on the board is horizontal.
   * @param fromRow is the original row index (from 0).
   * @param fromCol is the original column index (from 0).
   * @param toRow the next row index (from 0).
   * @param toCol the next column number (from 0).
   * @return a boolean value that is true when direction is horizontal and false otherwise.
   */
  private static boolean isHorizontal(int fromRow, int fromCol, int toRow, int toCol) {

    boolean isHorizontal = true;

    if ((Math.abs(fromRow - toRow) < 3) && (Math.abs(fromCol - toCol) < 3)) {
      if (fromRow != toRow) {
        isHorizontal = false;
      }
    }

    return isHorizontal;
  }

  //Assignment 4 edits:
  //Changed to accommodate the triangle shaped board by introducing more logic and splitting
  //into helper functions.
  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @throws IllegalArgumentException when any of the parameters are negative or the helper
   *         methods determine that the move is invalid for the specific board.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    int maxIndex = Math.max(Math.max(fromRow, fromCol), Math.max(toRow, toCol));
    int minIndex = Math.min(Math.min(fromRow, fromCol), Math.min(toRow, toCol));
    int dim = determineDim();

    if (maxIndex >= dim || minIndex < 0) {

      throw new IllegalArgumentException("Invalid move.");
    }

    //If a triangle shaped board.
    if (this instanceof TriangleSolitaireModelImpl) {

      moveTriangle(fromRow, fromCol, toRow, toCol);
    }

    //If a default or European style board.
    if (this instanceof MarbleSolitaireModelImpl || this instanceof EuropeanSolitaireModelImpl) {

      moveDefaultOrEuro(fromRow, fromCol, toRow, toCol);
    }
  }

  //Added in assignment 4.
  /**
   * Used to move a marble in a triangle shaped board, which is determined by the {@code move()}
   * method.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @throws IllegalArgumentException if move is determined to be invalid for a triangle shaped
   *         board. A move is invalid when it does not move horizontally or diagonally relative
   *         to the board.
   */
  private void moveTriangle(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {

    Marble to = this.board[toRow][toCol];
    Marble from = this.board[fromRow][fromCol];

    int rowDif = Math.abs(toRow - fromRow);
    int colDif = Math.abs(toCol - fromCol);

    int maxRowIndex = Math.max(fromRow, toRow);
    int maxColIndex = Math.max(fromCol, toCol);

    if (from == Marble.INPLAY && to == Marble.TAKEN && ((rowDif == 2) || (rowDif == 0))
        && ((colDif == 2) || (colDif == 0))) {

      if (fromRow == toRow && fromCol != toCol
          && this.board[fromRow][maxColIndex - 1] == Marble.INPLAY) {

        this.board[fromRow][fromCol] = Marble.TAKEN;
        this.board[toRow][toCol] = Marble.INPLAY;
        this.board[fromRow][maxColIndex - 1] = Marble.TAKEN;

      } else if (fromCol == toCol && fromRow != toRow
          && this.board[maxRowIndex - 1][fromCol] == Marble.INPLAY) {

        this.board[fromRow][fromCol] = Marble.TAKEN;
        this.board[toRow][toCol] = Marble.INPLAY;
        this.board[maxRowIndex - 1][fromCol] = Marble.TAKEN;

      } else if (fromCol != toCol && fromRow != toRow
          && this.board[maxRowIndex - 1][maxColIndex - 1] == Marble.INPLAY) {

        this.board[fromRow][fromCol] = Marble.TAKEN;
        this.board[toRow][toCol] = Marble.INPLAY;
        this.board[maxRowIndex - 1][maxColIndex - 1] = Marble.TAKEN;

      } else {

        throw new IllegalArgumentException("Invalid move");
      }
    } else {

      throw new IllegalArgumentException("Invalid move");
    }
  }

  //Added in assignment 4.
  /**
   * Used to move a default or European board from the move method.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @throws IllegalArgumentException when any of the parameters are out of bounds of
   *         the board, if the direction of movement is diagonal, if the spaces moved is not two,
   *         if the if the space being moved or the space in between the space being moved from
   *         and the space being moved to is not occupied by a marble, or if the space being moved
   *         to is occupied by a marble or is out of bounds.
   */
  private void moveDefaultOrEuro(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {

    Marble to = this.board[toRow][toCol];
    Marble from = this.board[fromRow][fromCol];
    Marble between = this.board[fromRow][fromCol];

    int rowDif = Math.abs(toRow - fromRow);
    int colDif = Math.abs(toCol - fromCol);

    int maxIndex = Math.max(Math.max(fromRow, fromCol), Math.max(toRow, toCol));

    if ((rowDif > 0) && (colDif > 0)) {

      throw new IllegalArgumentException("Invalid move.");
    }

    if (rowDif == 2 ^ colDif == 2 && maxIndex < calcDim()) {

      if (isHorizontal(fromRow, fromCol, toRow, toCol)) {

        between = this.board[fromRow][Math.min(fromCol, toCol) + 1];
      }

      if (!isHorizontal(fromRow, fromCol, toRow, toCol)) {

        between = this.board[Math.min(fromRow, toRow) + 1][fromCol];
      }

      if (between != Marble.INPLAY || from != Marble.INPLAY || to != Marble.TAKEN) {

        throw new IllegalArgumentException("Invalid Move.");
      }

      this.board[toRow][toCol] = Marble.INPLAY;
      this.board[fromRow][fromCol] = Marble.TAKEN;

      if (isHorizontal(fromRow, fromCol, toRow, toCol)) {

        this.board[fromRow][Math.min(fromCol, toCol) + 1] = Marble.TAKEN;
      }

      if (!isHorizontal(fromRow, fromCol, toRow, toCol)) {

        this.board[Math.min(fromRow, toRow) + 1][fromCol] = Marble.TAKEN;
      }
    } else {

      throw new IllegalArgumentException("Invalid move.");
    }
  }

  //Added in assignment 4.
  /**
   * Helper method to determine the defining dimension of the board.
   * @return the calculated dimension from {@code calcDim()} if a default or European board
   *         or the arm thickness if the board is triangular.
   */
  private int determineDim() {

    int dim;

    if (this instanceof TriangleSolitaireModelImpl) {

      dim = this.armThickness;
    }
    else {

      dim = calcDim();
    }

    return dim;
  }


  //Broken up into two helper methods for two cases.
  @Override
  public boolean isGameOver() {

    if (this instanceof TriangleSolitaireModelImpl) {
      return isOverTriangle();
    }

    return isOverDefaultEuro();
  }

  //Added in assignment 4.
  /**
   * Determines if a triangular board game is over.
   * @return a boolean value, which is true when the game is over and false when not over.
   */
  private boolean isOverTriangle() {

    int i;
    int j;
    int dim = determineDim();

    Marble toCheckU = this.board[0][0];
    Marble toCheckD = this.board[0][0];

    Marble toCheckURight;
    Marble toCheckULeft;
    Marble toCheckDRight;
    Marble toCheckDLeft;
    Marble toCheckHRight;
    Marble toCheckHLeft;

    boolean isOver = true;

    for (i = 1; i < dim; i++) {

      for (j = 0; j < dim - 1; j++) {

        if (this.board[i][j].equals(Marble.INPLAY)) {

          if (j == 0 && i < dim - 1) {

            toCheckU = this.board[i - 1][j];
            toCheckD = this.board[i + 1][j];
          }

          else if (i == j) {

            toCheckU = this.board[i - 1][j - 1];
            toCheckD = this.board[i + 1][j + 1];
          }
          if (i != j && j != 0 && i < dim - 1 && j < dim - 1) {

            toCheckURight = this.board[i - 1][j];
            toCheckULeft = this.board[i - 1][j - 1];
            toCheckDRight = this.board[i + 1][j + 1];
            toCheckDLeft = this.board[i + 1][j];
            toCheckHRight = this.board[i][j + 1];
            toCheckHLeft = this.board[i][j - 1];


            if ((toCheckURight.equals(Marble.INPLAY) ^ toCheckDLeft.equals(Marble.INPLAY))
                || (toCheckULeft.equals(Marble.INPLAY) ^ toCheckDRight.equals(Marble.INPLAY))
                || (toCheckHRight.equals(Marble.INPLAY) ^ toCheckHLeft.equals(Marble.INPLAY))) {

              isOver = false;
            }
          }

          if (i == dim - 1 && j > 0) {
            toCheckHRight = this.board[i][j + 1];
            toCheckHLeft = this.board[i][j - 1];

            if (toCheckHRight.equals(Marble.INPLAY) ^ toCheckHLeft.equals(Marble.INPLAY)) {

              isOver = false;
            }

          }

          if ((toCheckU.equals(Marble.INPLAY) && toCheckD.equals(Marble.TAKEN))
              || (toCheckU.equals(Marble.TAKEN) && toCheckD.equals(Marble.INPLAY))) {

            isOver = false;
          }
        }
      }
    }

    return isOver;
  }

  //Added in assignment 4.
  /**
   * Determines if a default or European board game is over.
   * @return a boolean value, which is true when the game is over and false when not over.
   */
  private boolean isOverDefaultEuro() {
    boolean isOver = true;
    //Adjacent spaces to a marble.
    Marble aRight;
    Marble aLeft;
    Marble aTop;
    Marble aBot;
    int i;
    int j;
    int dim = determineDim();

    for (i = 0; i < dim; i++) {
      for (j = 0; j < dim; j++) {
        if (this.board[i][j].equals(Marble.INPLAY)) {

          if ((i > 0 && i < dim - 1) && (j > 0 && j < dim - 1)) {

            aRight = this.board[i][j + 1];
            aLeft = this.board[i][j - 1];
            aTop = this.board[i - 1][j];
            aBot = this.board[i + 1][j];

            if ((aRight.equals(Marble.INPLAY) ^ aLeft.equals(Marble.INPLAY)) || (
                aTop.equals(Marble.INPLAY) ^ aBot.equals(Marble.INPLAY))) {

              isOver = false;
            }
          }

          if ((i == 0 || i == dim - 1) && j != 0 && j < dim - 1) {

            aRight = this.board[i][j + 1];
            aLeft = this.board[i][j - 1];

            if ((aRight.equals(Marble.INPLAY) ^ aLeft.equals(Marble.INPLAY))
                && ((aRight != Marble.OOFB) && (aLeft != Marble.OOFB))) {

              isOver = false;
            }
          }

          if ((j == 0 || j == dim - 1) && i != 0 && i < dim - 1) {

            aTop = this.board[i - 1][j];
            aBot = this.board[i + 1][j];

            if ((aTop.equals(Marble.INPLAY) ^ aBot.equals(Marble.INPLAY)) && ((aTop != Marble.OOFB)
                && (aBot != Marble.OOFB))) {

              isOver = false;
            }
          }
        }
      }
    }

    return isOver;
  }

  //Added in assignment 4.
  /**
   * Used to help getGameState() triangle board setup. Gets the number of INPLAY or TAKEN
   * spaces for a given row of the Marble[][] board.
   * @param row is the row to be verified.
   * @return the number of INPLAY spaces in the corresponding row.
   */
  private static int getInplayCount(Marble[] row) {

    int count = 0;

    for (Marble x : row) {
      if (x.equals(Marble.INPLAY) || x.equals(Marble.TAKEN)) {
        count += 1;
      }
    }

    return count;
  }

  //Added the triangle setup if an instanceof it. stringBoard2 was replaced with a more concise
  //return statement that incorporated it.
  @Override
  public String getGameState() {

    String untrimmed;

    //For triangle board.
    if (this instanceof TriangleSolitaireModelImpl) {

      int rowNum = this.armThickness;

      untrimmed = Arrays.stream(board).map(
          row -> (new String(new char[rowNum - getInplayCount(row)]).replace(
              "\0", " ")) + Arrays.stream(row).map(p -> p == Marble.OOFB  ? " " :
              p.toString()).collect(Collectors.joining(" ")))
          .collect(Collectors.joining("\n"));
    }
    //For default and European board.
    else {

      untrimmed = Arrays.stream(board).map(
          row -> "" + Arrays.stream(row).map(
              p -> p == Marble.OOFB ? " " : p.toString()).collect(Collectors.joining(" ")))
          .collect(Collectors.joining("\n"));
    }

    String stringBoard1 = untrimmed.replaceAll("\\s+\n|\\s++$", "\n");

    return stringBoard1.replaceAll("\\s++$", "");
  }

  @Override
  public int getScore() {

    int dim = determineDim();
    int marbles = 0;
    int i;
    int j;

    for (i = 0; i < dim; i++) {
      for (j = 0; j < dim; j++) {

        if (this.board[i][j].equals(Marble.INPLAY)) {
          marbles += 1;
        }
      }
    }

    return marbles;
  }

}
