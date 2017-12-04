package team.oha.laboa.vo;

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
public class ConfigureRoleVo implements Serializable{
    private Integer[] ids;
    private UserDo.Role role;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public UserDo.Role getRole() {
        return role;
    }

    public void setRole(UserDo.Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ConfigureRoleVo{" +
                "ids=" + Arrays.toString(ids) +
                ", role=" + role +
                '}';
    }
}
