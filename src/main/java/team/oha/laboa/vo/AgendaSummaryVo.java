package team.oha.laboa.vo;

import team.oha.laboa.model.AgendaSummaryDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public class AgendaSummaryVo {
    private Integer summaryId;
    private String content;
    private AgendaSummaryDo.SummaryStatus status;

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AgendaSummaryDo.SummaryStatus getStatus() {
        return status;
    }

    public void setStatus(AgendaSummaryDo.SummaryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AgendaSummaryVo{" +
                "summaryId=" + summaryId +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
