package puzzle;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * These tests aren't extremely clever and function mostly as regression tests.
 *
 * @author Sebastian Oberhoff
 */
public final class CharArraysTest {

  @Test
  public void testHorizontalNextCharArrays() {
    char[][] charArray = CharArrays.stringToCharArray("A\nB\nC");
    Set<char[][]> nextCharArrays = CharArrays.nextCharArrays(charArray, "BBB");
    assertEquals(3, nextCharArrays.size());
  }

  @Test
  public void testVerticalNextCharArrays() {
    char[][] charArray = CharArrays.stringToCharArray("ABC");
    Set<char[][]> nextCharArrays = CharArrays.nextCharArrays(charArray, "BBB");
    assertEquals(3, nextCharArrays.size());
  }

  @Test
  public void testNoNextCharArrays() {
    char[][] charArray = CharArrays.stringToCharArray("ABC");
    Set<char[][]> nextCharArrays = CharArrays.nextCharArrays(charArray, "DDD");
    assertEquals(0, nextCharArrays.size());
  }

  @Test
  public void testHorizontalOffsets() {
    char[][] charArray = CharArrays.stringToCharArray("A\nB\nC");
    List<Integer> horizontalOffsets = CharArrays.horizontalOffsets(charArray, 1, 0, "BBB");
    assertTrue(horizontalOffsets.contains(0));
    assertTrue(horizontalOffsets.contains(1));
    assertTrue(horizontalOffsets.contains(2));
  }

  @Test
  public void testVerticalOffsets() {
    char[][] charArray = CharArrays.stringToCharArray("ABC");
    List<Integer> verticalOffsets = CharArrays.verticalOffsets(charArray, 0, 1, "BBB");
    assertTrue(verticalOffsets.contains(0));
    assertTrue(verticalOffsets.contains(1));
    assertTrue(verticalOffsets.contains(2));
  }

  @Test
  public void testInsertHorizontally() {
    char[][] charArray = CharArrays.stringToCharArray("A\nB\nC");
    char[][] horizontalInsertion = CharArrays.insertHorizontally(charArray, 1, 0, "BBB", 0);
    assertEquals("A__\nBBB\nC__", CharArrays.charArrayToString(horizontalInsertion));
  }

  @Test
  public void testInsertVertically() {
    char[][] charArray = CharArrays.stringToCharArray("ABC");
    char[][] verticalInsertion = CharArrays.insertVertically(charArray, 0, 1, "BBB", 0);
    assertEquals("ABC\n_B_\n_B_", CharArrays.charArrayToString(verticalInsertion));
  }

  @Test
  public void testStringToCharArrayToString() {
    String expected = "A__\nBBB\nC__";
    assertEquals(expected, CharArrays.charArrayToString(CharArrays.stringToCharArray(expected)));
  }
}
