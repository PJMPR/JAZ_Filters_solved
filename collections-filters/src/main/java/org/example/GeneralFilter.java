package org.example;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class GeneralFilter implements IFilterPeople {
    private Predicate<SearchParameters> canFilterPredicate;
    private BiPredicate<SearchParameters, Person> filterpredicate;

    private SearchParameters searchParameters;
    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, BiPredicate<SearchParameters, Person> filterpredicate) {
        this.canFilterPredicate = canFilterPredicate;
        this.filterpredicate = filterpredicate;
    }

    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters=searchParameters;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParameters);
    }

    @Override
    public List<Person> filter(List<Person> items) {
        return items.stream().filter(person -> filterpredicate.test(searchParameters, person)).toList();
    }
}
