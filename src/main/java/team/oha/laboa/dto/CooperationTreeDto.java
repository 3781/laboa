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
    private String label;
    private List<CooperationTreeDto> children;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
