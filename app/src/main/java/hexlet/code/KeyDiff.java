package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class KeyDiff {

    public static List<Status> makeList(Map<String, Object> data1, Map<String, Object> data2) {
        List<Status> statusList = new ArrayList<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            Status status = makeStatus(key, data1, data2);
            statusList.add(status);
        }

        return statusList;
    }

    private static Status makeStatus(String key, Map<String, Object> data1, Map<String, Object> data2) {
        Object oldValue = data1.get(key);
        Object newValue = data2.get(key);

        if (!data1.containsKey(key)) {
            return new Status(Status.ADDED, null, newValue);
        } else if (!data2.containsKey(key)) {
            return new Status(Status.DELETED, oldValue, null);
        } else if (Objects.equals(oldValue, newValue)) {
            return new Status(Status.UNCHANGED, oldValue, newValue);
        } else {
            return new Status(Status.CHANGED, oldValue, newValue);
        }
    }
}

