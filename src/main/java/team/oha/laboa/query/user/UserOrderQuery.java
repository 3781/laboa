package team.oha.laboa.query.user;

import team.oha.laboa.query.OrderQuery;

public class UserOrderQuery extends OrderQuery<UserOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        userId,
        username,
        role,
        status,
        loginTime,
        registerTime,
        name,
        employeeNumbner,
        qq,
        email,
        phone
    }
}
