package model;

public class NodoPila {
    private Object data;
    private NodoPila next;

    public NodoPila(Object data) {
        this.data = data;
        this.next = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NodoPila getNext() {
        return next;
    }

    public void setNext(NodoPila next) {
        this.next = next;
    }
}
