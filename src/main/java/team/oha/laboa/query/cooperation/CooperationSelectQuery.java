package team.oha.laboa.query.cooperation;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class CooperationSelectQuery implements Serializable{

    private CooperationFilterQuery filterQuery;
    private PageQuery pageQuery;
    private CooperationOrderQuery orderQuery;

    public CooperationFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(CooperationFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public CooperationOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(CooperationOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
