package team.oha.laboa.vo;

import team.oha.laboa.model.CooperationMemberDo;

import java.io.Serializable;

public class MemberRoleChangeVo implements Serializable {
    private Integer memberId;
    private CooperationMemberDo.CooperationRole role;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public CooperationMemberDo.CooperationRole getRole() {
        return role;
    }

    public void setRole(CooperationMemberDo.CooperationRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MemberRoleChangeVo{" +
                "memberId=" + memberId +
                ", role=" + role +
                '}';
    }
}
