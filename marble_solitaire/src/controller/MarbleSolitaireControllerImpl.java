package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Marble Solitaire Controller implementation of {@code MarbleSolitaireController}. It
 * will run a game of Marble Solitaire, asking for input and printing the game state.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  //Readable and Appendable are two existing interfaces
  // in Java that abstract input and output respectively
  // more fields?
  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructs a Marble Solitaire Controller that accepts and stores these objects for doing input
   * and output.
   *
   * @param rd receives the input coming from the user.
   * @param ap receives the output that will be sent to the user.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Either the input or output stream is null.");
    }
    this.out = ap;
    this.scan = new Scanner(rd);
  }

  @Override
  public void playGame(MarbleSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    try {

      int fromRow = -1;
      int fromCol = -1;
      int toRow = -1;
      int toCol = -1;
      int counter = 0;
      while (!model.isGameOver()) {
        if (this.scan.hasNext()) {
          String next = this.scan.next();
          try {
            int nextNumForm = Integer.parseInt(next) - 1;
            if (counter % 4 == 0) {
              fromRow = nextNumForm;
            } else if (counter % 4 == 1) {
              fromCol = nextNumForm;
            } else if (counter % 4 == 2) {
              toRow = nextNumForm;
            } else if (counter % 4 == 3) {
              toCol = nextNumForm;
            }
            counter = counter + 1;
          } catch (NumberFormatException ex) {
            if (next.equals("Q") || next.equals("q")) {
              outputWhenQuit(next, model);
              break;
            } else {
              this.out.append("Invalid move. Please enter a different value.\n");
            }
          }
          if (counter > 0 && (counter % 4 == 0)) {
            try {
              model.move(fromRow, fromCol, toRow, toCol);
              outputWhenMove(model);
            } catch (IllegalArgumentException iae) {
              this.out.append("Invalid move. Play again. " + iae.getMessage() + "\n");
            }
          }
        } else if (!this.scan.hasNext()) {
          throw new IllegalStateException();
        }
      }
      if (model.isGameOver()) {
        outputWhenGameOver(model);
      }
    } catch (IOException error) {
      throw new IllegalStateException("controller is unable to successfully receive input "
          + "or transmit output", error);
    }
  }

  //a helper method that determines what to output in the case that the input is Q
  private void outputWhenQuit(String next, MarbleSolitaireModel model) throws IOException {
    this.out.append("Game quit!\n" + "State of game when quit:\n"
        + model.getGameState() + "\nScore: " + model.getScore() + "\n");
  }

  //a helper method that determines what to output in the case that a move can be made
  private void outputWhenMove(MarbleSolitaireModel model) throws IOException {
    this.out.append(model.getGameState() + "\nScore: " + model.getScore() + "\n");
  }

  //a helper method that determines what to output in the case that a game is over
  private void outputWhenGameOver(MarbleSolitaireModel model) throws IOException {
    this.out.append("Game over!\n" + model.getGameState() + "\nScore: " + model.getScore() + "\n");
  }


}
