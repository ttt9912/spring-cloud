package stringsource;

import java.util.List;

import static collections.AnyElementFunction.any;

public class StaticStrings {
    private static final List<String> names = List.of("Thomas", "Fritz", "Peter", "Hans", "Glenn");

    public static String random() {
        return any(names);
    }

}
