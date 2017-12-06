package team.oha.laboa.query.agenda;

import java.io.Serializable;
import java.time.LocalDate;

public class AgendaToDoQuery implements Serializable {
    private String username;
    private LocalDate targetDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String toString() {
        return "AgendaToDoQuery{" +
                "username='" + username + '\'' +
                ", targetDate=" + targetDate +
                '}';
    }
}
