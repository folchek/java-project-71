package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;

public class Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> TYPE_REF =
            new TypeReference<Map<String, Object>>() {};

    public static Map<String, Object> parse(String filePath) throws Exception {
        try {
            String content = Files.readString(Path.of(filePath));
            return MAPPER.readValue(content, TYPE_REF);
        } catch (Exception e) {
            throw new Exception("Failed to parse file: " + filePath, e);
        }
    }
}
