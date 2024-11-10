package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.List;

public final class Stylish implements StringFormatter {

    @Override
    public String format(List<Status> differNodeList) {
        StringBuilder sb = new StringBuilder("{\n");
        final int indentation = 2;

        for (Status node : differNodeList) {
            String condition = node.getStatusName();
            switch (condition) {
                case Status.ADDED -> sb.append(" ".repeat(indentation))
                        .append("+ %s: %s%n".formatted(node.getStatusName(), node.getNewValue()));
                case Status.DELETED -> sb.append(" ".repeat(indentation))
                        .append("- %s: %s%n".formatted(node.getStatusName(), node.getOldValue()));
                case Status.CHANGED -> {
                    sb.append(" ".repeat(indentation))
                            .append("- %s: %s%n".formatted(node.getStatusName(), node.getOldValue()));
                    sb.append(" ".repeat(indentation))
                            .append("+ %s: %s%n".formatted(node.getStatusName(), node.getNewValue()));
                }
                case Status.UNCHANGED -> sb.append(" ".repeat(indentation))
                        .append("  %s: %s%n".formatted(node.getStatusName(), node.getOldValue()));
                default -> throw new IllegalStateException("Unexpected value: " + condition);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
