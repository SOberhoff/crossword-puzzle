package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sebastian Oberhoff
 */
final class Reader {

  private Reader() {
    //noninstantiable
  }

  static Puzzle read(Path path) {
    try {
      Map<Boolean, List<String>> lines = Files.readAllLines(path).stream()
          .collect(Collectors.partitioningBy(line -> line.startsWith(";"), Collectors.toList()));
      String comment = lines.get(true).stream()
          .collect(Collectors.joining("\n"));
      List<String> words = lines.get(false).stream()
          .map(String::toUpperCase)
          .collect(Collectors.toList());
      return new Puzzle(comment, words);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
