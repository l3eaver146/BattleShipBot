package browserFactory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox");
    private final String title;

    public static Browsers getByName(String name) {
       return Arrays.stream(Browsers.values())
               .filter(element -> element.title.equalsIgnoreCase(name))
               .findFirst()
               .orElseThrow(()-> new IllegalArgumentException((String.format("Unsupported type %s",name))));
    }
}
