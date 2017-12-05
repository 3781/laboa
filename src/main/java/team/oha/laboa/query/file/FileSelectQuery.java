package team.oha.laboa.query.file;

import team.oha.laboa.query.PageQuery;

import java.io.Serializable;

public class FileSelectQuery implements Serializable{

    private FileFilterQuery filterQuery;
    private PageQuery pageQuery;
    private FileOrderQuery orderQuery;

    public FileFilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(FileFilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public FileOrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(FileOrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }
}
