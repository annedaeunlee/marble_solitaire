package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw02.Variables;

/**
 * Represents a Marble Solitaire Model implementation of {@code MarbleSolitaireModel} of a
 * triangular board. It will maintain the state of the game and allow a client to specify moves.
 */
public class TriangleSolitaireModelImpl extends MarbleSolitaireModelImpl {

  /**
   * Constructs a {@code MarbleSolitaireModel} with triangular board with default number of pegs in
   * its bottom-most row is 5 and empty slot is located at (0,0).
   */
  public TriangleSolitaireModelImpl() {
    this.armThickness = 5;
    this.createTriangleBoard();
    this.gameBoard[0][0] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with triangular board with customized number of pegs
   * in its bottom-most row and empty slot located at (0,0).
   *
   * @param dimensions the number of pegs in bottom row
   * @throws IllegalArgumentException if the dimension entered is invalid.
   */
  public TriangleSolitaireModelImpl(int dimensions) {
    if (dimensions < 2) {
      throw new IllegalArgumentException("dimension entered is too small.");
    }
    this.armThickness = dimensions;
    this.createTriangleBoard();
    this.gameBoard[0][0] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with triangular board with default number of pegs in
   * its bottom-most row of 5 and empty slot located at the customized row and col given as {@code
   * sRow} and {@code sCol}, respectively.
   *
   * @param sRow the row that the empty slot is in
   * @param sCol the column that the empty slot is in
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space
   * @throws IllegalArgumentException if the empty slot location is out of bounds.
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 5;
    this.createTriangleBoard();
    if (sRow < 0 || sRow >= this.gameBoard.length || sCol < 0 || sCol >= this.gameBoard.length) {
      throw new IllegalArgumentException("desired empty slot location is out of bounds.");
    } else if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("Invalid empty cell position ("
          + Integer.toString(sRow) + "," + Integer.toString(sCol) + ").");
    } else {
      this.gameBoard[sRow][sCol] = Variables.Empty;
    }
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with triangular board with customized number of pegs
   * in its bottom-most row and empty slot located at the customized row and col given as {@code
   * sRow} and {@code sCol}, respectively.
   *
   * @param dimensions the number of pegs in bottom row
   * @param sRow       the row that the empty slot is in
   * @param sCol       the column that the empty slot is in
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space
   * @throws IllegalArgumentException if the empty slot location is out of bounds.
   * @throws IllegalArgumentException if the dimension entered is invalid.
   */
  public TriangleSolitaireModelImpl(int dimensions, int sRow, int sCol) {
    if (dimensions < 2) {
      throw new IllegalArgumentException("dimension entered is too small.");
    }
    this.armThickness = dimensions;
    this.createTriangleBoard();
    if (sRow < 0 || sRow >= this.gameBoard.length || sCol < 0
        || sCol >= this.gameBoard.length) {
      throw new IllegalArgumentException("desired empty slot location is out of bounds.");
    } else if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("Invalid empty cell position ("
          + Integer.toString(sRow) + "," + Integer.toString(sCol) + ").");
    } else {
      this.gameBoard[sRow][sCol] = Variables.Empty;
    }
  }

  // a helper method that created a standard triangle board without the empty slot
  private void createTriangleBoard() {
    this.gameBoard = new Variables[this.armThickness][this.armThickness];

    int i = 0;
    while (i < this.armThickness) {
      int j = 0;
      while (j < this.armThickness) {
        if (j > i) {
          this.gameBoard[i][j] = Variables.Space;
        } else {
          this.gameBoard[i][j] = Variables.Marble;
        }
        j++;
      }
      i++;
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow < 0 || toRow >= this.gameBoard.length
        || toCol < 0 || toCol >= this.gameBoard.length) {
      throw new IllegalArgumentException("Desired spot to move into is out of bounds.");
    } else if (this.gameBoard[fromRow][fromCol] != Variables.Marble) {
      throw new IllegalArgumentException("Can only move marbles!");
    } else if (this.gameBoard[toRow][toCol] != Variables.Empty) {
      throw new IllegalArgumentException("Can only move into empty slots!");
    } else if ((Math.abs(fromRow - toRow)) == 2 && fromCol == toCol) {
      int midCol = fromCol;
      int midRow = (fromRow + toRow) / 2;
      if (this.gameBoard[midRow][midCol] == Variables.Marble) {
        this.updateBoard(fromRow, fromCol, toRow, toCol, midRow, midCol);
      } else {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
    } else if ((Math.abs(fromCol - toCol)) == 2 && fromRow == toRow) {
      int midCol = (fromCol + toCol) / 2;
      int midRow = fromRow;
      if (this.gameBoard[midRow][midCol] == Variables.Marble) {
        this.updateBoard(fromRow, fromCol, toRow, toCol, midRow, midCol);
      } else {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
    } else if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2) {
      int midRow = (fromRow + toRow) / 2;
      int midCol = (fromCol + toCol) / 2;
      if (this.gameBoard[midRow][midCol] == Variables.Marble) {
        this.updateBoard(fromRow, fromCol, toRow, toCol, midRow, midCol);
      } else {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
    } else {
      throw new IllegalArgumentException("Invalid move. Must jump over exactly one marble.");
    }
  }

  @Override
  public boolean isNextToMarble(int i, int j, int boardLength) {
    boolean soFar = false;

    if (((i + 2) >= 0) && ((i + 2) < boardLength)) {
      soFar = soFar || (this.gameBoard[i + 1][j] == Variables.Marble
          && this.gameBoard[i + 2][j] == Variables.Empty);
    }
    if (((i - 2) >= 0) && ((i - 2) < boardLength)) {
      soFar = soFar || (this.gameBoard[i - 1][j] == Variables.Marble
          && this.gameBoard[i - 2][j] == Variables.Empty);
    }
    if (((j + 2) >= 0) && ((j + 2) < boardLength)) {
      soFar = soFar || (this.gameBoard[i][j + 1] == Variables.Marble
          && this.gameBoard[i][j + 2] == Variables.Empty);
    }
    if (((j - 2) >= 0) && ((j - 2) < boardLength)) {
      soFar = soFar || (this.gameBoard[i][j - 1] == Variables.Marble
          && this.gameBoard[i][j - 2] == Variables.Empty);
    }
    if (((i - 2) >= 0) && ((j - 2) >= 0)) {
      soFar = soFar || (this.gameBoard[i - 1][j - 1] == Variables.Marble
          && this.gameBoard[i - 2][j - 2] == Variables.Empty);
    }
    if (((i + 2) < boardLength) && ((j + 2) < boardLength)) {
      soFar = soFar || (this.gameBoard[i + 1][j + 1] == Variables.Marble
          && this.gameBoard[i + 2][j + 2] == Variables.Empty);
    }
    return soFar;
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
        if (j < i + 1) {
          rowSoFar =
              String.join(p2, rowSoFar, this.gameBoard[i][j].toString());
          p2 = d2;
        }
      }
      String begSpaces = "";
      for (int k = 0; k < this.gameBoard.length - i - 1; k++) {
        begSpaces = begSpaces + " ";
      }
      rowSoFar = begSpaces + rowSoFar;
      displayLayoutSoFar = String.join(p1, displayLayoutSoFar, rowSoFar);
      p1 = d1;
    }
    return displayLayoutSoFar;
  }
}
