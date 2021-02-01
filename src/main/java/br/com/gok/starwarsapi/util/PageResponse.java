package br.com.gok.starwarsapi.util;

import lombok.*;

import java.util.List;

@Value
public class PageResponse<C>{
    private int size,totalPages,pageNumber;
    private long totalElements;
    private final List<C> elements;
}