import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;

/**
 * Test cases for {@link TriangleSolitaireModelImpl} to assert that it functions and behaves
 * as expected.
 */
public class TestTriangleSolitaire {
  @Test
  public void testTriangleConstructors() {
    MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl();
    MarbleSolitaireModel t2 = new TriangleSolitaireModelImpl(7);
    MarbleSolitaireModel t3 = new TriangleSolitaireModelImpl(0, 0);
    MarbleSolitaireModel t4 = new TriangleSolitaireModelImpl(3, 3);
    MarbleSolitaireModel t5 = new TriangleSolitaireModelImpl(8, 2, 1);

    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", t1.getGameState());
    assertFalse(t1.isGameOver());

    assertEquals("      _\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O", t2.getGameState());
    assertFalse(t2.isGameOver());

    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", t3.getGameState());
    assertFalse(t3.isGameOver());

    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O _\n"
        + "O O O O O", t4.getGameState());
    assertFalse(t4.isGameOver());

    assertEquals("       O\n"
        + "      O O\n"
        + "     O _ O\n"
        + "    O O O O\n"
        + "   O O O O O\n"
        + "  O O O O O O\n"
        + " O O O O O O O\n"
        + "O O O O O O O O", t5.getGameState());
    assertFalse(t5.isGameOver());
  }


  @Test
  public void testInvalidConstructorParamsTriangle() {
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(6, 0, 3);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(-4);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(6, 0, 3);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(0);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    //Test for out of bounds negative.
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(-4, -5);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    //Test for out of bounds pos.
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(5, 5);
      fail("Invalid constructor parameters should have thrown exception.");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void moveTestFullGameTriangle() {
    MarbleSolitaireModel t1 = new TriangleSolitaireModelImpl(5, 0, 0);
    t1.move(2, 2, 0, 0);
    t1.move(4, 2, 2, 2);
    t1.move(3, 0, 3, 2);
    assertFalse(t1.isGameOver());
    t1.move(4, 4, 4, 2);
    t1.move(2, 2, 4, 4);
    t1.move(1, 0, 3, 0);
    t1.move(4, 1, 4, 3);
    t1.move(3, 2, 1, 0);
    t1.move(0, 0, 2, 0);
    t1.move(3, 0, 1, 0);
    assertFalse(t1.isGameOver());
    t1.move(4, 4, 4, 2);

    assertEquals("    _\n"
        + "   O _\n"
        + "  _ _ _\n"
        + " _ _ _ _\n"
        + "O _ O _ _", t1.getGameState());

    assertTrue(t1.isGameOver());
  }

  @Test
  public void testInvalidMoveTriangle() {
    TriangleSolitaireModelImpl t1 = new TriangleSolitaireModelImpl();
    t1.move(2, 2, 0, 0);
    assertEquals(Marble.INPLAY, t1.getMarkAt(0, 0));
    assertEquals(Marble.TAKEN, t1.getMarkAt(2, 2));
    assertEquals(Marble.TAKEN, t1.getMarkAt(1, 1));
    assertEquals(13, t1.getScore());
    //Test moving out of bounds from valid.
    try {
      t1.move(2, 1, 2, 3);
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
      t1.move(2, 2, 0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving to a marble.
    try {
      t1.move(1, 0, 3, 2);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    assertEquals(Marble.INPLAY, t1.getMarkAt(3, 3));
    assertEquals(Marble.TAKEN, t1.getMarkAt(2, 2));
    assertEquals(Marble.TAKEN, t1.getMarkAt(1, 1));
    //Test for moving over a blank space.
    try {
      t1.move(3, 3, 1, 1);
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
      t1.move(1, 0, 0, 0);
      t1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      t1.move(2, 1, 0, 0);
      t1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    assertEquals("    O\n"
        + "   O _\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", t1.getGameState());
    assertFalse(t1.isGameOver());
  }
}
