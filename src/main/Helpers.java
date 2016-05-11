package main;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Sebastian Oberhoff
 */
public final class Helpers {

  public static String charArrayToString(char[][] characters) {
    return Arrays.stream(characters).map(String::new).collect(Collectors.joining("\n"));
  }
}
