package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Sebastian Oberhoff
 */
final class Grid implements Comparable<Grid> {

  private final Set<String> unusedWords;

  private final Set<CharPosition> charPositions;

  private final int top;

  private final int bottom;

  private final int right;

  private final int left;

  /**
   * Constructor for the root grid, for which no previous grid exists
   */
  Grid(Set<String> unusedWords, String firstWord) {
    this.unusedWords = unusedWords;
    this.charPositions = CharPosition.createHorizontalPositions(firstWord, 0, 0, 0);
    this.top = 0;
    this.bottom = 0;
    this.right = firstWord.length() - 1;
    this.left = 0;
  }

  /**
   * Constructor for a child grid. All the arguments passed in are assumed to be consistent.
   */
  Grid(Set<String> unusedWords, Set<CharPosition> charPositions,
      int top, int bottom, int right, int left) {
    this.unusedWords = unusedWords;
    this.charPositions = charPositions;
    this.top = top;
    this.bottom = bottom;
    this.right = right;
    this.left = left;
  }

  /**
   * @return the set of legal grids that can be built by adding one of the remaining words to this grid
   */
  Set<Grid> getChildren() {
    Set<Grid> children = new HashSet<>();
    for (String word : unusedWords) {
      Set<String> remainingWords = new HashSet<>();
      remainingWords.addAll(unusedWords);
      remainingWords.remove(word);

      for (Set<CharPosition> legalCharPositions : getLegalCharPositions(word)) {
        int newTop = IntStream.concat(IntStream.of(top),
            legalCharPositions.stream().mapToInt(CharPosition::getY)).max().getAsInt();
        int newBottom = IntStream.concat(IntStream.of(bottom),
            legalCharPositions.stream().mapToInt(CharPosition::getY)).min().getAsInt();
        int newRight = IntStream.concat(IntStream.of(right),
            legalCharPositions.stream().mapToInt(CharPosition::getX)).max().getAsInt();
        int newLeft = IntStream.concat(IntStream.of(left),
            legalCharPositions.stream().mapToInt(CharPosition::getX)).min().getAsInt();

        legalCharPositions.addAll(this.charPositions);
        children.add(new Grid(remainingWords, legalCharPositions,
            newTop, newBottom, newRight, newLeft));
      }

    }
    return children;
  }

  Set<Set<CharPosition>> getLegalCharPositions(String word) {
    Set<Set<CharPosition>> legalCharPositions = new HashSet<>();
    for (int index = 0; index < word.length(); index++) {
      for (CharPosition charPosition : this.charPositions) {
        if (charPosition.getChar() == word.charAt(index)) {

          Set<CharPosition> horizontalPositions = CharPosition.createHorizontalPositions(
              word, index, charPosition.getX(), charPosition.getY());
          if (CharPosition.areCompatible(this.charPositions, horizontalPositions)) {
            legalCharPositions.add(horizontalPositions);
          }

          Set<CharPosition> verticalPositions = CharPosition.createVerticalPositions(
              word, index, charPosition.getX(), charPosition.getY());
          if (CharPosition.areCompatible(this.charPositions, verticalPositions)) {
            legalCharPositions.add(verticalPositions);
          }
        }
      }
    }
    return legalCharPositions;
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
    return (top - bottom + 1) * (right - left + 1);
  }

  @Override
  public String toString() {
    char[][] charArray = new char[top - bottom + 1][right - left + 1];

    // fill in 2D Array
    for (CharPosition charPosition : this.charPositions) {
      charArray[top - charPosition.getY()][charPosition.getX() - left] = charPosition.getChar();
    }

    // replace null chars with spaces
    for (int row = 0; row < charArray.length; row++) {
      for (int column = 0; column < charArray[row].length; column++) {
        if (charArray[row][column] == 0) {
          charArray[row][column] = ' ';
        }
      }
    }

    return Arrays.stream(charArray).map(String::new).collect(Collectors.joining("\n"));
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
        && this.charPositions.equals(((Grid) other).charPositions);
  }

  @Override
  public int hashCode() {
    return this.unusedWords.stream().mapToInt(String::hashCode).sum() +
        this.charPositions.stream().mapToInt(CharPosition::hashCode).sum();
  }
}
