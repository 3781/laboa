package team.oha.laboa.dto;

import team.oha.laboa.model.AgendaSummaryDo;

import java.io.Serializable;

public class AgendaSummaryDto implements Serializable{
    private Integer summaryId;
    private AgendaSummaryDo.SummaryStatus status;
    private String content;

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
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

    @Override
    public String toString() {
        return "AgendaSummaryDto{" +
                "summaryId=" + summaryId +
                ", status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}
