package model;

public class PilaLista {
    private NodoPila top;

    public PilaLista() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Object element) {
        NodoPila newNode = new NodoPila(element);
        newNode.setNext(top);
        top = newNode;
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        Object aux = top.getData();
        top = top.getNext();
        return aux;
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return top.getData();
    }

    public void clear() {
        top = null;
    }
}
