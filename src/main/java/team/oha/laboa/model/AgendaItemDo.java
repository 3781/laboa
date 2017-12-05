package team.oha.laboa.model;

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
public class AgendaItemDo implements Serializable{
    private Integer agendaId;
    private Integer itemId;
    private LocalDateTime summaryTime;

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(LocalDateTime summaryTime) {
        this.summaryTime = summaryTime;
    }
}
