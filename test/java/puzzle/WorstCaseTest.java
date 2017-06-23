package puzzle;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Sebastian Oberhoff
 */
public final class WorstCaseTest {

  /**
   * Only run this test if you want to watch the algorithm spin its wheels
   */
  @Test @Ignore
  public void testWorstCase() {
    List<String> words = new ArrayList<>();
    words.add("ABCDEFGHIJK");
    words.add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    words.add("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
    words.add("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
    words.add("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
    words.add("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    words.add("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
    words.add("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
    words.add("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    words.add("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
    words.add("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
    words.add("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");

    Optional<Grid> smallestGrid = Evaluator.findSmallestGrid(words);
    System.err.println(smallestGrid.get());
  }

  /**
   * The 3-SAT example discussed in the pdf.
   * This example is only solved quickly because of its small size.
   */
  @Test
  public void testThreeSat() {
    List<String> words = new ArrayList<>();
    words.add("UUIXYZUUJ");
    words.add("RIRRRRRRKR");
    words.add("VVKABCVVL");
    words.add("SJSS");
    words.add("QQLQ");

    words.add("GXFTTFAG");
    words.add("NYFFTTBN");
    words.add("OZFFTTCO");

    words.add("TFFPPPP");
    words.add("TFFMMMM");

    assertTrue(Evaluator.findSmallestGrid(words).isPresent());
  }

  /**
   * X can't satisfy the formula all by itself, so this puzzle should be unsolvable
   */
  @Test
  public void testUnsatisfiable() {
    List<String> words = new ArrayList<>();
    words.add("UUIXYZUUJ");
    words.add("RIRRRRRRKR");
    words.add("VVKABCVVL");
    words.add("SJSS");
    words.add("QQLQ");

    words.add("GXFTTFAG");

    words.add("TFFPPPP");
    words.add("TFFMMMM");

    assertFalse(Evaluator.findSmallestGrid(words).isPresent());
  }

}
