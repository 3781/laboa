package team.oha.laboa.dto;

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
public class AgendaItemDetailDto implements Serializable {
    private Integer itemId;
    private LocalDateTime summaryTime;
    private List<AgendaSummaryDetailDto> summaryList;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(LocalDateTime summaryTime) {
        this.summaryTime = summaryTime;
    }

    public List<AgendaSummaryDetailDto> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<AgendaSummaryDetailDto> summaryList) {
        this.summaryList = summaryList;
    }

    @Override
    public String toString() {
        return "AgendaItemDetailDto{" +
                "itemId=" + itemId +
                ", summaryTime=" + summaryTime +
                ", summaryList=" + summaryList +
                '}';
    }
}
