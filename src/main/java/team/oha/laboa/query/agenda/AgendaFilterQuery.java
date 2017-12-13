package team.oha.laboa.query.agenda;


import team.oha.laboa.model.AgendaDo;
import team.oha.laboa.model.AgendaSummaryDo;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AgendaFilterQuery {
    private Integer agendaId;
    private Integer ownerId;
    private Integer summarizerId;
    private Integer cooperationId;
    private String ownerName;
    private String title;
    private String remark;
    private Integer quantity;
    private LocalDateTime nextTimeBegin;
    private LocalDateTime nextTimeEnd;
    private AgendaDo.AgendaType type;
    private AgendaDo.AgendaUnit[] units;
    private AgendaSummaryDo.SummaryStatus[] statuses;
    private LocalDateTime updateTimeBegin;
    private LocalDateTime updateTimeEnd;
    private LocalDateTime createTimeBegin;
    private LocalDateTime createTimeEnd;
    private Boolean open;

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getSummarizerId() {
        return summarizerId;
    }

    public void setSummarizerId(Integer summarizerId) {
        this.summarizerId = summarizerId;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getNextTimeBegin() {
        return nextTimeBegin;
    }

    public void setNextTimeBegin(LocalDateTime nextTimeBegin) {
        this.nextTimeBegin = nextTimeBegin;
    }

    public LocalDateTime getNextTimeEnd() {
        return nextTimeEnd;
    }

    public void setNextTimeEnd(LocalDateTime nextTimeEnd) {
        this.nextTimeEnd = nextTimeEnd;
    }

    public AgendaDo.AgendaType getType() {
        return type;
    }

    public void setType(AgendaDo.AgendaType type) {
        this.type = type;
    }

    public AgendaDo.AgendaUnit[] getUnits() {
        return units;
    }

    public void setUnits(AgendaDo.AgendaUnit[] units) {
        this.units = units;
    }

    public AgendaSummaryDo.SummaryStatus[] getStatuses() {
        return statuses;
    }

    public void setStatuses(AgendaSummaryDo.SummaryStatus[] statuses) {
        this.statuses = statuses;
    }

    public LocalDateTime getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    public void setUpdateTimeBegin(LocalDateTime updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    public LocalDateTime getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(LocalDateTime updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public LocalDateTime getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(LocalDateTime createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "AgendaFilterQuery{" +
                "agendaId=" + agendaId +
                ", ownerId=" + ownerId +
                ", summarizerId=" + summarizerId +
                ", cooperationId=" + cooperationId +
                ", ownerName='" + ownerName + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", quantity=" + quantity +
                ", nextTimeBegin=" + nextTimeBegin +
                ", nextTimeEnd=" + nextTimeEnd +
                ", type=" + type +
                ", units=" + Arrays.toString(units) +
                ", statuses=" + Arrays.toString(statuses) +
                ", updateTimeBegin=" + updateTimeBegin +
                ", updateTimeEnd=" + updateTimeEnd +
                ", createTimeBegin=" + createTimeBegin +
                ", createTimeEnd=" + createTimeEnd +
                ", open=" + open +
                '}';
    }
}
