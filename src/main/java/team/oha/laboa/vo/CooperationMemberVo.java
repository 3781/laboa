package team.oha.laboa.vo;

import team.oha.laboa.model.CooperationMemberDo;

public class CooperationMemberVo {
    private Integer userId;
    private Integer cooperationId;
    private CooperationMemberDo.CooperationRole role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public CooperationMemberDo.CooperationRole getRole() {
        return role;
    }

    public void setRole(CooperationMemberDo.CooperationRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CooperationMemberVo{" +
                "userId=" + userId +
                ", cooperationId=" + cooperationId +
                ", role=" + role +
                '}';
    }
}
