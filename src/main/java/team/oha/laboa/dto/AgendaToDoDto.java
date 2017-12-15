package team.oha.laboa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AgendaToDoDto {
    private Integer agendaId;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;
    private LocalDateTime summaryTime;
    private AgendaSummaryDto agendaSummary;

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

    public AgendaSummaryDto getAgendaSummary() {
        return agendaSummary;
    }

    public void setAgendaSummary(AgendaSummaryDto agendaSummary) {
        this.agendaSummary = agendaSummary;
    }

    @Override
    public String toString() {
        return "AgendaToDoDto{" +
                "agendaId=" + agendaId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", summaryTime=" + summaryTime +
                ", agendaSummary=" + agendaSummary +
                '}';
    }
}
