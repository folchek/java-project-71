package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter implements Formatter {
    @Override
    public String format(Map<String, Object> diff) {
        return "{\n  ... stylish format output ...\n}";
    }
}
