import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import org.junit.Test;

/**
 * Test cases for {@link EuropeanSolitaireModelImpl} to assert that it functions and behaves as
 * expected.
 */
public class TestEuropeanSolitaire {
  @Test
  public void testEuroConstructors() {
    MarbleSolitaireModel m1 = new EuropeanSolitaireModelImpl();
    MarbleSolitaireModel m2 = new EuropeanSolitaireModelImpl(5);
    MarbleSolitaireModel m3 = new EuropeanSolitaireModelImpl(3, 3);
    MarbleSolitaireModel m4 = new EuropeanSolitaireModelImpl(1, 1);
    MarbleSolitaireModel m5 = new EuropeanSolitaireModelImpl(5, 3, 3);

    assertEquals(
        "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", m1.getGameState());

    assertEquals(
        "        O O O O O\n"
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
            + "        O O O O O", m2.getGameState());

    assertEquals(
        "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", m3.getGameState());

    assertEquals(
        "    O O O\n"
            + "  _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", m4.getGameState());

    assertEquals(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O _ O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", m5.getGameState());


  }

  @Test
  public void testInvalidConstructorParamsEuro() {
    try {
      EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(5, 0, 0);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(6);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(8, 3, 3);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(6);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(6, 6);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void moveTestEuro() {
    EuropeanSolitaireModelImpl e1 = new EuropeanSolitaireModelImpl(5, 11, 5);
    e1.move(11, 7, 11, 5);
    e1.getGameState();
    e1.move(11, 4, 11, 6);
    e1.getGameState();
    e1.move(9, 4, 11, 4);
    e1.getGameState();
    assertFalse(e1.isGameOver());
    e1.move(7, 4, 9, 4);
    e1.getGameState();
    e1.move(5, 4, 7, 4);
    e1.getGameState();
    e1.move(5, 2, 5, 4);
    assertEquals("        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O _ _ O O O O O O O O O\n"
        + "O O O O _ O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O _ O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O _ O O O O O O\n"
        + "      O O _ O _ O O\n"
        + "        O O O O O", e1.getGameState());
  }

  @Test
  public void testInvalidMoveEuro() {
    EuropeanSolitaireModelImpl t1 = new EuropeanSolitaireModelImpl();
    t1.move(5, 3, 3, 3);
    assertEquals(Marble.INPLAY, t1.getMarkAt(3, 3));
    assertEquals(Marble.TAKEN, t1.getMarkAt(4, 3));
    assertEquals(Marble.TAKEN, t1.getMarkAt(5, 3));
    assertEquals(35, t1.getScore());
    try {
      t1.move(5, 3, 1, 3);
      t1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      t1.move(-1, 0, 200, 5);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving from no marble.
    try {
      t1.move(4, 3, 5, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving to a marble.
    try {
      t1.move(0, 2, 0, 4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving over a blank space.
    try {
      t1.move(3, 6, 3, 4);
      t1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test negative.
    try {
      t1.move(-1, -4, -3, -4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test no move.
    try {
      t1.move(0, 0, 0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test out of bounds.
    try {
      t1.move(5, 7, 3, 6);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      t1.move(1, 2, 3, 3);
      t1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    assertEquals(
        "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O", t1.getGameState());

  }

  @Test
  public void fullGameEuro() {
    MarbleSolitaireModel e1 = new EuropeanSolitaireModelImpl(3);
    e1.move(5, 3, 3, 3);
    assertFalse(e1.isGameOver());
    e1.move(2, 3, 4, 3);
    assertFalse(e1.isGameOver());
    e1.move(3, 1, 3, 3);
    assertFalse(e1.isGameOver());
    e1.move(0, 3, 2, 3);
    assertFalse(e1.isGameOver());
    e1.move(3, 4, 3, 2);
    assertFalse(e1.isGameOver());
    e1.move(5, 4, 3, 4);
    assertFalse(e1.isGameOver());
    e1.move(2, 4, 4, 4);
    assertFalse(e1.isGameOver());
    e1.move(2, 6, 2, 4);
    assertFalse(e1.isGameOver());
    e1.move(4, 5, 2, 5);
    assertFalse(e1.isGameOver());
    e1.move(4, 6, 2, 6);
    assertFalse(e1.isGameOver());
    e1.move(4, 3, 4, 5);
    assertFalse(e1.isGameOver());
    e1.move(4, 1, 4, 3);
    assertFalse(e1.isGameOver());
    e1.move(6, 2, 4, 2);
    assertFalse(e1.isGameOver());
    e1.move(4, 2, 4, 4);
    assertFalse(e1.isGameOver());
    e1.move(4, 5, 4, 3);
    assertFalse(e1.isGameOver());
    e1.move(6, 4, 6, 2);
    assertFalse(e1.isGameOver());
    e1.move(1, 4, 3, 4);
    assertFalse(e1.isGameOver());
    e1.move(2, 6, 2, 4);
    assertFalse(e1.isGameOver());
    e1.move(2, 3, 2, 5);
    assertFalse(e1.isGameOver());
    e1.move(2, 1, 2, 3);
    assertFalse(e1.isGameOver());
    e1.move(0, 2, 2, 2);
    assertFalse(e1.isGameOver());
    e1.move(2, 2, 4, 2);
    assertFalse(e1.isGameOver());
    e1.move(4, 2, 4, 4);
    assertFalse(e1.isGameOver());
    e1.move(3, 4, 5, 4);
    assertFalse(e1.isGameOver());
    e1.move(1, 5, 3, 5);
    assertFalse(e1.isGameOver());
    e1.move(5, 5, 5, 3);

    assertEquals("    _ _ O\n"
        + "  O _ _ _ _\n"
        + "O _ _ O _ _ _\n"
        + "O _ _ _ _ O _\n"
        + "O _ _ _ _ _ _\n"
        + "  O _ O _ _\n"
        + "    O _ _", e1.getGameState());
    assertEquals(10, e1.getScore());
    assertTrue(e1.isGameOver());
  }
}
