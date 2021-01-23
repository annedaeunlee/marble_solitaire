package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire controller: "running" a
 * game of Marble Solitaire, asking for input and printing the game state. One object of the
 * controller represents one game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * This method plays a new game of Marble Solitaire using the provided model.
   *
   * @param model the provided model - enforces game state and rules - for the game.
   * @throws IllegalArgumentException if the provided model is null.
   * @throws IllegalStateException    if the controller is unable to successfully receive input or
   *                                  transmit output.
   */
  void playGame(MarbleSolitaireModel model);
}
