package team.oha.laboa.model;

import team.oha.laboa.strategy.agenda.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public class AgendaDo implements Serializable{
    private Integer agendaId;
    private Integer ownerId;
    private String title;
    private AgendaType type;
    private LocalDateTime nextTime;
    private Integer quantity;
    private AgendaUnit unit;
    private String remark;
    private Boolean open;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;

    public static enum AgendaUnit{
        once(new NoRepeatStrategy()),
        month(new MonthRepeatStrategy()),
        week(new WeekRepeatStrategy()),
        day(new DayRepeatStrategy());


        private RepeatStrategy strategy;

        AgendaUnit(RepeatStrategy strategy) {
            this.strategy = strategy;
        }

        public RepeatStrategy getStrategy() {
            return strategy;
        }
    }

    public static enum  AgendaType{
        cooperation, personal
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AgendaType getType() {
        return type;
    }

    public void setType(AgendaType type) {
        this.type = type;
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

    public AgendaUnit getUnit() {
        return unit;
    }

    public void setUnit(AgendaUnit unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
