package team.oha.laboa.vo;

import team.oha.laboa.model.UserDo;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/4
 * @modified
 */
public class ResetPasswordVo implements Serializable {
    private Integer[] ids;

    private List<UserDo.Role> allowRoles;

    public List<UserDo.Role> getAllowRoles() {
        return allowRoles;
    }

    public void setAllowRoles(List<UserDo.Role> allowRoles) {
        this.allowRoles = allowRoles;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
