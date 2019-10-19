package movies.data;

import java.util.ArrayList;
import java.util.List;

public class RepositoryUtil {

    public static <T> List<T> toList(final Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
