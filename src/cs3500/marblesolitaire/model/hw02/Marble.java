package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a peg position in the game of marble solitaire.
 */
public enum Marble {
  INPLAY("O"),
  TAKEN("_"),
  OOFB(" ");

  private final String disp;

  Marble(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }

}
