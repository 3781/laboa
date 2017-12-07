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
public class CooperationAgendaParticipantDo implements Serializable{
    private Integer participantId;
    private Integer memberId;
    private Integer cooperationAgendaId;

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCooperationAgendaId() {
        return cooperationAgendaId;
    }

    public void setCooperationAgendaId(Integer cooperationAgendaId) {
        this.cooperationAgendaId = cooperationAgendaId;
    }

    @Override
    public String toString() {
        return "CooperationAgendaParticipantDo{" +
                "participantId=" + participantId +
                ", memberId=" + memberId +
                ", cooperationAgendaId=" + cooperationAgendaId +
                '}';
    }
}
