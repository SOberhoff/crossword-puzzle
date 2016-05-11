package main;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sebastian Oberhoff
 */
public final class CharPosition {

  private final char character;

  private final int x;

  private final int y;

  CharPosition(char character, int x, int y) {
    this.character = character;
    this.x = x;
    this.y = y;
  }

  public char getChar() {
    return character;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  boolean isCompatible(CharPosition other) {
    return this.x != other.x || this.y != other.y || this.character == other.character;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof CharPosition)) {
      return false;
    } else {
      CharPosition otherCharPosition = (CharPosition) other;
      return this.x == otherCharPosition.x &&
          this.y == otherCharPosition.y &&
          this.character == otherCharPosition.character;
    }
  }

  @Override
  public int hashCode() {
    return this.x + this.y + this.character;
  }

  static Set<CharPosition> createHorizontalPositions(String word, int offset, int x, int y) {
    Set<CharPosition> charPositions = new HashSet<>();
    for (int index = 0; index < word.length(); index++) {
      charPositions.add(new CharPosition(word.charAt(index), x + index - offset, y));
    }
    return charPositions;
  }

  static Set<CharPosition> createVerticalPositions(String word, int offset, int x, int y) {
    Set<CharPosition> charPositions = new HashSet<>();
    for (int index = 0; index < word.length(); index++) {
      charPositions.add(new CharPosition(word.charAt(index), x, y - index + offset));
    }
    return charPositions;
  }

  /**
   * @return true if every CharPosition in the first set is compatible with every CharPosition in
   * the second set
   */
  static boolean areCompatible(
      Set<CharPosition> charPositions, Set<CharPosition> otherCharPositions) {
    for (CharPosition charPosition : charPositions) {
      for (CharPosition otherCharPosition : otherCharPositions) {
        if (!charPosition.isCompatible(otherCharPosition)) {
          return false;
        }
      }
    }
    return true;
  }
}
