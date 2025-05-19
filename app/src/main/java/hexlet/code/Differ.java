package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(filePath1);  // Теперь принимает String
        Map<String, Object> data2 = Parser.parse(filePath2);
        Map<String, Object> diff = DiffCalculator.calculate(data1, data2);

        return getFormatter(format).format(diff);
    }

    private static Formatter getFormatter(String format) throws Exception {
        return switch (format) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            default -> throw new Exception("Unknown format: " + format);
        };
    }
}
