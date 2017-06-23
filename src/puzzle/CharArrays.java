package puzzle;

import java.util.*;

/**
 * All the low level array code is in here.
 *
 * @author Sebastian Oberhoff
 */
final class CharArrays {

  private CharArrays() {
    //non-instantiable
  }

  /**
   * @return all possible char arrays that can be derived by somehow attaching the given word
   *
   * The input array is not modified.
   */
  static Set<char[][]> nextCharArrays(char[][] charArray, String word) {
    Set<char[][]> nextArrays = new HashSet<>();

    for (int row = 0; row < charArray.length; row++) {
      for (int column = 0; column < charArray[0].length; column++) {
        if (charArray[row][column] != 0) {
          for (int offset : horizontalOffsets(charArray, row, column, word)) {
            nextArrays.add(insertHorizontally(charArray, row, column, word, offset));
          }
          for (int offset : verticalOffsets(charArray, row, column, word)) {
            nextArrays.add(insertVertically(charArray, row, column, word, offset));
          }
        }
      }
    }
    return nextArrays;
  }

  /**
   * @return all offsets into the given word that lead to a legal horizontal placement at the
   * indicated position
   */
  static List<Integer> horizontalOffsets(char[][] charArray, int row, int column, String word) {
    List<Integer> offsets = new ArrayList<>(word.length());
    for (int offset = 0; offset < word.length(); offset++) {
      boolean isLegal = true;
      for (int index = 0; index < word.length(); index++) {
        int shiftedIndex = index + column - offset;
        boolean withinBounds = 0 <= shiftedIndex && shiftedIndex < charArray[row].length;
        if (withinBounds &&
            charArray[row][shiftedIndex] != 0
            && charArray[row][shiftedIndex] != word.charAt(index)) {
          isLegal = false;
          break;
        }
      }
      if (isLegal) {
        offsets.add(offset);
      }
    }
    return offsets;
  }

  /**
   * @return all offsets into the given word that lead to a legal vertical placement at the
   * indicated position
   */
  static List<Integer> verticalOffsets(char[][] charArray, int row, int column, String word) {
    List<Integer> offsets = new ArrayList<>(word.length());
    for (int offset = 0; offset < word.length(); offset++) {
      boolean isLegal = true;
      for (int index = 0; index < word.length(); index++) {
        int shiftedIndex = index + row - offset;
        boolean withinBounds = 0 <= shiftedIndex && shiftedIndex < charArray.length;
        if (withinBounds &&
            charArray[shiftedIndex][column] != 0
            && charArray[shiftedIndex][column] != word.charAt(index)) {
          isLegal = false;
          break;
        }
      }
      if (isLegal) {
        offsets.add(offset);
      }
    }
    return offsets;
  }

  /**
   * @return a new char array where the given word has been placed horizontally at the indicated
   * position and with the given offset
   */
  static char[][] insertHorizontally(
      char[][] charArray, int row, int column, String word, int offset) {

    int leftInc = Math.abs(Integer.min(0, column - offset));
    int rightInc = Integer.max(0, column + word.length() - offset - charArray[0].length);
    char[][] newCharArray = new char[charArray.length][charArray[0].length + leftInc + rightInc];

    for (int rowIndex = 0; rowIndex < charArray.length; rowIndex++) {
      System.arraycopy(
          charArray[rowIndex], 0, newCharArray[rowIndex], leftInc, charArray[0].length);
    }
    for (int charIndex = 0; charIndex < word.length(); charIndex++) {
      newCharArray[row][column + leftInc + charIndex - offset] = word.charAt(charIndex);
    }
    return newCharArray;
  }

  /**
   * @return a new char array where the given word has been placed vertically at the indicated
   * position and with the given offset
   */
  static char[][] insertVertically(
      char[][] charArray, int row, int column, String word, int offset) {

    int topInc = Math.abs(Integer.min(0, row - offset));
    int bottomInc = Integer.max(0, row + word.length() - offset - charArray.length);
    char[][] newCharArray = new char[charArray.length + topInc + bottomInc][charArray[0].length];

    for (int rowIndex = 0; rowIndex < charArray.length; rowIndex++) {
      System.arraycopy(
          charArray[rowIndex], 0, newCharArray[rowIndex + topInc], 0, charArray[0].length);
    }
    for (int charIndex = 0; charIndex < word.length(); charIndex++) {
      newCharArray[row + topInc + charIndex - offset][column] = word.charAt(charIndex);
    }
    return newCharArray;
  }

  /**
   * @return a string representation of the given char array -
   * rows corresponding to lines and null characters corresponding to '_'
   */
  static String charArrayToString(char[][] charArray) {
    StringBuilder builder = new StringBuilder();
    for (int row = 0; row < charArray.length; builder.append('\n'), row++) {
      for (int column = 0; column < charArray[row].length; column++) {
        if (charArray[row][column] == 0) {
          builder.append('_');
        } else {
          builder.append(charArray[row][column]);
        }
      }
    }
    return builder.toString().substring(0, builder.length() - 1);
  }

  /**
   * the inverse of {@link #charArrayToString}
   *
   * only used by tests
   */
  static char[][] stringToCharArray(String string) {
    char[][] charArray = Arrays.stream(string.split("\n"))
        .map(String::toCharArray)
        .toArray(char[][]::new);
    for (int row = 0; row < charArray.length; row++) {
      assert (charArray[0].length == charArray[row].length);
      for (int column = 0; column < charArray[row].length; column++) {
        if (charArray[row][column] == '_') {
          charArray[row][column] = 0;
        }
      }
    }
    return charArray;
  }

}
