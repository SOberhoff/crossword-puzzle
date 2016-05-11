package main;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Sebastian Oberhoff
 */
public final class PredefinedTestCases {

  @Test
  public void runPredefinedTests() throws IOException {
    Files.walk(Paths.get("test/test_files"))
        .filter(Files::isRegularFile)
        .map(Path::toAbsolutePath)
        .map(Object::toString)
        .forEach(Main::main);
  }
  
}
