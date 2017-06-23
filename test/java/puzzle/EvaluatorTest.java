package puzzle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The primary loop is already indirectly tested elsewhere.
 *
 * @author Sebastian Oberhoff
 */
public final class EvaluatorTest {

  @Test
  public void testHiddenSolution() {
    String plainSolution = "___N__\nHUNGER\n___T__\n___T__";
    String hiddenSolution = Evaluator.hiddenSolution(plainSolution);
    assertEquals(plainSolution.length(), hiddenSolution.length());
    assertTrue(hiddenSolution.chars().noneMatch(c -> c == '_'));
  }

  @Test
  public void testNoSolutionToString() {
    List<String> words = new ArrayList<>();
    words.add("A");
    words.add("B");
    Puzzle unsolvablePuzzle = new Puzzle("", words);
    Evaluator.solve(unsolvablePuzzle);
    assertTrue(unsolvablePuzzle.toString().contains("Keine Lösung"));
  }

  @Test
  public void testNoSolution() {
    List<String> words = new ArrayList<>();
    words.add("AXA");
    words.add("BXB");
    words.add("CXC");
    assertFalse(Evaluator.findSmallestGrid(words).isPresent());
  }

  @Test
  public void testOverlap() {
    List<String> words = new ArrayList<>();
    words.add("ABBA");
    words.add("BB");
    assertEquals(4, Evaluator.findSmallestGrid(words).get().getCompactness());
  }

}
