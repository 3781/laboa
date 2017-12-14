package team.oha.laboa.query.cooperation.participant;


import java.io.Serializable;

public class ParticipantAvailableQuery implements Serializable{
    private Integer cooperationId;
    private String username;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ParticipantAvailableQuery{" +
                "cooperationId=" + cooperationId +
                ", username='" + username + '\'' +
                '}';
    }
}
