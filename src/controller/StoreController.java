package controller;

import model.Producto;

import java.util.*;

public class StoreController {
    private final List<Producto> catalogo;
    private final Stack<Producto> historial;
    private final Queue<Producto> listaDeseos;
    private final List<Producto> carrito;
    private final Map<String, String> usuariosRegistrados;

public StoreController() {
        catalogo = new ArrayList<>();
        historial = new Stack<>();
        listaDeseos = new LinkedList<>();
        carrito = new ArrayList<>();
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
    
    public List<Producto> getCarrito() {
        return carrito;
    }

    // ========== Carrito ==========
    public void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    public void vaciarCarrito() {
        carrito.clear();
    }

    public void finalizarCompra() {
        for (Producto p : carrito) {
            historial.push(p);
        }
        carrito.clear();
    }

    

    

    
