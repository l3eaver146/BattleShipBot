package algorithms.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FieldStatuses {
    EMPTY("empty"),
    MISS_AUTO("miss_auto"),
    MISS("miss"),
    HIT("hit"),
    DONE("done");
    private final String title;

    public static FieldStatuses getByName(String name) {
        return Arrays.stream(FieldStatuses.values())
                .filter(element -> element.title.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException((String.format("Unsupported type %s", name))));
    }
}
