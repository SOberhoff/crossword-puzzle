package puzzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sebastian Oberhoff
 */
public class Main {

  private Main() {
    //non-instantiable
  }

  public static void main(String... args) throws IOException {
    for (String arg : args) {
      Path sourcePath = Paths.get(arg);
      Puzzle puzzle = read(sourcePath);
      Evaluator.solve(puzzle);
      write(sourcePath, puzzle.toString());
    }
  }

  /**
   * Reads the input from the file at the specified location and separates comments from words before
   * returning a newly created {@link Puzzle} instance.
   */
  static Puzzle read(Path sourcePath) throws IOException {
    Map<Boolean, List<String>> lines = Files.readAllLines(sourcePath).stream()
        .collect(Collectors.partitioningBy(line -> line.startsWith(";"), Collectors.toList()));

    String comment = lines.get(true).stream().collect(Collectors.joining("\n"));
    List<String> words = lines.get(false).stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());

    return new Puzzle(comment, words);
  }

  /**
   * Writes the output to the same location as the input but with the suffix "_out" appended to the
   * filename. Also converts newlines to the system dependent line break character.
   */
  static void write(Path sourcePath, String output) throws IOException {
    Files.write(Paths.get(sourcePath.toString() + "_out"),
        output.replaceAll("\n", System.getProperty("line.separator")).getBytes());
  }
}
