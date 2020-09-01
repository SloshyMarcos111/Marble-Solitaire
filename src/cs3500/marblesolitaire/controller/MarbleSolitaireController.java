package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the controller for the Marble Solitaire model and takes input from the user
 * and displays messages to the user.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new Marble Solitaire game with the MarbleSolitaireModel and takes in values that start
   * from 1.
   * @param m is the MarbleSolitaireModel model that will be used to play.
   * @throws IllegalArgumentException is thrown when model provided, m, is null or when the
   *         controller is unable to receive input or transmit output.
   * @throws IllegalStateException is thrown when append fails.
   */
  void playGame(MarbleSolitaireModel m) throws IllegalArgumentException, IllegalStateException;
}
