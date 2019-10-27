package collections;

import java.util.List;
import java.util.Random;

public class AnyElementFunction {
    private static final Random random = new Random();

    public static <T> T any(final List<T> objects) {
        return objects.get(randomIndex(objects.size() - 1));
    }

    private static int randomIndex(final int limit) {
        return random.nextInt(limit);
    }
}
