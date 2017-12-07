package team.oha.laboa.dto;

import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.model.AgendaSummaryDo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AgendaDto implements Serializable {
    private Integer agendaId;
    private String username;
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

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "AgendaDto{" +
                "agendaId=" + agendaId +
                ", username='" + username + '\'' +
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
                '}';
    }
}
