package main;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sebastian Oberhoff
 */
public final class Evaluator {

  public static Puzzle solve(Puzzle puzzle) {
    return null;
  }

  static Optional<Grid> findSmallestGrid(List<String> words) {

    Queue<Grid> queue = new PriorityQueue<>();
    Set<Grid> visitedGrids = new HashSet<>();

    Grid grid = new Grid(words.stream().skip(1).collect(Collectors.toSet()), words.get(0));

    while (!grid.isComplete()) {
      if (!visitedGrids.contains(grid)) {
        visitedGrids.add(grid);
        grid.getChildren().forEach(queue::add);
      }
      if (queue.isEmpty()) {
        return Optional.empty();
      } else {
        grid = queue.poll();
      }
    }
    return Optional.of(grid);
  }

}
