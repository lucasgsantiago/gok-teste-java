package br.com.gok.starwarsapi.service.query;

import br.com.gok.starwarsapi.util.QueryOperators;
import br.com.gok.starwarsapi.util.SearchCriteria;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PopulationSearchCriteria extends SearchCriteria {
     public static final String KEY = "population";

     public PopulationSearchCriteria(String operation, Object value) {
          super(KEY, operation, value, Stream.of(QueryOperators.EQUAL,QueryOperators.GRATER_THAN,QueryOperators.LASS_THAN)
                  .collect(Collectors.toSet()));
     }

     private void validate(){

     }
}
