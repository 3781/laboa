package team.oha.laboa.query.cooperation.member;


import java.io.Serializable;

public class MemberAvailableQuery implements Serializable{
    private Integer parentId;
    private String username;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MemberAvailableQuery{" +
                "parentId=" + parentId +
                ", username='" + username + '\'' +
                '}';
    }
}
