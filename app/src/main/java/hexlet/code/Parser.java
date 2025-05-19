package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Path.of(filePath);
        try {
            String content = Files.readString(path);
            return MAPPER.readValue(content, Map.class);
        } catch (Exception e) {
            throw new ParsingException("Failed to parse file: " + filePath, e);
        }
    }
}
