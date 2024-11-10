package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = getPath(filePath1);
        Path path2 = getPath(filePath2);

        String data1 = readFile(path1);
        String data2 = readFile(path2);
        String dataFormat1 = getDataFormat(path1);
        String dataFormat2 = getDataFormat(path2);

        Map<String, Object> parsedData1 = Parser.parse(data1, dataFormat1);
        Map<String, Object> parsedData2 = Parser.parse(data2, dataFormat2);

        List<Status> differStatusList = KeyDiff.makeList(parsedData1, parsedData2);

        return Formatter.formatString(differStatusList, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String readFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }

    private static String getDataFormat(Path filePath) {
        String fileName = filePath.getFileName().toString();
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

    public static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }
}
