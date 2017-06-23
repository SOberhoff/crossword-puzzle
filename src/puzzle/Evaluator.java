package puzzle;

import java.util.*;

/**
 * This class encapsulates the high level algorithm that solves the puzzle
 *
 * @author Sebastian Oberhoff
 */
public final class Evaluator {

  private Evaluator() {
    //non-instantiable
  }

  /**
   * Sets the plain and hidden solutions, if they exist.
   */
  static void solve(Puzzle puzzle) {
    findSmallestGrid(puzzle.getWords()).ifPresent(grid -> puzzle.setSolution(
        grid.toString(), hiddenSolution(grid.toString()), grid.getCompactness()));
  }

  /**
   * @return the optimally compact grid, if it exists
   */
  // This function executes a variation of Dijkstra's algorithm.
  // An arbitrary word is chosen for the root of the search tree. Then the algorithm loops until
  // either a complete grid has been found or all possibilities have been exhausted.
  // The search direction of the algorithm is dictated by a queue that remains sorted by grid size.
  // Duplicates that arise from adding the same words in a permuted order are trimmed by maintaining
  // a set of previously visited grids.
  public static Optional<Grid> findSmallestGrid(List<String> words) {

    Queue<Grid> queue = new PriorityQueue<>();
    Set<Grid> visitedGrids = new HashSet<>();

    Grid grid = new Grid(words);

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

  /**
   * @return the same string with every occurrence of '_' replaced by a random capital letter
   */
  static String hiddenSolution(String plainSolution) {
    Random random = new Random(0);
    return plainSolution.chars().map(c -> c == '_' ? random.nextInt(26) + 65 : c)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
