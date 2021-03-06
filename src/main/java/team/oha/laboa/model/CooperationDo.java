package team.oha.laboa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public class CooperationDo implements Serializable{
    private Integer cooperationId;
    private Integer parentId;
    private String name;
    private String remark;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Boolean invite;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getInvite() {
        return invite;
    }

    public void setInvite(Boolean invite) {
        this.invite = invite;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CooperationDo{" +
                "cooperationId=" + cooperationId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", invite=" + invite +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
