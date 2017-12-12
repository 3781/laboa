package team.oha.laboa.dto;

import team.oha.laboa.model.AgendaDo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/12
 * @modified
 */
public class AgendaDetailDto implements Serializable {
    private Integer agendaId;
    private String ownerName;
    private String title;
    private AgendaDo.AgendaType type;
    private LocalDateTime nextTime;
    private Integer quantity;
    private AgendaDo.AgendaUnit unit;
    private String remark;
    private Boolean open;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private List<AgendaItemDetailDto> itemList;

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AgendaDo.AgendaType getType() {
        return type;
    }

    public void setType(AgendaDo.AgendaType type) {
        this.type = type;
    }

    public LocalDateTime getNextTime() {
        return nextTime;
    }

    public void setNextTime(LocalDateTime nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AgendaDo.AgendaUnit getUnit() {
        return unit;
    }

    public void setUnit(AgendaDo.AgendaUnit unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<AgendaItemDetailDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<AgendaItemDetailDto> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "AgendaDetailDto{" +
                "agendaId=" + agendaId +
                ", ownerName='" + ownerName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", nextTime=" + nextTime +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", remark='" + remark + '\'' +
                ", open=" + open +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", itemList=" + itemList +
                '}';
    }
}
