package team.oha.laboa.model;

import team.oha.laboa.util.password.SaltPasswordInfo;

import java.time.LocalDateTime;

/**
 * <p>用户</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
public class UserDo implements SaltPasswordInfo {
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private Role role;
    private Status status;
    private LocalDateTime loginTime;
    private LocalDateTime registerTime;

    public enum Role{
        superAdmin, admin, enduser
    }

    public enum Status{
        locked, normal, disable
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "UserDo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", loginTime=" + loginTime +
                ", registerTime=" + registerTime +
                '}';
    }
}
