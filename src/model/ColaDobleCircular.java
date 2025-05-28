package model;

import java.util.ArrayList;
import java.util.List;

public class ColaDobleCircular {
    private NodoDoble frente;
    private NodoDoble fin;

    public void encolar(Producto producto) {
        NodoDoble nuevo = new NodoDoble(producto);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            nuevo.setAnterior(fin);
            nuevo.setSiguiente(frente);
            fin.setSiguiente(nuevo);
            frente.setAnterior(nuevo);
            fin = nuevo;
        }
    }

    public void desencolar() {
        if (frente == null) return;

        if (frente == fin) {
            frente = null;
            fin = null;
        } else {
            frente = frente.getSiguiente();
            frente.setAnterior(fin);
            fin.setSiguiente(frente);
        }
    }

    public void eliminar(Producto producto) {
        if (frente == null) return;

        NodoDoble actual = frente;
        do {
            if (actual.getProducto().equals(producto)) {
                if (actual == frente && actual == fin) {
                    frente = null;
                    fin = null;
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());

                    if (actual == frente) frente = actual.getSiguiente();
                    if (actual == fin) fin = actual.getAnterior();
                }
                return;
            }
            actual = actual.getSiguiente();
        } while (actual != frente);
    }

    public List<Producto> toList() {
        List<Producto> lista = new ArrayList<>();
        if (frente == null) return lista;

        NodoDoble actual = frente;
        do {
            lista.add(actual.getProducto());
            actual = actual.getSiguiente();
        } while (actual != frente);

        return lista;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public NodoDoble getFrente() {
        return frente;
    }
}

