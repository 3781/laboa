package team.oha.laboa.query;

import java.io.Serializable;

public class OrderQuery<T extends Enum> implements Serializable{

    private T field;
    private Order order;

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static enum Order {
        asc,
        desc
    }
}
