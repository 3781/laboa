package team.oha.laboa.query.cooperation.member;

import team.oha.laboa.query.OrderQuery;

public class MemberOrderQuery extends OrderQuery<MemberOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        memberId,
        username,
        name,
        employeeNumber,
        qq,
        phone,
        email,
        joinTime,
    }
}
