package hexlet.code.formatters;

import java.util.Map;

public interface Formatter {
    String format(Map<String, Object> diff) throws Exception;
}