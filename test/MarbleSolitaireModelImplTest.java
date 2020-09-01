import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the {@link MarbleSolitaireModelImpl} to assert that it functions
 * and behaves properly and as expected.
 */
public class MarbleSolitaireModelImplTest {


  private MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModelImpl m2 = new MarbleSolitaireModelImpl(3);
  private MarbleSolitaireModelImpl m3 = new MarbleSolitaireModelImpl(2, 2);
  private MarbleSolitaireModelImpl m4 = new MarbleSolitaireModelImpl(3, 2, 5);
  private MarbleSolitaireModelImpl m5 = new MarbleSolitaireModelImpl(5);
  private MarbleSolitaireModelImpl m6 = new MarbleSolitaireModelImpl(5, 11, 5);

  @Test
  public void testConstructors() {
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m1.getGameState());

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m2.getGameState());

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O _ O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m3.getGameState());

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O _ O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m4.getGameState());

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
        + "        O O O O O", m5.getGameState());

    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O _ O O O\n"
        + "        O O O O O", m6.getGameState());
  }

  @Test
  public void moveTest() {
    m6.getGameState();
    m6.move(11, 7, 11, 5);
    m6.getGameState();
    m6.move(11, 4, 11, 6);
    m6.getGameState();
    m6.move(9, 4, 11, 4);
    m6.getGameState();
    assertFalse(m6.isGameOver());
    m6.move(7, 4, 9, 4);
    m6.getGameState();
    m6.move(5, 4, 7, 4);
    m6.getGameState();
    m6.move(5, 2, 5, 4);
    assertEquals("        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O _ _ O O O O O O O O O\n"
        + "O O O O _ O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O _ O O O O O O O O\n"
        + "        O O O O O\n"
        + "        _ O O O O\n"
        + "        O _ O _ O\n"
        + "        O O O O O", m6.getGameState());
  }

  @Test
  public void fullGame() {
    m1.move(5, 3, 3, 3);
    assertFalse(m1.isGameOver());
    m1.move(2, 3, 4, 3);
    assertFalse(m1.isGameOver());
    m1.move(3, 1, 3, 3);
    assertFalse(m1.isGameOver());
    m1.move(0, 3, 2, 3);
    assertFalse(m1.isGameOver());
    m1.move(3, 4, 3, 2);
    assertFalse(m1.isGameOver());
    m1.move(5, 4, 3, 4);
    assertFalse(m1.isGameOver());
    m1.move(2, 4, 4, 4);
    assertFalse(m1.isGameOver());
    m1.move(2, 6, 2, 4);
    assertFalse(m1.isGameOver());
    m1.move(4, 5, 2, 5);
    assertFalse(m1.isGameOver());
    m1.move(4, 6, 2, 6);
    assertFalse(m1.isGameOver());
    m1.move(4, 3, 4, 5);
    assertFalse(m1.isGameOver());
    m1.move(4, 1, 4, 3);
    assertFalse(m1.isGameOver());
    m1.move(6, 2, 4, 2);
    assertFalse(m1.isGameOver());
    m1.move(4, 2, 4, 4);
    assertFalse(m1.isGameOver());
    m1.move(4, 5, 4, 3);
    assertFalse(m1.isGameOver());
    m1.move(6, 4, 6, 2);
    assertFalse(m1.isGameOver());
    m1.move(1, 4, 3, 4);
    assertFalse(m1.isGameOver());
    m1.move(2, 6, 2, 4);
    assertFalse(m1.isGameOver());
    m1.move(2, 3, 2, 5);
    assertFalse(m1.isGameOver());
    m1.move(2, 1, 2, 3);
    assertFalse(m1.isGameOver());
    m1.move(0, 2, 2, 2);
    assertFalse(m1.isGameOver());
    m1.move(2, 2, 4, 2);
    assertFalse(m1.isGameOver());
    m1.move(4, 2, 4, 4);
    assertFalse(m1.isGameOver());
    m1.move(3, 4, 5, 4);
    assertEquals("    _ _ O\n"
        + "    _ _ _\n"
        + "O _ _ O _ O _\n"
        + "O _ _ _ _ _ _\n"
        + "O _ _ _ _ _ _\n"
        + "    _ _ O\n"
        + "    O _ _", m1.getGameState());
    assertEquals(8, m1.getScore());
    assertTrue(m1.isGameOver());
  }


  @Test
  public void testInvalidMove() {
    m1.move(5, 3, 3, 3);
    assertEquals(Marble.INPLAY, m1.getMarkAt(3, 3));
    assertEquals(Marble.TAKEN, m1.getMarkAt(4, 3));
    assertEquals(Marble.TAKEN, m1.getMarkAt(5, 3));
    assertEquals(31, m1.getScore());
    try {
      m1.move(5, 3, 1, 3);
      m1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      m1.move(-1, 0, 200, 5);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving from no marble.
    try {
      m1.move(4, 3, 5, 3);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving to a marble.
    try {
      m1.move(0, 2, 0, 4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    //Test for moving over a blank space.
    try {
      m1.move(3, 6, 3, 4);
      m1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      m1.move(-1, -4, -3, -4);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      m1.move(0, 0, 0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      m1.move(5, 7, 3, 6);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O",m1.getGameState());

    MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl();

    try {
      m1.move(1, 2, 3, 3);
      m1.getGameState();
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O",m1.getGameState());
  }



  @Test
  public void testInvalidConstructorParams() {
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(5, 0, 0);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(5, 3, 3);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(5, 9, 9);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl( -9, -9);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl( -10);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl( -7);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(-4, -2);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(-4, 2, 2);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      MarbleSolitaireModelImpl m1 = new MarbleSolitaireModelImpl(5, 0, 0);
      fail("Invalid move should have thrown exception");
    }
    catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }
}
