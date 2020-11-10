package com.restcode.restcode.BDD;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

public class CucumberConfiguration
{
    private final ObjectMapper objectMapper;

    public CucumberConfiguration()
    {
        objectMapper = new ObjectMapper();
    }

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform( Object from, Type to)
    {
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }
}
