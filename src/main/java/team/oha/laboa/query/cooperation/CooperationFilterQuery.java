package team.oha.laboa.query.cooperation;


import team.oha.laboa.model.CooperationMemberDo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CooperationFilterQuery implements Serializable{
    private Integer cooperationId;
    private CooperationMemberDo.CooperationRole role;
    private String username;
    private Boolean hasParent;
    private String remark;
    private String name;
    private String ownerName;
    private LocalDate beginDateBegin;
    private LocalDate endDateBegin;
    private LocalDate beginDateEnd;
    private LocalDate endDateEnd;
    private LocalDateTime updateTimeBegin;
    private LocalDateTime createTimeBegin;
    private LocalDateTime updateTimeEnd;
    private LocalDateTime createTimeEnd;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public CooperationMemberDo.CooperationRole getRole() {
        return role;
    }

    public void setRole(CooperationMemberDo.CooperationRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getHasParent() {
        return hasParent;
    }

    public void setHasParent(Boolean hasParent) {
        this.hasParent = hasParent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDate getBeginDateBegin() {
        return beginDateBegin;
    }

    public void setBeginDateBegin(LocalDate beginDateBegin) {
        this.beginDateBegin = beginDateBegin;
    }

    public LocalDate getEndDateBegin() {
        return endDateBegin;
    }

    public void setEndDateBegin(LocalDate endDateBegin) {
        this.endDateBegin = endDateBegin;
    }

    public LocalDate getBeginDateEnd() {
        return beginDateEnd;
    }

    public void setBeginDateEnd(LocalDate beginDateEnd) {
        this.beginDateEnd = beginDateEnd;
    }

    public LocalDate getEndDateEnd() {
        return endDateEnd;
    }

    public void setEndDateEnd(LocalDate endDateEnd) {
        this.endDateEnd = endDateEnd;
    }

    public LocalDateTime getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    public void setUpdateTimeBegin(LocalDateTime updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    public LocalDateTime getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(LocalDateTime createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public LocalDateTime getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(LocalDateTime updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "MemberFilterQuery{" +
                "cooperationId=" + cooperationId +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", hasParent=" + hasParent +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", beginDateBegin=" + beginDateBegin +
                ", endDateBegin=" + endDateBegin +
                ", beginDateEnd=" + beginDateEnd +
                ", endDateEnd=" + endDateEnd +
                ", updateTimeBegin=" + updateTimeBegin +
                ", createTimeBegin=" + createTimeBegin +
                ", updateTimeEnd=" + updateTimeEnd +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
