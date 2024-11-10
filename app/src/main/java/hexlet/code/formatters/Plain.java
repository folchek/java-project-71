package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.List;
import java.util.Map;

public final class Plain implements StringFormatter {

    @Override
    public String format(List<Status> differNodeList) {
        List<Status> filteredList = differNodeList.stream()
                .filter(node -> !node.getStatusName().equals(Status.UNCHANGED))
                .toList();

        StringBuilder sb = new StringBuilder();

        for (Status node : filteredList) {
            String condition = node.getStatusName();
            switch (condition) {
                case Status.ADDED -> {
                    String value = formatComplexValue(node.getNewValue());
                    sb.append("Property '%s' was added with value: %s%n"
                            .formatted(node.getStatusName(), value));
                }
                case Status.DELETED -> sb.append("Property '%s' was removed%n"
                        .formatted(node.getStatusName()));
                case Status.CHANGED -> {
                    String value1 = formatComplexValue(node.getOldValue());
                    String value2 = formatComplexValue(node.getNewValue());
                    sb.append("Property '%s' was updated. From %s to %s%n"
                            .formatted(node.getStatusName(), value1, value2));
                }
                default -> throw new IllegalStateException("Unexpected value: " + condition);
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static String formatComplexValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return value.toString();
    }
}
