package main;

import java.util.List;

import static main.Helpers.charArrayToString;

/**
 * @author Sebastian Oberhoff
 */
public final class Puzzle {

  private final String comment;

  private final List<String> words;

  private final int length;

  private char[][] plainSolution;

  Puzzle(String comment, List<String> words) {
    this.comment = comment;
    this.words = words;
    this.length = words.stream().mapToInt(String::length).sum();
  }

  char[][] getPlainSolution() {
    return this.plainSolution;
  }

  void setPlainSolution(char[][] plainSolution) {
    this.plainSolution = plainSolution;
  }

  List<String> getWords() {
    return this.words;
  }

  int getLength() {
    return this.length;
  }

  @Override
  public String toString() {
    if (plainSolution != null) {
      return comment + "\n" +
          //          words.stream().collect(Collectors.joining(", ")) + "\n" +
          charArrayToString(plainSolution) + "\n" +
          " compactness: " + plainSolution.length * plainSolution[0].length;

    } else {
      return "no solution";
    }
  }

}
