package hexlet.code;

public final class Status {
    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String statusName;
    private final Object oldValue;
    private final Object newValue;

    public Status(String statusName, Object oldValue, Object newValue) {
        this.statusName = statusName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        return "Status{"
                +
                "statusName='"
                + statusName
                + '\''
                +
                ", oldValue="
                + oldValue
                +
                ", newValue="
                + newValue
                +
                '}';
    }
}

