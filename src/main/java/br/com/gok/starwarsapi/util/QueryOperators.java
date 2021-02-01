package br.com.gok.starwarsapi.util;

public enum QueryOperators {
    GRATER_THAN(">"),LASS_THAN("<"),EQUAL(":");
    private String value;

    QueryOperators(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


