package cs3500.marblesolitaire.model.hw02;


/**
 * Represents a Marble Solitaire Model implementation of {@code MarbleSolitaireModel}. that will
 * maintain the state of the game and allow a client to specify moves.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  //these arguments are changed to protected for the other implementations to have access
  protected int armThickness;
  protected Variables[][] gameBoard;

  /**
   * Constructs a {@code MarbleSolitaireModel} with default arm thickness being 3 and empty slot at
   * the center.
   */
  public MarbleSolitaireModelImpl() {
    this.armThickness = 3;
    int boardLength = this.calcBoardLength();
    int boardCenter = this.calcBoardCenter();

    this.gameBoard = new Variables[boardLength][boardLength];

    for (int i = 0; i < boardLength; i++) {
      for (int j = 0; j < boardLength; j++) {
        if (((i < (armThickness - 1)) || (i > (boardLength - armThickness)))
            && (((j < (armThickness - 1)) || (j > (boardLength - armThickness))))) {
          this.gameBoard[i][j] = Variables.Space;
        } else {
          this.gameBoard[i][j] = Variables.Marble;
        }
      }
    }

    this.gameBoard[boardCenter][boardCenter] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} in terms of arm thickness of 3 and empty slot located
   * at the row and col given as {@code sRow} and {@code sCol}, respectively.
   *
   * @param sRow the row that the empty slot is in
   * @param sCol the column that the empty slot is in
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space (corners)
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armThickness = 3;
    int boardLength = this.calcBoardLength();

    this.gameBoard = new Variables[boardLength][boardLength];
    for (int i = 0; i < boardLength; i++) {
      for (int j = 0; j < boardLength; j++) {
        if (((i < (armThickness - 1)) || (i > (boardLength - armThickness)))
            && (((j < (armThickness - 1)) || (j > (boardLength - armThickness))))) {
          this.gameBoard[i][j] = Variables.Space;
        } else {
          this.gameBoard[i][j] = Variables.Marble;
        }
      }
    }

    if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("Invalid empty cell position ("
          + Integer.toString(sRow) + "," + Integer.toString(sRow) + ").");
    }
    this.gameBoard[sRow][sCol] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} in terms of arm thickness of the given number and
   * empty slot located at the center.
   *
   * @param armThickness the number of marbles at the end of rows/columns
   * @throws IllegalArgumentException if given arm thickness is less than three, then it would be an
   *                                  automatic win.
   * @throws IllegalArgumentException if given arm thickness is an even number
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    this.armThickness = armThickness;
    int boardLength = this.calcBoardLength();
    int boardCenter = this.calcBoardCenter();

    if (armThickness < 3) {
      throw new IllegalArgumentException("Arm thickness is too low.");
    } else if ((armThickness % 2) == 0) {
      throw new IllegalArgumentException("Arm thickness must be an odd number.");
    } else {
      this.gameBoard = new Variables[boardLength][boardLength];
      for (int i = 0; i < boardLength; i++) {
        for (int j = 0; j < boardLength; j++) {
          if (((i < (armThickness - 1)) || (i > (boardLength - armThickness)))
              && (((j < (armThickness - 1)) || (j > (boardLength - armThickness))))) {
            this.gameBoard[i][j] = Variables.Space;
          } else {
            this.gameBoard[i][j] = Variables.Marble;
          }
        }
      }
    }

    this.gameBoard[boardCenter][boardCenter] = Variables.Empty;
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} in terms of arm thickness of the given number and
   * empty slot located at the row and col given as {@code sRow} and {@code sCol}, respectively.
   *
   * @param armThickness the number of marbles at the end of rows/columns
   * @param sRow         the row that the empty slot is in
   * @param sCol         the column that the empty slot is in
   * @throws IllegalArgumentException if given arm thickness is less than three, then it would be an
   *                                  automatic win.
   * @throws IllegalArgumentException if given arm thickness is an even number
   * @throws IllegalArgumentException if the empty slot is assigned to a blank space (corners)
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {

    if (armThickness < 3) {
      throw new IllegalArgumentException("Arm thickness is too low.");
    } else if ((armThickness % 2) == 0) {
      throw new IllegalArgumentException("Arm thickness must be an odd number.");
    } else {
      this.armThickness = armThickness;
      int boardLength = this.calcBoardLength();

      this.gameBoard = new Variables[boardLength][boardLength];
      for (int i = 0; i < boardLength; i++) { //row
        for (int j = 0; j < boardLength; j++) { //column
          if (((i < (armThickness - 1)) || (i > (boardLength - armThickness)))
              && (((j < (armThickness - 1)) || (j > (boardLength - armThickness))))) {
            this.gameBoard[i][j] = Variables.Space;
          } else {
            this.gameBoard[i][j] = Variables.Marble;
          }
        }
      }
    }

    if (this.gameBoard[sRow][sCol] == Variables.Space) {
      throw new IllegalArgumentException("Invalid empty cell position ("
          + Integer.toString(sRow) + "," + Integer.toString(sRow) + ").");
    } else {
      this.gameBoard[sRow][sCol] = Variables.Empty;
    }
  }

  //this helper's access modifiers were changed to protected for other implementations for gain
  //easier access
  //this helper was simplified from earlier to make it more understandable
  // a helper method called when constructor is created to determine the number of
  // rows and columns of the board.
  protected int calcBoardLength() {
    return (3 * this.armThickness) - 2;
  }

  //this helper's access modifiers were changed to protected for other implementations to gain
  //easier access
  // a helper method called when constructor is created to determine the center point of the grid
  protected int calcBoardCenter() {
    return (int) Math.floor(this.calcBoardLength() / 2);
  }


  //the condition for comparing rows and columns have been combined from two nested -if's to just
  //one if for easier readability
  // exception in javadoc?
  // another helper for move?
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
    } else {
      throw new IllegalArgumentException("Invalid move. Must jump over exactly one marble.");
    }
  }

  //this helper's access modifiers were changed to protected for other implementations to gain
  //easier access
  // helper method that updates board accordingly after a move is made
  // - where marble was before is now empty
  // - where empty slot was before now a marble
  // - where the marble in between is now empty
  protected void updateBoard(int fromRow, int fromCol, int toRow, int toCol, int midRow,
      int midCol) {
    this.gameBoard[fromRow][fromCol] = Variables.Empty;
    this.gameBoard[toRow][toCol] = Variables.Marble;
    this.gameBoard[midRow][midCol] = Variables.Empty;
  }

  @Override
  public boolean isGameOver() {
    return !(this.canStillMakeMoves());
  }

  //this helper's access modifiers were changed to protected for other implementations to gain
  //easier access
  // helper method that determines whether or not a user is out of valid moves
  protected boolean canStillMakeMoves() {
    for (int i = 0; i < this.gameBoard.length; i++) {
      for (int j = 0; j < this.gameBoard.length; j++) {
        if ((this.gameBoard[i][j] == Variables.Marble)
            && this.isNextToMarble(i, j, this.gameBoard.length)) {
          return true;
        }
      }
    }
    return false;
  }

  // helper method that determines whether or not there is a marble next to the marble with
  // given coordinates
  protected boolean isNextToMarble(int i, int j, int boardLength) {
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
    return soFar;
  }


  //the joining of strings was modified. Earlier I included just one for the rows but
  //now, it is added for the columns too
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
        if (!((j > (this.gameBoard.length - this.armThickness))
            && ((i < this.armThickness - 1) || (i > this.gameBoard.length - this.armThickness)))) {
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

  //for this method, I changed the boardLength assignment because in this way, it can be applied
  // for the triangle, european and english styles of boards.
  @Override
  public int getScore() {
    int scoreSoFar = 0;
    int boardLength = this.gameBoard.length;

    for (int i = 0; i < boardLength; i++) {
      for (int j = 0; j < boardLength; j++) {
        if (this.gameBoard[i][j] == Variables.Marble) {
          scoreSoFar = scoreSoFar + 1;
        }
      }
    }

    return scoreSoFar;
  }

}

