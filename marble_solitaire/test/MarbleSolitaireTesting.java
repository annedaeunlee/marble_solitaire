import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the Marble Solitaire model. Verifying that game state is properly managed, and all
 * game actions are properly validated.
 */
public class MarbleSolitaireTesting {

  MarbleSolitaireModel euroDefaultSizeDefaultEmpty = new EuropeanSolitaireModelImpl();
  MarbleSolitaireModel euroDefaultSizeChosenEmpty = new EuropeanSolitaireModelImpl(2, 2);
  MarbleSolitaireModel euroChosenSizeDefaultEmpty = new EuropeanSolitaireModelImpl(5);
  MarbleSolitaireModel euroChosenSizeChosenEmpty =
      new EuropeanSolitaireModelImpl(5, 4, 4);

  MarbleSolitaireModel triDefaultSizeDefaultEmpty = new TriangleSolitaireModelImpl();
  MarbleSolitaireModel triDefaultSizeChosenEmpty = new TriangleSolitaireModelImpl(2, 2);
  MarbleSolitaireModel triChosenSizeDefaultEmpty = new TriangleSolitaireModelImpl(6);
  MarbleSolitaireModel triChosenSizeChosenEmpty =
      new TriangleSolitaireModelImpl(6, 4, 4);

  @Test
  public void testMove() {
    //tests for default grids and empty slot in the default location
    this.euroDefaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertFalse(this.euroDefaultSizeDefaultEmpty.isGameOver());
    this.triDefaultSizeDefaultEmpty.move(2, 2, 0, 0);
    assertFalse(this.triDefaultSizeDefaultEmpty.isGameOver());

    //tests for default grid and empty slot in customized location
    this.euroDefaultSizeChosenEmpty.move(2, 4, 2, 2);
    assertFalse(this.euroDefaultSizeChosenEmpty.isGameOver());
    this.triDefaultSizeChosenEmpty.move(2, 0, 2, 2);
    assertFalse(this.triDefaultSizeChosenEmpty.isGameOver());

    //tests for customized grid and empty slot in the default location
    this.euroChosenSizeDefaultEmpty.move(4, 6, 6, 6);
    assertFalse(this.euroChosenSizeDefaultEmpty.isGameOver());
    this.triChosenSizeDefaultEmpty.move(2, 2, 0, 0);
    assertFalse(this.triChosenSizeDefaultEmpty.isGameOver());

    //tests for customized grid and empty slot in customized location
    this.euroChosenSizeChosenEmpty.move(2, 4, 4, 4);
    assertFalse(this.euroChosenSizeChosenEmpty.isGameOver());
    this.triChosenSizeChosenEmpty.move(4, 2, 4, 4);
    assertFalse(this.triChosenSizeChosenEmpty.isGameOver());
  }


  @Test
  public void testIsGameOver() {
    this.euroDefaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertFalse(this.euroDefaultSizeDefaultEmpty.isGameOver());
    this.euroDefaultSizeDefaultEmpty.move(4, 3, 2, 3);
    assertFalse(this.euroDefaultSizeDefaultEmpty.isGameOver());
    this.euroDefaultSizeDefaultEmpty.move(3, 1, 3, 3);
    this.euroDefaultSizeDefaultEmpty.move(6, 3, 4, 3);
    this.euroDefaultSizeDefaultEmpty.move(3, 4, 3, 2);
    this.euroDefaultSizeDefaultEmpty.move(1, 4, 3, 4);
    this.euroDefaultSizeDefaultEmpty.move(2, 2, 2, 4);
    this.euroDefaultSizeDefaultEmpty.move(3, 5, 3, 3);
    this.euroDefaultSizeDefaultEmpty.move(3, 2, 3, 4);
    this.euroDefaultSizeDefaultEmpty.move(5, 2, 3, 2);
    this.euroDefaultSizeDefaultEmpty.move(2, 0, 2, 2);
    this.euroDefaultSizeDefaultEmpty.move(4, 4, 4, 2);
    this.euroDefaultSizeDefaultEmpty.move(2, 4, 4, 4);
    this.euroDefaultSizeDefaultEmpty.move(4, 5, 4, 3);
    this.euroDefaultSizeDefaultEmpty.move(4, 2, 4, 4);
    this.euroDefaultSizeDefaultEmpty.move(5, 4, 3, 4);
    this.euroDefaultSizeDefaultEmpty.move(2, 2, 4, 2);
    this.euroDefaultSizeDefaultEmpty.move(4, 1, 4, 3);
    this.euroDefaultSizeDefaultEmpty.move(4, 0, 2, 0);
    this.euroDefaultSizeDefaultEmpty.move(2, 6, 2, 4);
    this.euroDefaultSizeDefaultEmpty.move(2, 4, 4, 4);
    this.euroDefaultSizeDefaultEmpty.move(4, 4, 4, 2);
    this.euroDefaultSizeDefaultEmpty.move(0, 2, 2, 2);
    this.euroDefaultSizeDefaultEmpty.move(0, 4, 0, 2);
    assertFalse(this.euroDefaultSizeDefaultEmpty.isGameOver());
    this.euroDefaultSizeDefaultEmpty.move(4, 6, 2, 6);
    assertTrue(this.euroDefaultSizeDefaultEmpty.isGameOver());

    this.triDefaultSizeDefaultEmpty.move(2, 2, 0, 0);
    assertFalse(this.triDefaultSizeDefaultEmpty.isGameOver());
    this.triDefaultSizeDefaultEmpty.move(3, 1, 1, 1);
    this.triDefaultSizeDefaultEmpty.move(4, 3, 2, 1);
    this.triDefaultSizeDefaultEmpty.move(1, 0, 3, 2);
    this.triDefaultSizeDefaultEmpty.move(4, 1, 4, 3);
    this.triDefaultSizeDefaultEmpty.move(3, 0, 1, 0);
    this.triDefaultSizeDefaultEmpty.move(0, 0, 2, 2);
    this.triDefaultSizeDefaultEmpty.move(3, 3, 1, 1);
    this.triDefaultSizeDefaultEmpty.move(4, 4, 4, 2);
    this.triDefaultSizeDefaultEmpty.move(4, 2, 2, 2);
    assertFalse(this.triDefaultSizeDefaultEmpty.isGameOver());
    this.triDefaultSizeDefaultEmpty.move(1, 1, 3, 3);
    assertTrue(this.triDefaultSizeDefaultEmpty.isGameOver());
  }


