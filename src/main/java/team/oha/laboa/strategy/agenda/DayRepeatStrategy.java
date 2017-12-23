package team.oha.laboa.strategy.agenda;

import java.time.LocalDateTime;

public class DayRepeatStrategy implements RepeatStrategy {
    @Override
    public LocalDateTime computeNextTime(LocalDateTime preTime, Integer quantity) {
        return preTime.plusDays(quantity);
    }
}
