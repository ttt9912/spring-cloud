package stringsupplier;

import java.util.List;

import static common.collections.AnyElementFunction.any;


public class StaticStrings {
    private static final List<String> names = List.of("Thomas", "Fritz", "Peter", "Hans", "Glenn");

    public static String getAny() {
        return any(names);
    }

}
