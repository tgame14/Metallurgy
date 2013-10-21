package rebelkeithy.mods.metallurgy.core.database.csv;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.LineProcessor;

public abstract class CSVLineProcessor<T> implements LineProcessor<T>
{
    private final List<String> header = Lists.newArrayList();

    private void buildHeader(final Iterable<String> fields)
    {
        for (final String field : fields)
            header.add(field);
    }

    private Map<String, String> nameValuePairsFromCSVLine(final Iterable<String> fields)
    {
        final Map<String, String> data = Maps.newHashMap();

        int i = 0;
        for (String field : fields)
        {
            if (field.isEmpty() || field.equals("-")) field = "0";
            data.put(header.get(i++), field);
        }
        return data;
    }

    public abstract void processLine(Map<String, String> line);

    @Override
    public final boolean processLine(final String line) throws IOException
    {
        final Iterable<String> fields = Splitter.on(',').split(line);
        if (header.isEmpty()) buildHeader(fields);
        else
        {
            final Map<String, String> data = nameValuePairsFromCSVLine(fields);
            processLine(data);
        }

        return true;
    }
}
