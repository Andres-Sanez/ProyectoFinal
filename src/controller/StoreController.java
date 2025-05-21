package controller;

import model.Producto;

import java.util.*;
import model.ListaEnlazada;

public class StoreController {
    private final List<Producto> catalogo;
    private final Stack<Producto> historial;
    private final Queue<Producto> listaDeseos;
    private ListaEnlazada carrito = new ListaEnlazada();
    private final Map<String, String> usuariosRegistrados;

    public StoreController() {
        catalogo = new ArrayList<>();
        historial = new Stack<>();
        listaDeseos = new LinkedList<>();
        carrito = new ListaEnlazada();
        usuariosRegistrados = new HashMap<>();

        cargarProductos();
    }

    private void cargarProductos() {
    catalogo.add(new Producto("Air Jordan 1 Retro High", 180, "/imagenes/jordan1.jpg"));
    catalogo.add(new Producto("Air Jordan 4 Black Canvas", 210, "/imagenes/jordan4.jpg"));
    catalogo.add(new Producto("Air Jordan 11 Concord", 220, "/imagenes/jordan11.jpeg"));
    catalogo.add(new Producto("Air Jordan 3 White Cement", 200, "/imagenes/jordan3.jpg"));
    catalogo.add(new Producto("Air Jordan 5 Raging Bull", 190, "/imagenes/jordan5.jpg"));
    catalogo.add(new Producto("Air Jordan 6 Infrared", 200, "/imagenes/jordan6.jpg"));
    catalogo.add(new Producto("Air Jordan 12 Flu Game", 190, "/imagenes/jordan12.jpg"));
    catalogo.add(new Producto("Air Jordan 13 Bred", 195, "/imagenes/jordan13.jpg"));
    catalogo.add(new Producto("Air Jordan 1 Mid", 130, "/imagenes/jordan1mid.jpeg"));
    catalogo.add(new Producto("Air Jordan 7 Bordeaux", 175, "/imagenes/jordan7.jpg"));
}


    // ========== Métodos de Autenticación ==========
    public boolean login(String email, String password) {
        return usuariosRegistrados.containsKey(email) && usuariosRegistrados.get(email).equals(password);
    }

    public boolean register(String email, String password) {
        if (usuariosRegistrados.containsKey(email)) return false;
        usuariosRegistrados.put(email, password);
        return true;
    }

    // ========== Getters ==========
    public List<Producto> getProductos() {
        return catalogo;
    }

    

    public Queue<Producto> getListaDeseos() {
        return listaDeseos;
    }

    public Stack<Producto> getHistorialCompras() {
        return historial;
    }

    // ========== Carrito ==========
    public void agregarAlCarrito(Producto producto) {
    carrito.agregar(producto);
}
    
    public void finalizarCompra() {
    for (Producto p : carrito.toList()) {
        historial.push(p);
    }
    carrito.vaciar();
}


public void eliminarDelCarrito(Producto producto) {
    carrito.eliminar(producto);
}

public void vaciarCarrito() {
    carrito.vaciar();
}

public List<Producto> getCarrito() {
    return carrito.toList();
}

    // ========== Lista de Deseos ==========
    public void agregarAListaDeseos(Producto producto) {
        if (!listaDeseos.contains(producto)) {
            listaDeseos.add(producto);
        }
    }

    public void limpiarListaDeseos() {
        listaDeseos.clear();
    }

    // ========== Historial  ==========

    

public void eliminarDeListaDeseos(Producto producto) {
    listaDeseos.remove(producto);
}

    public void comprar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}