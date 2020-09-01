package cs3500.marblesolitaire;

import static java.lang.Integer.parseInt;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import java.io.InputStreamReader;

/**
 * Allows for commandline implementation. Takes in arguments for the 3 types of boards and their
 * 4 different.
 */
public final class MarbleSolitaire {

  /**
   * Main method for running commandline interaction.
   * @param args commandline input that can be in the format of typeofboard -size # -hole # #.
   */
  public static void main(String[] args) {

    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();

    String model;
    int size;
    int holeRow;
    int holeCol;

    switch (args.length) {
      case 1:
        model = args[0];

        switch (model) {
          case "english":
            m = new MarbleSolitaireModelImpl();
            break;

          case "triangular":
            m = new TriangleSolitaireModelImpl();
            break;

          default:
            m = new EuropeanSolitaireModelImpl();
            break;
        }

        break;

      case 3:
        model = args[0];

        size = parseInt(args[2]);
        System.out.println(size);

        switch (model) {
          case "english":
            m = new MarbleSolitaireModelImpl(size);
            break;

          case "triangular":
            m = new TriangleSolitaireModelImpl(size);
            break;

          default:
            m = new EuropeanSolitaireModelImpl(size);
            break;
        }

        break;

      case 4:
        model = args[0];
        holeRow = parseInt(args[2]);
        holeCol = parseInt(args[3]);

        switch (model) {
          case "english":
            m = new MarbleSolitaireModelImpl(holeRow, holeCol);
            break;

          case "triangular":
            m = new TriangleSolitaireModelImpl(holeRow, holeCol);
            break;

          default:
            m = new EuropeanSolitaireModelImpl(holeRow, holeCol);
            break;
        }

        break;

      case 6:
        model = args[0];
        size = parseInt(args[2]);
        holeRow = parseInt(args[4]);
        holeCol = parseInt(args[5]);

        switch (model) {
          case "english":
            m = new MarbleSolitaireModelImpl(size, holeRow, holeCol);
            break;

          case "triangular":
            m = new TriangleSolitaireModelImpl(size, holeRow, holeCol);
            break;

          default:
            m = new EuropeanSolitaireModelImpl(size, holeRow, holeCol);
            break;
        }

        break;

      default:
        System.out.println("Invalid entry.");
    }

    new MarbleSolitaireControllerImpl(new InputStreamReader(System.in),
        System.out).playGame(m);
  }
}
