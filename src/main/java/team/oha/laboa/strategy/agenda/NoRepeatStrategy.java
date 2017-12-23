package team.oha.laboa.strategy.agenda;

import java.time.LocalDateTime;

public class NoRepeatStrategy implements RepeatStrategy {
    @Override
    public LocalDateTime computeNextTime(LocalDateTime preTime, Integer quantity) {
        return null;
    }
}
