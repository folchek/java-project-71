package hexlet.code;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class DifferTest {
    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
        expectedStylish = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_STYLISH")));
        expectedPlain = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_PLAIN")));
        expectedJson = Differ.readFile(Differ.getPath(getPathToFixture("EXPECTED_JSON")));
    }

    @Test
    void testGenerateWithTwoArgs() throws Exception {
        String actualStylish = Differ.generate(getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested4.yml"));
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    void testGenerateWithThreeArgs() throws Exception {
        assertEquals(expectedStylish,
                Differ.generate(getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested4.yml"), "stylish"));
    }

    @Test
    void testGenerateWithJson() throws Exception {
        assertEquals(expectedStylish,
                Differ.generate(getPathToFixture("fileNested1.json"),
                        getPathToFixture("fileNested2.json"), "stylish"));
    }

    @Test
    void testGenerateWithJsonPlain() throws Exception {
        assertEquals(expectedPlain,
                Differ.generate(getPathToFixture("fileNested1.json"),
                        getPathToFixture("fileNested2.json"), "plain"));
    }

    @Test
    void testGenerateWithJsonJson() throws Exception {
        String actualJson = Differ.generate(getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"), "json");
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    @Test
    void testGenerateWithYaml() throws Exception {
        assertEquals(expectedStylish,
                Differ.generate(getPathToFixture("fileNested3.yml"),
                        getPathToFixture("fileNested4.yml"), "stylish"));
    }

    @Test
    void testGenerateWithYamlPlain() throws Exception {
        assertEquals(expectedPlain,
                Differ.generate(getPathToFixture("fileNested3.yml"),
                        getPathToFixture("fileNested4.yml"), "plain"));
    }

    @Test
    void testGenerateWithYamlJson() throws Exception {
        String actualJson = Differ.generate(getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"), "json");
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    private String getPathToFixture(String file) {
        return "\"C:\\Users\\bespa\\MyProjects\\java-project-71\\app\\src\\test\\resources\"" + file;
    }
}