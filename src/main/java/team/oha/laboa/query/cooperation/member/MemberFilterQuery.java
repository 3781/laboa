package team.oha.laboa.query.cooperation.member;


import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberFilterQuery implements Serializable{
    private Integer memberId;
    private String username;
    private String name;
    private String employeeNumber;
    private String qq;
    private String phone;
    private String email;
    private LocalDateTime joinTimeBegin;
    private LocalDateTime joinTimeEnd;

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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getJoinTimeBegin() {
        return joinTimeBegin;
    }

    public void setJoinTimeBegin(LocalDateTime joinTimeBegin) {
        this.joinTimeBegin = joinTimeBegin;
    }

    public LocalDateTime getJoinTimeEnd() {
        return joinTimeEnd;
    }

    public void setJoinTimeEnd(LocalDateTime joinTimeEnd) {
        this.joinTimeEnd = joinTimeEnd;
    }

    @Override
    public String toString() {
        return "MemberFilterQuery{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", joinTimeBegin=" + joinTimeBegin +
                ", joinTimeEnd=" + joinTimeEnd +
                '}';
    }
}