  @Test
  public void testGetGameState() {
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O", this.euroChosenSizeDefaultEmpty.getGameState());

    assertEquals("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", this.euroDefaultSizeDefaultEmpty.getGameState());

    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", this.triDefaultSizeDefaultEmpty.getGameState());

    assertEquals(
        "     O\n"
            + "    O O\n"
            + "   O O O\n"
            + "  O O O O\n"
            + " O O O O _\n"
            + "O O O O O O", this.triChosenSizeChosenEmpty.getGameState());
  }


  @Test
  public void testGetScore() {
    this.euroDefaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertEquals(35, this.euroDefaultSizeDefaultEmpty.getScore());
    this.euroDefaultSizeDefaultEmpty.move(4, 3, 2, 3);
    assertEquals(34, this.euroDefaultSizeDefaultEmpty.getScore());
    this.euroDefaultSizeDefaultEmpty.move(3, 5, 3, 3);
    this.euroDefaultSizeDefaultEmpty.move(3, 2, 3, 4);
    this.euroDefaultSizeDefaultEmpty.move(3, 0, 3, 2);
    assertEquals(31, this.euroDefaultSizeDefaultEmpty.getScore());
  }

  @Test
  public void testInvalidConstructors() {
    // attempt to make constructor with empty slot in invalid position/space
    try {
      new EuropeanSolitaireModelImpl(0, 0);
      fail("Invalid customized empty position should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("invalid empty slot location", iae.getMessage());
    }

    try {
      new TriangleSolitaireModelImpl(0, 4);
      fail("Invalid customized empty position should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid empty cell position (0,4).", iae.getMessage());
    }

    // attempt to make constructor with arm thickness that is too low
    try {
      new EuropeanSolitaireModelImpl(1, 3, 3);
      fail("Invalid customized arm thickness should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Arm thickness is too low.", iae.getMessage());
    }

    try {
      new EuropeanSolitaireModelImpl(10, 3, 3);
      fail("Invalid customized arm thickness should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Arm thickness must be an odd number.", iae.getMessage());
    }

    // attempt to make constructor with arm thickness of an even number
    try {
      new TriangleSolitaireModelImpl(1, 3, 3);
      fail("Invalid customized arm thickness should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimension entered is too small.", iae.getMessage());
    }
  }

  @Test
  public void testInvalidMove() {
    //attempt to move marble into somewhere out of bounds
    try {
      this.euroDefaultSizeDefaultEmpty.move(1, 3, -1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Desired spot to move into is out of bounds.", iae.getMessage());
    }

    //attempt to move empty slot into a marble
    try {
      this.euroDefaultSizeDefaultEmpty.move(3, 3, 1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move marbles!", iae.getMessage());
    }

    //attempt to move an space into an empty slot
    try {
      this.euroDefaultSizeDefaultEmpty.move(0, 0, 3, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move marbles!", iae.getMessage());
    }

    //attempt to move marble into a marble
    try {
      this.euroDefaultSizeDefaultEmpty.move(0, 2, 2, 2);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //attempt to move marbles into a space
    try {
      this.euroDefaultSizeDefaultEmpty.move(2, 2, 0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //attempt to move marbles into same position
    try {
      this.euroDefaultSizeDefaultEmpty.move(2, 2, 2, 2);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //marble attempt to jump over empty into an empty slot
    this.euroDefaultSizeDefaultEmpty.move(1, 3, 3, 3);
    try {
      this.euroDefaultSizeDefaultEmpty.move(0, 3, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Must jump over a marble.", iae.getMessage());
    }

    //marble attempt to jump into next empty slot
    try {
      this.euroChosenSizeChosenEmpty.move(3, 4, 4, 4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }

    //marble attempt to jump into next empty slot
    try {
      this.euroDefaultSizeDefaultEmpty.move(0, 3, 1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }

    //marble attempting to jump vertically over two marbles into an empty slot
    try {
      this.euroDefaultSizeDefaultEmpty.move(5, 3, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }

    //marble attempting to jump horizontally over two marbles into an empty slot
    try {
      this.euroDefaultSizeDefaultEmpty.move(2, 6, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }

    //marble attempting to jump diagonally over two marbles into an empty slot
    try {
      this.euroDefaultSizeDefaultEmpty.move(4, 4, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }
  }

}
