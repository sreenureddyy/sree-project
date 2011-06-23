package com.sree.test;

import java.util.List;

/**
 * Data provider for use in PageList. Paged data providers (ie. Dao for example) are passed into 
 * the lazy list to provide page size, dataset size and pages of data on request. The PagedList 
 * manages which pages are requested when.
 * 
 * @param <Dto> The object type of (remotely) persisted objects.
 * @param <QueryParameters> The custom query object type to be passed to the [i]provide()[/i] 
 *          method. Could be a parameter object, or a List with param strings for example.
 * @author Benny Bottema
 */
public interface PagedDataProvider<Dto, QueryParameters> {
    /**
     * Provides a page of (remote) data.
     * 
     * @param page The pagenumber to use when querying the datasource. Pagesize  is already known 
     *          (determined by the implementing class).
     * @param queryParameters Optional parameters to be used when querying a page of data. Are 
     *          provided by the PagedList and should be immutable for consistent resultsets. 
     *          Can be <code>null</code>.
     * @return A list of the requested persisted objects (dto's).
     */
    List<Dto> provide(int page, QueryParameters queryParameters);
    
    /**
     * @return The total size of the dataset. Should include all data ptentially fetchable in pages.
     */
    int getDataSize();
    
    /**
     * @return The pagesize as determined by the implementing class. A suggestion is to inject this 
     *          property using Spring for example, to be able to change pagesize without recompiling.
     */
    int getPageSize();
}