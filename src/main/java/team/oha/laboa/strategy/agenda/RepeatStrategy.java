package team.oha.laboa.strategy.agenda;

import java.time.LocalDateTime;

public interface RepeatStrategy {
    LocalDateTime computeNextTime(LocalDateTime preTime, Integer quantity);
}
