package team.oha.laboa.vo;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/11
 * @modified
 */
public class CooperationAgendaParticipantVo implements Serializable{
    private Integer cooperationAgendaId;
    private Integer memberId;

    public Integer getCooperationAgendaId() {
        return cooperationAgendaId;
    }

    public void setCooperationAgendaId(Integer cooperationAgendaId) {
        this.cooperationAgendaId = cooperationAgendaId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "CooperationAgendaParticipantVo{" +
                "cooperationAgendaId=" + cooperationAgendaId +
                ", memberId=" + memberId +
                '}';
    }
}
