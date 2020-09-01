import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test cases for {@link MarbleSolitaireModelImpl} to assert that it functions
 * and behaves as expected.
 */
public class TestController {
  @Test
  public void testValidMoveController() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(new StringReader("6 4 4 "
        + "4 3 4 5 4 4 2 4 4 1 4 3 4 4 5 4 3 6 5 4 5 3 5 5 5 3 7 3 5 5 6 3 6 5 7 3 7 5 4 5 6 5 2 5 "
        + "4 7 3 5 3 5 3 5 5 5 6 5 4 7 5 7 3 2 5 4 5 3 7 3 5 3 4 3 6 3 2 3 4 1 3 3 3 3 3 5 3 5 3 5 "
        + "5 4 5 6 5 "), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 9, lines.length));
    assertEquals("Game over!\n    _ _ O\n"
        + "    _ _ _\n"
        + "O _ _ O _ O _\n"
        + "O _ _ _ _ _ _\n"
        + "O _ _ _ _ _ _\n"
        + "    _ _ O\n"
        + "    O _ _\n"
        + "Score: 8", lastMsg);

    assertEquals(8, m.getScore());
    assertTrue(m.isGameOver());
  }

  //Test for invalid move when position being placed is occupied by another marble.
  @Test
  public void testBogusInputs() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("-2 -3 fdsa fdsa q"), gameLog);

    c.playGame(m);
    assertFalse(m.isGameOver());
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", lastMsg);
  }

  //Test for invalid move when position being placed is occupied by another marble.
  @Test
  public void testInvalidMove1Controller() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("1 4 3 4 q"), gameLog);

    c.playGame(m);
    assertFalse(m.isGameOver());
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", lastMsg);
  }

  //Tests for invalid move when direction is diagonal.
  @Test
  public void testInvalidMoveController2() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("3 1 2 5 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", lastMsg);
  }

  //Test for invalid move when moving over a blank space and otherwise valid.
  @Test
  public void testInvalidMoveController3() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 7 4 5 4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test for quit in toCol space.
  @Test
  public void testQuit4() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 7 4 5 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test for invalid move when moving to out of bounds area.
  @Test
  public void testInvalidMoveController5() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 7 4 7 6 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test for quit in fromRow space.
  @Test
  public void testQuit1() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 q 4 5 4"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test for quit in fromCol space.
  @Test
  public void testQuit2() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 7 q 5 4"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }


  //Test for quit in toRow space.
  @Test
  public void testQuit3() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 7 4 q 4"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 10, lines.length));
    assertEquals("Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test for null readable.
  @Test
  public void testNullReadable() {
    StringBuilder gameLog = new StringBuilder();
    try {
      MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(null, gameLog);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  //Test for null appendable.
  @Test
  public void testNullAppendable() {
    try {
      MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(new StringReader(
          "5 4 6 7 3"), null);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  //Test for Readable running out of inputs.
  @Test
  public void testRunOutOfArguments() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5, 4, 3);
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(new StringReader(
        "5 7 7 7"), gameLog);
    try {
      c.playGame(m);
      fail("Invalid move should have thrown exception");
    } catch (IllegalStateException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  //Test for Appendable unable to output.
  /*@Test
  public void testAppendableError() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5, 4, 3);
    StringBuilder gameLog = "String";
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(new StringReader(
        "5 7 7 7"), gameLog);
    try {
      c.playGame(m);
      fail("Invalid move should have thrown exception");
    } catch (IllegalStateException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }*/

  //Test for quit with armThickness 5 and changed blank space.
  @Test
  public void testQuit5() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5, 4, 3);
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("5 6 5 4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 15, lines.length));
    assertEquals("State of game when quit:\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O _ _ O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103", lastMsg);
  }

  //Test for quit with armThickness 5.
  @Test
  public void testQuit6() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("5 7 7 7 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 15, lines.length));
    assertEquals("State of game when quit:\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103", lastMsg);
  }


  //Test for with armThickness 5.
  @Test
  public void testInvalidNotDefault() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(5);
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("-1 -4 -50 5 7 7 7 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 15, lines.length));
    assertEquals("State of game when quit:\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "        O O O O O\n"
        + "Score: 103", lastMsg);
  }

  //Test with negative integer in fromRow space.
  @Test
  public void testInvalidMoveController4() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("0\n 6 4 4 4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 29, lines.length));
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "0 is an invalid entry.\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test with negative integer in fromCol space.
  @Test
  public void testInvalidInput1() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 -60\n 4 4 4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 29, lines.length));
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "-60 is an invalid entry.\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test with negative integer in toRow space.
  @Test
  public void testInvalidInput2() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 -450\n 4 4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 29, lines.length));
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "-450 is an invalid entry.\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test with negative integer in toCol space.
  @Test
  public void testInvalidInput4() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 -4\n4 q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 29, lines.length));
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "-4 is an invalid entry.\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  //Test with negative integer after valid move.
  @Test
  public void testInvalidInput5() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("6 4 4 4 -1\n q"), gameLog);

    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 29, lines.length));
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Enter the current row and column of the marble you want to move and the row and column"
        + " you want to move it to.\n"
        + "-1 is an invalid entry.\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31", lastMsg);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPassed() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(
        new StringReader("-1 6 4 4 4"), gameLog);

    c.playGame(null);

  }
}
