package puzzle;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The test cases from the problem sheet
 *
 * A temporary directory is created at the beginning. The tests then copy the test files to that
 * directory, call main, and verify that main created the correct output file in the temporary directory.
 *
 * There are multiple sources of non-determinism caused by hashCodes and parallelism that cause ties
 * in the search algorithm to be broken in an unpredictable fashion, which prevents checking for exact
 * String equality. Checking for correct output length and compactness is probably as close as I can get.
 *
 * @author Sebastian Oberhoff
 */
public final class PredefinedTestCases {

  private Path tempDirectory;

  @Before
  public void createTempDirectory() throws IOException {
    this.tempDirectory = Files.createTempDirectory("temp_test");
  }

  @Test
  public void testCase1() throws IOException {
    Path input = Paths.get("test/test_files/Testfall_1");
    Path tempSource = tempDirectory.resolve(input.getFileName());
    Path tempDestination = Paths.get(tempSource.toString() + "_out");
    Files.copy(input, tempSource);

    Main.main(tempSource.toString());
    String output = new String(Files.readAllBytes(tempDestination));

    assertEquals(295, output.length());
    assertTrue(output.contains("Kompaktheitsmaß: 36"));
  }

  @Test
  public void testCase2() throws IOException {
    Path input = Paths.get("test/test_files/Testfall_2");
    Path tempSource = tempDirectory.resolve(input.getFileName());
    Path tempDestination = Paths.get(tempSource.toString() + "_out");
    Files.copy(input, tempSource);

    Main.main(tempSource.toString());
    String output = new String(Files.readAllBytes(tempDestination));

    assertEquals(493, output.length());
    assertTrue(output.contains("Kompaktheitsmaß: 85"));
  }

  @Test
  public void testCase3() throws IOException {
    Path input = Paths.get("test/test_files/Testfall_3");
    Path tempSource = tempDirectory.resolve(input.getFileName());
    Path tempDestination = Paths.get(tempSource.toString() + "_out");
    Files.copy(input, tempSource);

    Main.main(tempSource.toString());
    String output = new String(Files.readAllBytes(tempDestination));

    assertEquals(459, output.length());
    assertTrue(output.contains("Kompaktheitsmaß: 98"));
  }

  @Test
  public void testCase4() throws IOException {
    Path input = Paths.get("test/test_files/Testfall_4");
    Path tempSource = tempDirectory.resolve(input.getFileName());
    Path tempDestination = Paths.get(tempSource.toString() + "_out");
    Files.copy(input, tempSource);

    Main.main(tempSource.toString());
    String output = new String(Files.readAllBytes(tempDestination));

    assertEquals(483, output.length());
    assertTrue(output.contains("Kompaktheitsmaß: 98"));
  }

}
