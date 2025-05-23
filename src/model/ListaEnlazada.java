package model;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazada {
    private Nodo cabeza;

    public void agregar(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void eliminar(Producto producto) {
        if (cabeza == null) return;

        if (cabeza.getProducto().equals(producto)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getProducto().equals(producto)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public List<Producto> toList() {
        List<Producto> lista = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            lista.add(actual.getProducto());
            actual = actual.getSiguiente();
        }
        return lista;
    }

    public void vaciar() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}