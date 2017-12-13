package team.oha.laboa.query.agenda;

import team.oha.laboa.query.OrderQuery;

public class AgendaOrderQuery extends OrderQuery<AgendaOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        agendaId,
        ownerName,
        title,
        nextTime,
        quantity,
        unit,
        open,
        updateTime,
        createTime
    }
}
