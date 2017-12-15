package team.oha.laboa.dto;

import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.model.AgendaSummaryDo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class AgendaDto implements Serializable {
    private Integer agendaId;
    private String ownerName;
    private String title;
    private String remark;
    private LocalDateTime nextTime;
    private Integer quantity;
    private AgendaDo.AgendaType type;
    private AgendaDo.AgendaUnit unit;
    private AgendaSummaryDo.SummaryStatus status;
    private Boolean open;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Integer cooperationId;
    private List<ParticipantAvailableDto> memberList;

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getNextTime() {
        return nextTime;
    }

    public void setNextTime(LocalDateTime nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AgendaDo.AgendaType getType() {
        return type;
    }

    public void setType(AgendaDo.AgendaType type) {
        this.type = type;
    }

    public AgendaDo.AgendaUnit getUnit() {
        return unit;
    }

    public void setUnit(AgendaDo.AgendaUnit unit) {
        this.unit = unit;
    }

    public AgendaSummaryDo.SummaryStatus getStatus() {
        return status;
    }

    public void setStatus(AgendaSummaryDo.SummaryStatus status) {
        this.status = status;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
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

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public List<ParticipantAvailableDto> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ParticipantAvailableDto> memberList) {
        this.memberList = memberList;
    }

    @Override
    public String toString() {
        return "AgendaDto{" +
                "agendaId=" + agendaId +
                ", ownerName='" + ownerName + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", nextTime=" + nextTime +
                ", quantity=" + quantity +
                ", type=" + type +
                ", unit=" + unit +
                ", status=" + status +
                ", open=" + open +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", cooperationId=" + cooperationId +
                ", memberList=" + memberList +
                '}';
    }
}
