package team.oha.laboa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AgendaSummaryDto {
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;

    private LocalDateTime summaryTime;

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

    @Override
    public String toString() {
        return "AgendaSummaryDto{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", summaryTime=" + summaryTime +
                '}';
    }
}
