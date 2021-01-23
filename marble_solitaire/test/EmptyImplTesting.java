import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the Marble Solitaire model. Verifying that game state is properly managed, and all
 * game actions are properly validated.
 */
public class EmptyImplTesting {

  MarbleSolitaireModel defaultSizeDefaultEmpty = new MarbleSolitaireModelImpl();
  MarbleSolitaireModel defaultSizeChosenEmpty = new MarbleSolitaireModelImpl(2, 2);
  MarbleSolitaireModel chosenSizeDefaultEmpty = new MarbleSolitaireModelImpl(5);
  MarbleSolitaireModel chosenSizeChosenEmpty =
      new MarbleSolitaireModelImpl(5, 4, 4);

  @Test
  public void testMove() {
    //tests for 3x3 grid and empty slot in the center
    this.defaultSizeDefaultEmpty.move(1, 3, 3, 3);
    this.defaultSizeDefaultEmpty.move(2,1,2,3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());

    //tests for 3x3 grid and empty slot in (2,2)
    this.defaultSizeChosenEmpty.move(2, 4, 2, 2);
    this.defaultSizeChosenEmpty.move(0, 3, 2, 3);
    assertFalse(this.defaultSizeChosenEmpty.isGameOver());

    //tests for 5x5 grid and empty slot in the center
    this.chosenSizeDefaultEmpty.move(4, 6, 6, 6);
    this.chosenSizeDefaultEmpty.move(7, 6, 5, 6);
    assertFalse(this.chosenSizeDefaultEmpty.isGameOver());

    //tests for 5x5 grid and empty slot in (4,4)
    this.chosenSizeChosenEmpty.move(2, 4, 4, 4);
    this.chosenSizeChosenEmpty.move(5, 4, 3, 4);
    assertFalse(this.chosenSizeChosenEmpty.isGameOver());
  }

  @Test
  public void testIsGameOver() {
    this.defaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 3, 2, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(3, 1, 3, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(6, 3, 4, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(3, 4, 3, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(1, 4, 3, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 2, 2, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(3, 5, 3, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(3, 2, 3, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(5, 2, 3, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 0, 2, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 4, 4, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 4, 4, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 5, 4, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 2, 4, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(5, 4, 3, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 2, 4, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 1, 4, 3);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 0, 2, 0);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 6, 2, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(2, 4, 4, 4);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 4, 4, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(0, 2, 2, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(0, 4, 0, 2);
    assertFalse(this.defaultSizeDefaultEmpty.isGameOver());
    this.defaultSizeDefaultEmpty.move(4, 6, 2, 6);
    assertTrue(this.defaultSizeDefaultEmpty.isGameOver());
  }

  @Test
  public void testGetGameState() {
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", this.defaultSizeDefaultEmpty.getGameState());

    this.defaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertEquals("    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", this.defaultSizeDefaultEmpty.getGameState());

    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O", this.chosenSizeDefaultEmpty.getGameState());


  }

  @Test
  public void testGetScore() {
    this.defaultSizeDefaultEmpty.move(1, 3, 3, 3);
    assertEquals(31, this.defaultSizeDefaultEmpty.getScore());
    this.defaultSizeDefaultEmpty.move(4, 3, 2, 3);
    assertEquals(30, this.defaultSizeDefaultEmpty.getScore());
    this.defaultSizeDefaultEmpty.move(3, 5, 3, 3);
    this.defaultSizeDefaultEmpty.move(3, 2, 3, 4);
    this.defaultSizeDefaultEmpty.move(3, 0, 3, 2);
    assertEquals(27, this.defaultSizeDefaultEmpty.getScore());
  }

  @Test
  public void testInvalidConstructors() {
    // attempt to make constructor with empty slot in invalid position/space
    try {
      new MarbleSolitaireModelImpl(0, 0);
      fail("Invalid customized empty position should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid empty cell position (0,0).", iae.getMessage());
    }

    // attempt to make constructor with arm thickness that is too low
    try {
      new MarbleSolitaireModelImpl(1, 3, 3);
      fail("Invalid customized arm thickness should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Arm thickness is too low.", iae.getMessage());
    }

    // attempt to make constructor with arm thickness of an even number
    try {
      new MarbleSolitaireModelImpl(10, 3, 3);
      fail("Invalid customized arm thickness should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Arm thickness must be an odd number.", iae.getMessage());
    }
  }

  @Test
  public void testInvalidMove() {
    //attempt to move marble into somewhere out of bounds
    try {
      this.defaultSizeDefaultEmpty.move(1, 3, -1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Desired spot to move into is out of bounds.", iae.getMessage());
    }

    //attempt to move empty slot into a marble
    try {
      this.defaultSizeDefaultEmpty.move(3, 3, 1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move marbles!", iae.getMessage());
    }

    //attempt to move an space into an empty slot
    try {
      this.defaultSizeDefaultEmpty.move(0, 0, 3, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move marbles!", iae.getMessage());
    }

    //attempt to move marble into a marble
    try {
      this.defaultSizeDefaultEmpty.move(0, 2, 2, 2);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //attempt to move marbles into a space
    try {
      this.defaultSizeDefaultEmpty.move(2, 2, 0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //attempt to move marbles into same position
    try {
      this.defaultSizeDefaultEmpty.move(2, 2, 2, 2);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Can only move into empty slots!", iae.getMessage());
    }

    //marble attempt to jump over empty into an empty slot
    this.defaultSizeDefaultEmpty.move(1, 3, 3, 3);
    try {
      this.defaultSizeDefaultEmpty.move(0, 3, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Must jump over a marble.", iae.getMessage());
    }

    //marble attempt to jump into next empty slot
    try {
      this.chosenSizeChosenEmpty.move(3, 4, 4, 4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Not two positions apart", iae.getMessage());
    }

    //marble attempt to jump into next empty slot
    try {
      this.defaultSizeDefaultEmpty.move(0, 3, 1, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Not two positions apart", iae.getMessage());
    }

    //marble attempting to jump vertically over two marbles into an empty slot
    try {
      this.defaultSizeDefaultEmpty.move(5, 3, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Not two positions apart", iae.getMessage());
    }

    //marble attempting to jump horizontally over two marbles into an empty slot
    try {
      this.defaultSizeDefaultEmpty.move(2, 6, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Not two positions apart", iae.getMessage());
    }

    //marble attempting to jump diagonally over two marbles into an empty slot
    try {
      this.defaultSizeDefaultEmpty.move(4, 4, 2, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid move. Must jump over exactly one marble.", iae.getMessage());
    }
  }

}
