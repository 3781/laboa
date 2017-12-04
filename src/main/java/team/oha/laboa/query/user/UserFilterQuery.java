package team.oha.laboa.query.user;


import org.springframework.format.annotation.DateTimeFormat;
import team.oha.laboa.model.UserDo;

import java.time.LocalDateTime;

public class UserFilterQuery {
    private Integer userId;
    private String username;
    private UserDo.Role[] roles;
    private UserDo.Status[] statuses;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTimeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTimeEnd;
    private String name;
    private String employeeNumber;
    private String qq;
    private String email;
    private String phone;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDo.Role[] getRoles() {
        return roles;
    }

    public void setRoles(UserDo.Role[] roles) {
        this.roles = roles;
    }

    public UserDo.Status[] getStatuses() {
        return statuses;
    }

    public void setStatuses(UserDo.Status[] statuses) {
        this.statuses = statuses;
    }

    public LocalDateTime getLoginTimeBegin() {
        return loginTimeBegin;
    }

    public void setLoginTimeBegin(LocalDateTime loginTimeBegin) {
        this.loginTimeBegin = loginTimeBegin;
    }

    public LocalDateTime getLoginTimeEnd() {
        return loginTimeEnd;
    }

    public void setLoginTimeEnd(LocalDateTime loginTimeEnd) {
        this.loginTimeEnd = loginTimeEnd;
    }

    public LocalDateTime getRegisterTimeBegin() {
        return registerTimeBegin;
    }

    public void setRegisterTimeBegin(LocalDateTime registerTimeBegin) {
        this.registerTimeBegin = registerTimeBegin;
    }

    public LocalDateTime getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(LocalDateTime registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
