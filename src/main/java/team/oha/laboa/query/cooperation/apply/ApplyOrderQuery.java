package team.oha.laboa.query.cooperation.apply;

import team.oha.laboa.query.OrderQuery;

public class ApplyOrderQuery extends OrderQuery<ApplyOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        applyId,
        username,
        name,
        employeeNumber,
        qq,
        phone,
        email,
        applyTime,
        updateTime,
        status
    }
}
