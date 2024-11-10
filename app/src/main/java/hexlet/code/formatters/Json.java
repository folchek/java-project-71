package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Status;
import java.io.IOException;
import java.util.List;

public final class Json implements StringFormatter {

    @Override
    public String format(List<Status> differNodeList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(differNodeList);
    }
}
