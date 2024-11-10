package hexlet.code.formatters;

import hexlet.code.Status;

import java.io.IOException;
import java.util.List;

public interface StringFormatter {
    String format(List<Status> differEntryList) throws IOException;
}
