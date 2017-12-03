package team.oha.laboa.dto;

import team.oha.laboa.model.UserDo;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/28
 * @modified
 */
public class LoginDto {
    private String username;
    private UserDo.Role role;
    private LocalDateTime lastLoginTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDo.Role getRole() {
        return role;
    }

    public void setRole(UserDo.Role role) {
        this.role = role;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", role=" + role +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
