package team.oha.laboa.dto;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
public class ApiDto implements Serializable{
    boolean success;
    Object info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ApiDto{" +
                "success=" + success +
                ", info=" + info +
                '}';
    }
}
