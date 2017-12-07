package team.oha.laboa.model;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public class CooperationAgendaDo implements Serializable{
    private Integer cooperationAgendaId;
    private Integer cooperationId;
    private Integer agendaId;

    public Integer getCooperationAgendaId() {
        return cooperationAgendaId;
    }

    public void setCooperationAgendaId(Integer cooperationAgendaId) {
        this.cooperationAgendaId = cooperationAgendaId;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    @Override
    public String toString() {
        return "CooperationAgendaDo{" +
                "cooperationAgendaId=" + cooperationAgendaId +
                ", cooperationId=" + cooperationId +
                ", agendaId=" + agendaId +
                '}';
    }
}
