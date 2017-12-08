package team.oha.laboa.query.cooperation.member;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class MemberSelectQuery implements Serializable{

    private MemberFilterQuery filterQuery;
    private PageQuery pageQuery;
    private MemberOrderQuery orderQuery;

    public MemberFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(MemberFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public MemberOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(MemberOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
