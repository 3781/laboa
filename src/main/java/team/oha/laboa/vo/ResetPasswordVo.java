package team.oha.laboa.vo;

import team.oha.laboa.model.HashInfo;
import team.oha.laboa.model.UserDo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/4
 * @modified
 */
public class ResetPasswordVo implements Serializable, HashInfo {
    private Integer[] ids;
    private String salt;
    private String password;

    private UserDo.Role[] allowRoles;

    public UserDo.Role[] getAllowRoles() {
        return allowRoles;
    }

    public void setAllowRoles(UserDo.Role[] allowRoles) {
        this.allowRoles = allowRoles;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordVo{" +
                "ids=" + Arrays.toString(ids) +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
