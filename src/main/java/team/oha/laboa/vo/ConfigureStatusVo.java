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
public class ConfigureStatusVo implements Serializable {
    private Integer[] ids;
    private UserDo.Status status;

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

    public UserDo.Status getStatus() {
        return status;
    }

    public void setStatus(UserDo.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConfigureStatusVo{" +
                "ids=" + Arrays.toString(ids) +
                ", status=" + status +
                '}';
    }
}
