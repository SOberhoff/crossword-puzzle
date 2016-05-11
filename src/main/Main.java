package main;

import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

  private Main() {
    //noninstantiable
  }

  public static void main(String... args) {
    Arrays.stream(args)
        .map(Paths::get)
        .map(Reader::read)
        .map(Evaluator::solve)
        .map(Puzzle::toString)
        .forEach(System.err::println);
  }
}
