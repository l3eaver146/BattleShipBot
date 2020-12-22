package algorithms.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ShotVariant {
    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    CURRENT("current"),
    DOWN("down");
    private final String title;

    public static ShotVariant getByName(String name) {
        return Arrays.stream(ShotVariant.values())
                .filter(element -> element.title.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException((String.format("Unsupported type %s", name))));
    }
}
