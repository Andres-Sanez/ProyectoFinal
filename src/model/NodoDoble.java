package model;

public class NodoDoble {
    private Producto producto;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Producto producto) {
        this.producto = producto;
        this.siguiente = this;
        this.anterior = this;
    }

    public Producto getProducto() {
        return producto;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}
