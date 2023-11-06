package org.example.queries;

import org.example.ICalculate;
import org.example.ICutToPage;
import org.example.IFilterPeople;
import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class QueryProcessor {

    List<IFilterPeople> filters = new ArrayList<>();
    List<ICalculate> calculators = new ArrayList<>();
    ICutToPage pageCutter;

    public Results GetResults(SearchParameters parameters, List<Person> data){

        var filteredData = doFilter(parameters, data);
        var functionResults = doCalculations(parameters.getFunctions(), filteredData);
        var cuttedData = pageCutter.cut(parameters.getPage(), filteredData);
        return prepareResults(parameters.getPage(), filteredData, functionResults, cuttedData);
    }

    private static Results prepareResults(Page page, List<Person> filteredData, List<FunctionResult> functionResults, List<Person> cuttedData) {
        Results results = new Results();
        results.setItems(cuttedData);
        results.setFunctionResults(functionResults);
        results.setCurrentPage(page.getPageNumber());
        results.setPages(filteredData.size()/ page.getSize());
        return results;
    }

    private List<FunctionResult> doCalculations(List<FunctionsParameters> parametersFunctions, List<Person> data) {
        var results = new ArrayList<FunctionResult>();
        for (ICalculate calculator: calculators){
            for(FunctionsParameters fp: parametersFunctions)
            if(calculator.getFieldName().equals(fp.getFieldName())){
                calculateResult(data, results, calculator, fp);
            }
        }

        return results;
    }

    private static void calculateResult(List<Person> data, ArrayList<FunctionResult> results, ICalculate calculator, FunctionsParameters fp) {
        var result = new FunctionResult();
        result.setFieldName(calculator.getFieldName());
        result.setFunction(fp.getFunction());
        result.setValue(calculator.calculate(fp, data));
        results.add(result);
    }

    private List<Person> doFilter(SearchParameters parameters, List<Person> data) {
        List<Person> result = new ArrayList<>(data);
        for(IFilterPeople filter: filters){
            filter.setSearchParameters(parameters);
            if(filter.canFilter())
               result = filter.filter(result);
        }
        return result;
    }

    public QueryProcessor add(IFilterPeople filter) {
        filters.add(filter);
        return this;
    }

    public QueryProcessor add(ICalculate calculator) {
        calculators.add(calculator);
        return this;
    }

    public QueryProcessor add(ICutToPage pageCutter) {
        this.pageCutter=pageCutter;
        return this;
    }
}
