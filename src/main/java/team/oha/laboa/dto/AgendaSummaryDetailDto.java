package team.oha.laboa.dto;

import team.oha.laboa.model.AgendaSummaryDo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/12
 * @modified
 */
public class AgendaSummaryDetailDto implements Serializable {
    private Integer summaryId;
    private String username;
    private AgendaSummaryDo.SummaryStatus status;
    private String content;
    private LocalDateTime summaryTime;

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AgendaSummaryDo.SummaryStatus getStatus() {
        return status;
    }

    public void setStatus(AgendaSummaryDo.SummaryStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "AgendaSummaryDetailDto{" +
                "summaryId=" + summaryId +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", summaryTime=" + summaryTime +
                '}';
    }
}
