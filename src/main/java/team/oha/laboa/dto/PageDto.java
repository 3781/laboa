package team.oha.laboa.dto;

import java.io.Serializable;
import java.util.List;

public class PageDto<T> implements Serializable {
    private Integer totalSize;
    private List<T> data;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
