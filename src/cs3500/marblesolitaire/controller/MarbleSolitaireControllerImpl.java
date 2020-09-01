package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the controller for the Marble Solitaire model and takes input from the user
 * and displays messages to the user.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructor for the model controller that takes input from the user and displays messages
   * to the user as well.
   * @param rd the input from the user to the controller.
   * @param ap the output displayed to the user through the controller.
   * @throws IllegalArgumentException when either field rd or ap is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("null");
    }

    try {
      this.scan = new Scanner(rd);
      this.out = Objects.requireNonNull(ap);
    }
    catch (NullPointerException npe) {
      throw new IllegalArgumentException("Fields rd and ap must not be null.", npe);
    }
  }

  @Override
  public void playGame(MarbleSolitaireModel m) throws IllegalArgumentException,
      IllegalStateException {

    try {
      Objects.requireNonNull(m);
    }
    catch (NullPointerException npe) {
      throw new IllegalArgumentException("Model must not be null.", npe);
    }

    int fromRowInt;
    int fromColInt;
    int toRowInt;
    int toColInt;

    appendHelper(m.getGameState() + "\n" + "Score: " + m.getScore() + "\n"
        + "Enter the current row and column of the " + "marble you want to move and the row and "
        + "column you want to move it to.");

    while (true) {

      if (!this.scan.hasNext()) {
        break;
      }
      //Trying to get fromRow
      fromRowInt = tryGetCoordinate(m);

      if (fromRowInt == -1) {
        return;
      }

      if (!this.scan.hasNext()) {
        break;
      }

      //Trying to get fromCol
      fromColInt = tryGetCoordinate(m);

      if (fromColInt == -1) {
        return;
      }

      if (!this.scan.hasNext()) {
        break;
      }

      //Trying to get toRow
      toRowInt = tryGetCoordinate(m);

      if (toRowInt == -1) {
        return;
      }

      if (!this.scan.hasNext()) {
        break;
      }

      //Trying to get toCol
      toColInt = tryGetCoordinate(m);

      if (toColInt == -1) {
        return;
      }

      //Attempt to move model marble.
      moveModel(fromRowInt, fromColInt, toRowInt, toColInt, m);

      //Check if game is over.
      if (gameOver(m)) {
        return;
      }
    }

    throw new IllegalStateException("No more inputs.");
  }

  /**
   * Attempts to append and display to user.
   * @param toAppend the string to append.
   */
  private void appendHelper(String toAppend) {

    try {
      this.out.append(toAppend).append("\n");
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  /**
   * Determines if the user input is a positive integer. Only determines validity in the scope
   * of the controller (not the model move function) and outputs a message to the user if invalid.
   * @param coordinate is the string input from the user.
   * @return -1 if the input is invalid, or the original input parsed to an int.
   * @throws IllegalStateException if the append fails.
   */
  private int getMoveHelper(String coordinate) throws IllegalStateException {

    int coordinateInt;

    try {
      coordinateInt = Integer.parseInt(coordinate);

      if (coordinateInt <= 0) {

        appendHelper(coordinate + " is an invalid entry.");
        return -1;
      }
    }
    catch (NumberFormatException nfe) {
      /*try {
        this.out.append(coordinate + " is an invalid entry.\n");
        return -1;
      }
      catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }*/

      appendHelper(coordinate + " is an invalid entry.");

      return -1;
    }

    return coordinateInt;
  }

  /**
   * Attempts to move marble with parameters and model passed.
   * @param fromRow original marble row.
   * @param fromCol original marble column.
   * @param toRow next marble row.
   * @param toCol next marble column.
   * @param m Marble Solitaire model being used.
   * @throws IllegalStateException when append fails.
   */
  private void moveModel(int fromRow, int fromCol, int toRow, int toCol,
      MarbleSolitaireModel m) throws IllegalStateException {
    try {
      m.move(fromRow, fromCol, toRow, toCol);
    }
    catch (IllegalArgumentException iae) {
      /*try {
        this.out.append("Invalid move. Play again. Move out of bounds or direction is"
            + " invalid.\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }*/
      appendHelper("Invalid move. Play again. Move out of bounds or direction is"
          + " invalid.");
    }
  }

  /**
   * Checks if the game is over with the model passed.
   * @param m the Marble Solitaire model being used.
   * @return a boolean value that is true when the game is over and false otherwise.
   * @throws IllegalStateException when append fails.
   */
  private boolean gameOver(MarbleSolitaireModel m) throws IllegalStateException {
    if (m.isGameOver()) {
      /*try {
        this.out.append("Game over!\n" + m.getGameState() + "\n");
        this.out.append("Score: " + m.getScore());
        return true;
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }*/
      appendHelper("Game over!\n" + m.getGameState() + "\n" + "Score: " + m.getScore());
      return true;
    }
    else {
      /*try {
        this.out.append(m.getGameState() + "\n");
        this.out
            .append("Score: " + m.getScore() + "\n" + "Enter the current row and column of the "
                + "marble you want to move and the row and column you want to move it to.\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }*/
      appendHelper(m.getGameState() + "\n" + "Score: " + m.getScore() + "\n"
          + "Enter the current row and column" + " of the marble you want to move and the row "
          + "and column you want to move it to.");
      return false;
    }
  }

  /**
   * Attempts to scan for a valid coordinate.
   * @param m the Marble Solitaire model being used.
   * @return an int value of the coordinate ( adjusted to start from 1) or a -1 value that
   *         signifies a "quit".
   */
  private int tryGetCoordinate(MarbleSolitaireModel m) {
    int loop = -1;
    String coordinate;
    int coordinateInt;

    while (loop == -1) {
      coordinate = this.scan.next();

      if (isQuit(coordinate, m)) {
        return - 1;
      }

      loop = getMoveHelper(coordinate);
    }

    coordinateInt = loop - 1;
    return coordinateInt;
  }

  /**
   * Determines if the game has been quit by the user by input of "q" or "Q".
   * @param in the user input read as a string box type.
   * @param m the MarbleSolitaireModel that is being played with.
   * @return a boolean value with true and false corresponding to quit and not quit respectively.
   * @throws IllegalArgumentException when append fails.
   */
  private boolean isQuit(String in, MarbleSolitaireModel m) throws IllegalStateException {
    if (in.equals("q") || in.equals("Q")) {
      /*try {
        this.out.append("Game quit!\nState of game when quit:\n" + m.getGameState() + "\nScore: "
            + m.getScore());*/
      appendHelper("Game quit!\nState of game when quit:\n" + m.getGameState()
          + "\nScore: " + m.getScore());
      return true;

      /*} catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }*/
    } else {
      return false;
    }
  }
}
