package team.oha.laboa.query.agenda;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class AgendaSelectQuery implements Serializable{

    private AgendaFilterQuery filterQuery;
    private PageQuery pageQuery;
    private AgendaOrderQuery orderQuery;

    public AgendaFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(AgendaFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public AgendaOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(AgendaOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
