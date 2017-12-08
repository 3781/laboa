package team.oha.laboa.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
public class CooperationDto implements Serializable {
    private Integer cooperationId;
    private Integer parentName;
    private String ownerName;
    private String name;
    private String remark;
    private LocalDate beginTime;
    private LocalDate endTime;
    private Boolean invite;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getParentName() {
        return parentName;
    }

    public void setParentName(Integer parentName) {
        this.parentName = parentName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public LocalDate getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDate beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
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
        return "CooperationDto{" +
                "cooperationId=" + cooperationId +
                ", parentName=" + parentName +
                ", ownerName='" + ownerName + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", invite=" + invite +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
