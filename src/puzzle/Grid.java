package puzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a two dimensional layout of characters. Alternatively a grid can also be viewed as a
 * vertex in Dijkstra's algorithm, hence the {@link #getChildren} method.
 *
 * @author Sebastian Oberhoff
 */
final class Grid implements Comparable<Grid> {

  private final Set<String> unusedWords;

  private final char[][] charArray;

  private final int hashCode; // prevent re-computation for performance

  /**
   * Constructs a root grid with no previous character placements
   */
  Grid(List<String> unusedWords) {
    this.charArray = new char[][] { unusedWords.get(0).toCharArray() };
    this.unusedWords = unusedWords.stream().skip(1).collect(Collectors.toSet());
    this.hashCode = this.unusedWords.hashCode() + Arrays.deepHashCode(this.charArray);
  }

  /**
   * Constructor for child grids
   */
  Grid(Set<String> unusedWords, char[][] chars) {
    this.unusedWords = unusedWords;
    this.charArray = chars;
    this.hashCode = this.unusedWords.hashCode() + Arrays.deepHashCode(this.charArray);
  }

  /**
   * @return the set of legal grids that can be built by adding one of the remaining words to this grid
   */
  Set<Grid> getChildren() {
    return unusedWords.parallelStream() // only place where concurrency is introduced
        .flatMap(word -> {
          Set<String> remainingWords = new HashSet<>();
          remainingWords.addAll(this.unusedWords);
          remainingWords.remove(word);
          return CharArrays.nextCharArrays(charArray, word).stream()
              .map(nextCharArray -> new Grid(remainingWords, nextCharArray));
        })
        .collect(Collectors.toSet());
  }

  /**
   * @return true if all words have been used
   */
  boolean isComplete() {
    return unusedWords.isEmpty();
  }

  /**
   * @return the two dimensional area occupied by the grid
   */
  int getCompactness() {
    return this.charArray.length * this.charArray[0].length;
  }

  @Override
  public String toString() {
    return CharArrays.charArrayToString(this.charArray);
  }

  /**
   * @return size difference between the area covered by this grid and the other
   */
  @Override public int compareTo(Grid other) {
    return getCompactness() - other.getCompactness();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof Grid
        && this.unusedWords.equals(((Grid) other).unusedWords)
        && Arrays.deepEquals(this.charArray, ((Grid) other).charArray);
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }
}
