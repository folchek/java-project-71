package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public final class AppTest {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 15;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
        System.setOut(new PrintStream(output));
        expectedStylish = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_STYLISH")));
        expectedPlain = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_PLAIN")));
        expectedJson = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_JSON")));
    }

    @Test
    @DisplayName("App works correctly")
    void testSuccessExitCode() throws Exception {
        runTestWithArgs(new String[]{"-f=stylish",
                getPathToFixture("fileNested1.json"), getPathToFixture("fileNested2.json")}, expectedStylish);
        runTestWithArgs(new String[]{"-f=plain",
                getPathToFixture("fileNested3.yml"), getPathToFixture("fileNested4.yml")}, expectedPlain);

        String[] argsYamlJson = {"-f=json",
                getPathToFixture("fileNested3.yml"), getPathToFixture("fileNested2.json")};
        int exitCode = new CommandLine(new App()).execute(argsYamlJson);
        JSONAssert.assertEquals(expectedJson, output.toString(StandardCharsets.UTF_8).trim(), JSONCompareMode.STRICT);
        assertEquals(SUCCESS_EXIT_CODE, exitCode);
    }

    @Test
    @DisplayName("App returns error exit code when provided with incorrect args")
    void testErrorExitCode() {
        int exitCode = new CommandLine(new App()).execute(new String[]{"-f=stailish",
                getPathToFixture("fileNested1.json"), getPathToFixture("fileNested2.json")});
        assertEquals(ERROR_EXIT_CODE, exitCode);

        exitCode = new CommandLine(new App()).execute(new String[]{"-f=stylish",
                getPathToFixture("file.yml"), getPathToFixture("fileNested3.yml")});
        assertEquals(ERROR_EXIT_CODE, exitCode);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    private void runTestWithArgs(String[] args, String expectedOutput) throws Exception {
        output.reset();
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(expectedOutput, output.toString(StandardCharsets.UTF_8).trim());
        assertEquals(SUCCESS_EXIT_CODE, exitCode);
    }

    private String getPathToFixture(String file) {
        return ".\\src\\test\\resources\\" + file;
    }
}
