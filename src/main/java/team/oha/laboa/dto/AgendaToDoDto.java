package team.oha.laboa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import team.oha.laboa.model.AgendaSummaryDo;

import java.time.LocalDateTime;

public class AgendaToDoDto {
    private Integer agendaId;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;
    private LocalDateTime summaryTime;
    private Integer summaryId;
    private AgendaSummaryDo.SummaryStatus status;
    private String content;

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(LocalDateTime summaryTime) {
        this.summaryTime = summaryTime;
    }

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
}
