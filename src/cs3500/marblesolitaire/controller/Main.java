package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.InputStreamReader;

/**
 * Main for running the Marble Solitaire model and controller.
 */
public class Main {

  /**
   * Main method for running commandline input.
   * @param args arguments for main method.
   */
  public static void main(String[] args) {
    new MarbleSolitaireControllerImpl(new InputStreamReader(System.in),
        System.out).playGame(new MarbleSolitaireModelImpl());
  }
}
