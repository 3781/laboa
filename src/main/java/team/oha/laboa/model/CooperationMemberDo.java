package team.oha.laboa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public class CooperationMemberDo implements Serializable{
    private Integer memberId;
    private Integer cooperationId;
    private Integer userId;
    private CooperationRole role;
    private LocalDateTime joinTime;

    public static enum CooperationRole{
        owner, manager, member
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CooperationRole getRole() {
        return role;
    }

    public void setRole(CooperationRole role) {
        this.role = role;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "CooperationMemberDo{" +
                "memberId=" + memberId +
                ", cooperationId=" + cooperationId +
                ", userId=" + userId +
                ", role=" + role +
                ", joinTime=" + joinTime +
                '}';
    }
}
