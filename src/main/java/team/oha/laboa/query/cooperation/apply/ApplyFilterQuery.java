package team.oha.laboa.query.cooperation.apply;


import team.oha.laboa.model.CooperationApplyDo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ApplyFilterQuery implements Serializable{
    private Integer applyId;
    private Integer cooperationId;
    private String username;
    private String name;
    private String employeeNumber;
    private String qq;
    private String phone;
    private String email;
    private CooperationApplyDo.ApplyStatus[] statuses;
    private LocalDateTime updateTimeBegin;
    private LocalDateTime updateTimeEnd;
    private LocalDateTime applyTimeBegin;
    private LocalDateTime applyTimeEnd;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
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

    public CooperationApplyDo.ApplyStatus[] getStatuses() {
        return statuses;
    }

    public void setStatuses(CooperationApplyDo.ApplyStatus[] statuses) {
        this.statuses = statuses;
    }

    public LocalDateTime getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    public void setUpdateTimeBegin(LocalDateTime updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    public LocalDateTime getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(LocalDateTime updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public LocalDateTime getApplyTimeBegin() {
        return applyTimeBegin;
    }

    public void setApplyTimeBegin(LocalDateTime applyTimeBegin) {
        this.applyTimeBegin = applyTimeBegin;
    }

    public LocalDateTime getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(LocalDateTime applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    @Override
    public String toString() {
        return "ApplyFilterQuery{" +
                "applyId=" + applyId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", statuses=" + Arrays.toString(statuses) +
                ", updateTimeBegin=" + updateTimeBegin +
                ", updateTimeEnd=" + updateTimeEnd +
                ", applyTimeBegin=" + applyTimeBegin +
                ", applyTimeEnd=" + applyTimeEnd +
                '}';
    }
}
