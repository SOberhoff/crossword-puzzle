package puzzle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Container for input and output data
 *
 * @author Sebastian Oberhoff
 */
final class Puzzle {

  private final String comment;

  private final List<String> words;

  private Optional<String> plainSolution = Optional.empty();

  private Optional<String> hiddenSolution = Optional.empty();

  private int compactness;

  Puzzle(String comment, List<String> words) {
    this.comment = comment;
    this.words = words;
  }

  List<String> getWords() {
    return this.words;
  }

  void setSolution(String plainSolution, String hiddenSolution, int compactness) {
    this.plainSolution = Optional.of(plainSolution);
    this.hiddenSolution = Optional.of(hiddenSolution);
    this.compactness = compactness;
  }

  @Override
  public String toString() {
    String string = this.comment + "\n" +
        "Eingelesene Wörter:\n" + this.words.stream().collect(Collectors.joining(", "));
    if (this.plainSolution.isPresent()) {
      string += "\n\n** Rätsel nicht versteckt\n\n" + this.plainSolution.get() +
          "\n\n** Rätsel versteckt\n\n" + this.hiddenSolution.get() +
          "\n\nKompaktheitsmaß: " + this.compactness + "\n";

    } else {
      string += "\n\nKeine Lösung\n";
    }
    return string;
  }

}
