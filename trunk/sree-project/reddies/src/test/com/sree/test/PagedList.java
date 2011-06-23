package com.sree.test;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedList<Dto, QueryParameters> extends AbstractList<Dto> {

    private final QueryParameters queryParameters;
    
    private final PagedDataProvider<Dto, QueryParameters> pagedDataProvider;
    
    private final Map<Integer, List<Dto>> fetchedPages;
    
    private final int dataSize;
    
    private final int pageSize;
    
    public PagedList(PagedDataProvider<Dto, QueryParameters> pagedDataProvider, QueryParameters queryParameters) {
        this.pagedDataProvider = pagedDataProvider;
        this.queryParameters = queryParameters;
        fetchedPages = new HashMap<Integer, List<Dto>>();
        dataSize = pagedDataProvider.getDataSize();
        pageSize = pagedDataProvider.getPageSize();
    }

    public Dto get(int index) {
        int pageNr = (int) Math.floor(index / pageSize);
        if (fetchedPages.get(pageNr) == null) {
            fetchedPages.put(pageNr, pagedDataProvider.provide(pageNr, queryParameters));
        }
        return fetchedPages.get(pageNr).get(index % pageSize);
    }

    public int size() {
        return dataSize;
    }
}