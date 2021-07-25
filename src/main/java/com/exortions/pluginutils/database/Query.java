package com.exortions.pluginutils.database;

/**
 * @since 0.4.24.23
 */
@SuppressWarnings("unused")
public class Query {

    private String query;

    public Query(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
