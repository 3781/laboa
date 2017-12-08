package team.oha.laboa.query.cooperation;

import team.oha.laboa.query.OrderQuery;

public class CooperationOrderQuery extends OrderQuery<CooperationOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        cooperationId,
        hasParent,
        name,
        ownername,
        beginDate,
        endDate,
        updateTime,
        createTime,
    }
}
