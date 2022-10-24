package org.example;

import org.example.model.Gender;
import org.example.model.PeopleSample;
import org.example.model.Person;
import org.example.queries.QueryProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){

        /**
         * Zacznijmy od przygotowania sobie kolekcji na której będziemy pracować.
         *
         * Zanim zaczniesz pracę zaznajom się z klasami w projekcie
         */

//        List<Person> sampleData = PeopleSample.Data;
//
//        SearchParameters searchParameters = new SearchParameters();
//
//        searchParameters.setName("jan");


        /**
         * Zacznijmy od utworzenia interfejsu o nazwie IFilterPeople,
         * implementacje tego interfejsu będą przechowywać informacje,
         * niezbędne do przefiltrowywania kolekcji osób
         */

//        IFilterPeople byName = new ByNameFilter();

        /**
         * Niech ten interfejs posiada następujące metody:
         * -> setSearchParameters - setter dla parametrów wyszukiwania
         * -> canFilter - metoda która spawdza czy dany filtr jest będzie teraz wykorzystany
         * -> filter - metoda któa zwróci nowy odflitrowany strumień kolekcji
         */

//        byName.setSearchParameters(searchParameters);
//
//        Stream<Person> streamOfPeople = sampleData.stream();
//
//        if(byName.canFilter()){
//            streamOfPeople = byName.filter(streamOfPeople);
//        }

        /**
         * Dodajmy jeszcze 2 implementacje dla filtrów:
         * -> wiek od,
         * -> wiek do
         */

//        searchParameters.setName(null);
//        searchParameters.setAgeFrom(30);
//
//        IFilterPeople byAgeFromFilter = new ByAgeFromFilter();
//        byAgeFromFilter.setSearchParameters(searchParameters);
//        IFilterPeople byAgeToFilter = new ByAgeToFilter();
//        byAgeToFilter.setSearchParameters(searchParameters);

        /**
         * Pewnie jesteś bystrym programistą/ką
         * i zobaczyłeś/aś, że kod wcześniej da się uogólnić.
         * Spróbuj to zrobić tworząc klasę GeneralFilter,
         * która w konstruktorze przyjmie dwie lambdy:
         * -> predykat (użyj interfejsu java Predicate<SearchParameters>) sprawdzający, czy filtr ma być użyty
         * -> własny predykat który przyjmie dwie wartości,
         *    który zwróci warunek filtracji (tutaj trzeba będzie dostarczyć własny interfejs funkcjonalny)
         */

//        IFilterPeople genericFilter = new GeneralFilter(
//                (searchParams)->searchParams.getSelectedGenders().size()>0,
//                (searchParams, person)->searchParams.getSelectedGenders().contains(person.getGender())
//        );


        /**
         * Rozszerzmy klasę QueryProcessor o metody pozwalające
         * na dodawanie filtrów do kolekcji
         */

//        QueryProcessor queryProcessor = new QueryProcessor()
//                .addFilter(byName)
//                .addFilter(byAgeToFilter)
//                .addFilter(byAgeToFilter)
//                .addFilter(genericFilter);

        /**
         * Dostarcz dwa obiekty generycznego filtra:
         * -> zarobki od
         * -> zarobki do
         *
         * i dodaj je do obiektu QueryProcessora
         */

        /******************************************
         * W tym miejscu pozwalam na edycje maina *
         ******************************************/

//        queryProcessor.addFilter(byIncomeToGenericFilter)
//                .addFilter(byIncomeFromGenericFilter);


        /**
         * Teraz przygotujemy mechanizm do wykonywania obliczeń
         *
         * Dodaj nowy interfejs o nazwie ICalculate
         * i jego implementacje jako klasę GeneralCalculator,
         * która ma konstruktor z dwoma argumentami:
         * -> nazwa pola którym będziemy wykonywać obliczenia
         * -> lambda (użyj interfejsu java Function<Person, Number>),
         *    która zwraca te pole (jak typ danych dla liczb użyj klasy bazowej dla wszystkich liczb)
         */

//        ICalculate incomeCalculator = new GeneralCalculator("income", p-> p.getIncome() );

        /**
         * Niech interfejs posiada metodę o nazwie calculate,
         * która ma dwa parametry:
         * -> obiekt typu FunctionsParameters, który posiada informacje o tym po jakim polu wykonujemy obliczenia oraz rodzaj obliczeń
         * -> strumień do kolekcji osób
         *
         * W wyniku ma zwrócić liczbę double która jest wynikiem obliczeń
         */

//        double sumOfIncomes = incomeCalculator
//                .calculate(new FunctionsParameters("income", Funcs.SUM), streamOfPeople);
//        ICalculate ageCalculator = new GeneralCalculator("age", p-> p.getAge());

        /**
         * dodajmy nasze kalkulatory, do obiektu klasy QueryProcessor
         */

//        queryProcessor.addCalculation(incomeCalculator)
//                .addCalculation(ageCalculator);

        /**
         * Ostatnim krokiem do zakońćzenia zadania jest
         * przygotowanie interfejsu ICutToPage,
         * którego jedyna implementacja PageCutter
         * będzie "kroiła" strumień osób do wcześniej zadeklarowanej strony wyników
         */

//        ICutToPage pageCutter = new PageCutter();
//
//        streamOfPeople = pageCutter.cut(new Page(3,2), streamOfPeople);

        /**
         * dodajmy też ten obiekt do QueryProcessora
         */

//        queryProcessor.addPageCutter(pageCutter);

        /**
         * I teraz wisienka na torcie,
         * czyli wykorzystajmy wszystko co do tej pory napisaliśmy
         * i wykonajmy filtrowanie danych,
         * wszystkie potrzebne kalkulacje,
         * oraz zwróćmy rządaną stronę wyników
         */

//        Results results = queryProcessor.GetResults(sampleSearchParams(), PeopleSample.Data);
//
//        if(!resultsAreGood(results)){
//            System.out.println("filtrowanie nie działa prawidłowo :(");
//            return;
//        }
//
//        System.out.println("wygląda akceptowalnie.");

    }

    private static SearchParameters sampleSearchParams(){
        SearchParameters params =new SearchParameters();
        params.setAgeFrom(20);
        params.setAgeTo(40);
        params.setIncomeFrom(2000);
        params.setPage(new Page(9,1));
        params.getSelectedGenders().add(Gender.FEMALE);
        params.getSelectedGenders().add(Gender.OTHER);
        params.getFunctions().add(new FunctionsParameters("age", Funcs.AVERAGE));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.SUM));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.AVERAGE));
        return params;
    }

    private static boolean resultsAreGood(Results result){

        return result.getItems().size() == 3
                && result.getItems().contains(PeopleSample.AnnaBuda)
                && result.getItems().contains(PeopleSample.ConchitaWurst)
                && result.getItems().contains(PeopleSample.AnetaUrban)
                &&result.getCurrentPage() == 1
                && result.getPages() == 1
                && result.getFunctionResults().size() == 3;
    }
}
