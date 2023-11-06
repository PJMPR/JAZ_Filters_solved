package org.example;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.util.List;

public interface ICalculate {
    String getFieldName();
    double calculate(FunctionsParameters functionsParameters, List<Person> data);
}
