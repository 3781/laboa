package team.oha.laboa.query.user;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class UserSelectQuery implements Serializable{

    private UserFilterQuery filterQuery;
    private PageQuery pageQuery;
    private UserOrderQuery orderQuery;

    public UserFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(UserFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public UserOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(UserOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
