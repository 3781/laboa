package team.oha.laboa.query.cooperation.apply;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class ApplySelectQuery implements Serializable{

    private ApplyFilterQuery filterQuery;
    private PageQuery pageQuery;
    private ApplyOrderQuery orderQuery;

    public ApplyFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(ApplyFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public ApplyOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(ApplyOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
