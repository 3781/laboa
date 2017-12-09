package team.oha.laboa.vo;

import java.time.LocalDate;

public class CooperationVo {
    private Integer cooperationId;
    private Integer parentId;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String remark;
    private Boolean invite;

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getInvite() {
        return invite;
    }

    public void setInvite(Boolean invite) {
        this.invite = invite;
    }

    @Override
    public String toString() {
        return "CooperationVo{" +
                "cooperationId=" + cooperationId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", remark='" + remark + '\'' +
                ", invite=" + invite +
                '}';
    }
}
