package team.oha.laboa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public class AgendaSummaryDo implements Serializable{
    private Integer summaryId;
    private Integer itemId;
    private Integer summarizerId;
    private String content;
    private LocalDateTime summaryTime;
    private LocalDateTime updateTime;

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSummarizerId() {
        return summarizerId;
    }

    public void setSummarizerId(Integer summarizerId) {
        this.summarizerId = summarizerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(LocalDateTime summaryTime) {
        this.summaryTime = summaryTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AgendaSummaryDo{" +
                "summaryId=" + summaryId +
                ", itemId=" + itemId +
                ", summarizerId=" + summarizerId +
                ", content='" + content + '\'' +
                ", summaryTime=" + summaryTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
