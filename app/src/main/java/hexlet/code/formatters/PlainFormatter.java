package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, Object> diff) {
        return "... plain format output ...";
    }
}
