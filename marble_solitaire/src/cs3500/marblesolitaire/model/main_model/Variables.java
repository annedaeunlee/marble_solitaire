package cs3500.marblesolitaire.model.hw02;

/**
 * Represents the state of a position on the board in a game of MarbleSolitaire.
 */
public enum Variables {
  Marble("O"), Empty("_"), Space(" ");

  private final String disp;

  Variables(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}
