package br.com.gok.starwarsapi.util;

import br.com.gok.starwarsapi.exception.BadRequestException;
import br.com.gok.starwarsapi.exception.InvalidQueryException;
import br.com.gok.starwarsapi.service.query.PopulationSearchCriteria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaFactory {
    public static SearchCriteria createSearchCriteria(String query){
        SearchCriteria criteria;
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=)(\\w+?),");
        Matcher matcher = pattern.matcher(query + ",");
        if (!matcher.find()) {
            throw new InvalidQueryException(Constants.INVALID_QUERY);
        }

        String key = matcher.group(1);
        String operator = matcher.group(2);
        String value = matcher.group(3);
        key = matcher.group(1);
        switch (key){
            case PopulationSearchCriteria.KEY: criteria = new PopulationSearchCriteria(operator,  value); break;
            default: throw new BadRequestException(String.format(Constants.BAD_REQUEST_NOT_VALID,"key",key));
        }
        validateSearchCriteria(criteria,operator);
        return criteria;
    }

    private static void validateSearchCriteria(SearchCriteria criteria, String givenOperator){
        boolean containsOperators = criteria.getOperators().stream().map(QueryOperators::getValue).anyMatch(operator -> operator.equalsIgnoreCase(givenOperator));
        if(!containsOperators){
            throw new BadRequestException(String.format(Constants.BAD_REQUEST_NOT_VALID,"operator",givenOperator));
        }
    }
}
