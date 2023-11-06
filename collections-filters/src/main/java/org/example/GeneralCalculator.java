package org.example;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneralCalculator implements ICalculate {
    private String fieldName;
    private Function<Person, Number> getter;

    public GeneralCalculator(String fieldName, Function<Person, Number> getter) {
        this.fieldName = fieldName;
        this.getter = getter;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public double calculate(FunctionsParameters functionsParameters, List<Person> data) {


        var numbers = data
                .stream()
                .map(person -> getter.apply(person).doubleValue())
                .toList();
        var results=
                numbers
                        .stream()
                        .collect(Collectors.summarizingDouble(n->n));
        switch (functionsParameters.getFunction()){
            case AVERAGE -> {
                //return numbers.stream().collect(Collectors.averagingDouble(n->n));
                return results.getAverage();
            }
            case SUM -> {
                return results.getSum();
                //return numbers.stream().reduce(0.0, (a,b)->a+b);
            }
            case MAX -> {
                return results.getMax();
                //return numbers.stream().max(Double::compareTo).get();
            }
            case MIN -> {
                return results.getMin();
                //return numbers.stream().min(Double::compareTo).get();
            }
        }
        return 0;
    }
}
