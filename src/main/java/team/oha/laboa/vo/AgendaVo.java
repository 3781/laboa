package team.oha.laboa.vo;

import team.oha.laboa.model.AgendaDo;

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
public class AgendaVo implements Serializable {
    private Integer agendaId;
    private String title;
    private LocalDateTime nextTime;
    private Integer quantity;
    private AgendaDo.AgendaUnit unit;
    private String remark;
    private Boolean open;


    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public AgendaDo.AgendaUnit getUnit() {
        return unit;
    }

    public void setUnit(AgendaDo.AgendaUnit unit) {
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

    @Override
    public String toString() {
        return "AgendaVo{" +
                "agendaId=" + agendaId +
                ", title='" + title + '\'' +
                ", nextTime=" + nextTime +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", remark='" + remark + '\'' +
                ", open=" + open +
                '}';
    }
}
