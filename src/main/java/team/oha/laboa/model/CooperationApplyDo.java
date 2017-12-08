package team.oha.laboa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public class CooperationApplyDo implements Serializable{
    private Integer applyId;
    private Integer applicantId;
    private Integer cooperationId;
    private ApplyStatus status;
    private String remark;
    private LocalDateTime updateTime;
    private LocalDateTime applyTime;

    public static enum ApplyStatus{
        todo, pass, reject, never
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public ApplyStatus getStatus() {
        return status;
    }

    public void setStatus(ApplyStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        return "CooperationApplyDo{" +
                "applyId=" + applyId +
                ", applicantId=" + applicantId +
                ", cooperationId=" + cooperationId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                ", applyTime=" + applyTime +
                '}';
    }
}
