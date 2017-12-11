package team.oha.laboa.vo;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public class AgendaBatchVo {
    private Integer[] agendaIds;
    private Integer userId;
    private LocalDateTime nowTime;

    public Integer[] getAgendaIds() {
        return agendaIds;
    }

    public void setAgendaIds(Integer[] agendaIds) {
        this.agendaIds = agendaIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getNowTime() {
        return nowTime;
    }

    public void setNowTime(LocalDateTime nowTime) {
        this.nowTime = nowTime;
    }

    @Override
    public String toString() {
        return "AgendaBatchVo{" +
                "agendaIds=" + Arrays.toString(agendaIds) +
                ", userId=" + userId +
                ", nowTime=" + nowTime +
                '}';
    }
}
