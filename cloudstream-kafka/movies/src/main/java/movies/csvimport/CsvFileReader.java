package movies.csvimport;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class CsvFileReader {

    public <T> List<T> loadObjectList(Class<T> type, InputStream inputStream) {
        try {
            final CsvMapper csvMapper = new CsvMapper();
            final CsvSchema schema = CsvSchema.emptySchema().withHeader();
            final ObjectReader reader = csvMapper.readerFor(type).with(schema);
            return reader.<T>readValues(inputStream).readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
