package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import java.io.InputStreamReader;

/**
 * Represents the main function that creates the {@code MarbleSolitaireModel} and {@code
 * MarbleSolitaireController} and sets them up for the game.
 */
public final class MarbleSolitaire {

  /**
   * Sets up the game by gathering the right arguments and creating a
   * model and controller.
   * @param args a finite array of arguments that the user inputs to start the game
   */
  public static void main(String[] args) {
    int size = 0;
    int sRow = 0;
    int sCol = 0;
    String typeModel = "";
    MarbleSolitaireModel model;
    MarbleSolitaireController controller;

    int i = 0;
    while (i < args.length) {

      switch (args[i]) {
        case "-size":
          try {
            size = Integer.parseInt(args[i + 1]);
            i = i + 2;
          } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid size.");
          }
          break;
        case "-hole":
          try {
            sRow = Integer.parseInt(args[i + 1]);
            sCol = Integer.parseInt(args[i + 2]);
            i = i + 3;
          } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid hole.");
          }
          break;
        case "european":
          typeModel = "european";
          size = 3;
          sRow = 3;
          sCol = 3;
          break;
        case "triangular":
          typeModel = "triangular";
          size = 5;
          sRow = 0;
          sCol = 0;
          break;
        case "english":
          typeModel = "english";
          size = 3;
          sRow = 3;
          sCol = 3;
          break;
        default:
          System.out.println(String.format("Unknown command %s", args[i]));
          break;
      }
    }

    if (typeModel.equals("european")) {
      model = new EuropeanSolitaireModelImpl(size, sRow, sCol);
    } else if (typeModel.equals("triangular")) {
      model = new TriangleSolitaireModelImpl(size, sRow, sCol);
    } else if (typeModel.equals("english")) {
      model = new MarbleSolitaireModelImpl(size, sRow, sCol);
    } else {
      throw new IllegalArgumentException("Invalid model type.");
    }

    controller = new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);
    controller.playGame(model);
  }
}


