package main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sebastian Oberhoff
 */
public final class EvaluatorTest {

  @Test
  public void testFindSmallestGrid() {
    List<String> words = new ArrayList<>();
    words.add("HUNGER");
    words.add("NETT");
    Optional<Grid> smallestGrid = Evaluator.findSmallestGrid(words);
    System.err.println(smallestGrid.get());
  }

  @Test
  public void testFindSmallestGrid2() {
    List<String> words = new ArrayList<>();
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("AUTO");
    words.add("LADUNG");
    words.add("UNGARN");
    words.add("MILCH");
    words.add("ESSEN");
    words.add("VERSUCH");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");

    Optional<Grid> smallestGrid = Evaluator.findSmallestGrid(words);
    System.err.println(smallestGrid.get());
    System.err.println(smallestGrid.get().getCompactness());
  }

  @Test
  public void testFindSmallestGrid3() {
    List<String> words = new ArrayList<>();
    words.add("PRUEFUNG");
    words.add("AUFGABE");
    words.add("MATHEMATIK");
    words.add("INFORMATIK");
    words.add("PROGRAMMIERUNG");
    words.add("ZEIT");

    Optional<Grid> smallestGrid = Evaluator.findSmallestGrid(words);
    System.err.println(smallestGrid.get());
    System.err.println(smallestGrid.get().getCompactness());
  }

  @Test
  public void testFindSmallestgrid4() {
    List<String> words = new ArrayList<>();
    words.add("PROGRAMMIERUNG");
    words.add("ZEIT");
    words.add("KONZENTRATION");
    words.add("RECHNEN");
    words.add("ERGEBNIS");
    words.add("INTEGRAL");
    words.add("STOCHASTIK");
    words.add("STATISTIK");

    Optional<Grid> smallestGrid = Evaluator.findSmallestGrid(words);
    System.err.println(smallestGrid.get());
    System.err.println(smallestGrid.get().getCompactness());
  }
}
