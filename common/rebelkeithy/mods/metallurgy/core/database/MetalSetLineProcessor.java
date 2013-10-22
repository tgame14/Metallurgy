package rebelkeithy.mods.metallurgy.core.database;

import java.util.Map;

import rebelkeithy.mods.metallurgy.core.database.csv.CSVLineProcessor;

import com.google.common.collect.Table;

public class MetalSetLineProcessor extends CSVLineProcessor<Boolean>
{
    private final Table<String, String, Map<String, String>> metalSets;

    MetalSetLineProcessor(final Table<String, String, Map<String, String>> metalSets)
    {
        this.metalSets = metalSets;
    }

    @Override
    public Boolean getResult()
    {
        return true;
    }

    @Override
    public void processLine(final Map<String, String> line)
    {
        metalSets.put(line.get("Metal Set"), line.get("Name"), line);
    }
}
