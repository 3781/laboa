package team.oha.laboa.vo;

import team.oha.laboa.model.CooperationApplyDo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/8
 * @modified
 */
public class ApplyDealBatchVo implements Serializable {
    ArrayList<Integer> ids;
    CooperationApplyDo.ApplyStatus status;
    LocalDateTime updateTime;

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }

    public CooperationApplyDo.ApplyStatus getStatus() {
        return status;
    }

    public void setStatus(CooperationApplyDo.ApplyStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ApplyDealBatchVo{" +
                "ids=" + ids +
                ", status=" + status +
                ", updateTime=" + updateTime +
                '}';
    }
}
