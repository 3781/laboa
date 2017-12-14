package team.oha.laboa.dto;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
public class ParticipantAvailableDto implements Serializable {
    private Integer memberId;
    private String username;
    private String name;
    private String employeeNumber;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "ParticipantAvailableDto{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                '}';
    }
}
