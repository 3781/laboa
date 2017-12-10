package team.oha.laboa.dto;

import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
public class CooperationTreeDto {
    private Integer cooperationId;
    private Integer parentId;
    private String name;
    private List<CooperationTreeDto> children;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CooperationTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<CooperationTreeDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CooperationTreeDto{" +
                "cooperationId=" + cooperationId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
