package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw02.Variables;

/**
 * Represents a Marble Solitaire Model implementation of {@code MarbleSolitaireModel} of a European
 * board. It will maintain the state of the game and allow a client to specify moves.
 */
public class EuropeanSolitaireModelImpl extends MarbleSolitaireModelImpl {

  /**
   * Constructs a {@code MarbleSolitaireModel} with European board with default arm thickness of 3
   * and empty slot located at the very center.
   */
  public EuropeanSolitaireModelImpl() {
    this.armThickness = 3;
    int center = this.calcBoardCenter();
    this.createHexBoard();
    this.gameBoard[center][center] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with European board with customized arm thickness and
   * empty slot located at (0,0).
   *
   * @param armThickness the given arm thickness of the european board
   * @throws IllegalArgumentException if the arm thickness entered is invalid.
   */
  public EuropeanSolitaireModelImpl(int armThickness) {
    if (checkArm(armThickness)) {
      this.armThickness = armThickness;
    }
    this.createHexBoard();
    int center = this.calcBoardCenter();
    this.gameBoard[center][center] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with European board with default arm thickness of 3
   * and empty slot located at the customized row and col given as {@code sRow} and {@code sCol},
   * respectively.
   *
   * @param sRow the row that the empty slot is in
   * @param sCol the column that the empty slot is in
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space
   * @throws IllegalArgumentException if the empty slot location is out of bounds.
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    this.createHexBoard();
    if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("invalid empty slot location");
    }
    if (checkInitEmpty(sRow, sCol)) {
      this.gameBoard[sRow][sCol] = Variables.Empty;
    }
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with triangular board with customized arm thickness
   * and empty slot located at the customized row and col given as {@code sRow} and {@code sCol},
   * respectively.
   *
   * @param armThickness the arm thickness of the European board.
   * @param sRow         the row that the empty slot is in
   * @param sCol         the column that the empty slot is in
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space
   * @throws IllegalArgumentException if the empty slot location is out of bounds.
   * @throws IllegalArgumentException if the arm thickness entered is invalid.
   */
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (checkArm(armThickness)) {
      this.armThickness = armThickness;
    }
    this.createHexBoard();
    if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("invalid empty slot location");
    }
    if (checkInitEmpty(sRow, sCol)) {
      this.gameBoard[sRow][sCol] = Variables.Empty;
    }
  }

  // a helper method that created the hexagonal board without empty slot using given length
  private void createHexBoard() {
    int boardLength = this.calcBoardLength();
    this.gameBoard = new Variables[boardLength][boardLength];

    int leftEndMiddle = this.armThickness - 1; // 5 --> 4
    int rightEndMiddle = 2 * this.armThickness - 2; // 5 --> 8

    int i = 0;
    //go across board
    while (i < boardLength) {

      //fill middle
      this.gameBoard[this.calcBoardCenter()][i] = Variables.Marble;

      if (i < leftEndMiddle) {
        //fill going up
        int a = this.calcBoardCenter() - 1;
        while (a >= 0) {
          if (a >= (this.calcBoardCenter() - (Math.floor(this.armThickness / 2) + i))) {
            this.gameBoard[a][i] = Variables.Marble;
          } else {
            this.gameBoard[a][i] = Variables.Space;
          }
          a--;
        }

        //fill going down
        int b = this.calcBoardCenter() + 1;
        while (b < boardLength) {
          if (b > (this.calcBoardCenter() + (Math.floor(this.armThickness / 2) + i))) {
            this.gameBoard[b][i] = Variables.Space;
          } else {
            this.gameBoard[b][i] = Variables.Marble;
          }
          b++;
        }
      }

      if (i >= leftEndMiddle && i <= rightEndMiddle) {
        //fills with marbles going up
        int c = this.calcBoardCenter() - 1;
        while (c >= 0) {
          this.gameBoard[c][i] = Variables.Marble;
          c--;
        }

        //fills with marbles going down
        int d = this.calcBoardCenter() + 1;
        while (d < boardLength) {
          this.gameBoard[d][i] = Variables.Marble;
          d++;
        }
      }

      if (i > rightEndMiddle) {
        //fill going up
        int e = this.calcBoardCenter() - 1;
        while (e >= 0) {
          if (e >= (this.calcBoardCenter()
              - ((Math.floor(this.armThickness / 2) + ((this.calcBoardLength() - i) - 1))))) {
            this.gameBoard[e][i] = Variables.Marble;
          } else {
            this.gameBoard[e][i] = Variables.Space;
          }
          e--;
        }

        //fill going down
        int f = this.calcBoardCenter() + 1;
        while (f < boardLength) {
          if (f <= (this.calcBoardCenter()
              + ((Math.floor(this.armThickness / 2) + ((this.calcBoardLength() - i) - 1))))) {
            this.gameBoard[f][i] = Variables.Marble;
          } else {
            this.gameBoard[f][i] = Variables.Space;
          }
          f++;
        }
      }
      i++;
    }
  }


  // a helper method that checks to see if the arm thickness is valid for a EuropeanSolitaireModel
  private boolean checkArm(int armThickness) {
    if (armThickness < 3) {
      throw new IllegalArgumentException("Arm thickness is too low.");
    } else if ((armThickness % 2) == 0) {
      throw new IllegalArgumentException("Arm thickness must be an odd number.");
    }
    return true;
  }

  // a helper method that checks to see if the given initial empty location is valid
  private boolean checkInitEmpty(int sRow, int sCol) {
    if (sRow < 0 || sRow >= this.calcBoardLength()
        || sCol < 0 || sCol >= this.calcBoardLength()) {
      throw new IllegalArgumentException("Initial empty spot is out of bounds.");
    }
    return true;
  }

  @Override
  public String getGameState() {

    String displayLayoutSoFar = "";
    String p1 = "";
    String d1 = "\n";

    for (int i = 0; i < this.gameBoard.length; i++) {
      String rowSoFar = "";
      String p2 = "";
      String d2 = " ";
      for (int j = 0; j < this.gameBoard.length; j++) {
        if ((j >= (this.gameBoard.length - this.armThickness)
            && (this.gameBoard[i][j] != Variables.Space))) {
          rowSoFar =
              String.join(p2, rowSoFar, this.gameBoard[i][j].toString());
          p2 = d2;
        } else if (j < (this.gameBoard.length - this.armThickness)) {
          rowSoFar =
              String.join(p2, rowSoFar, this.gameBoard[i][j].toString());
          p2 = d2;
        }
      }
      displayLayoutSoFar = String.join(p1, displayLayoutSoFar, rowSoFar);
      p1 = d1;
    }
    return displayLayoutSoFar;
  }
}
