package controller;

import model.Producto;

import java.util.*;

public class StoreController {
    private final List<Producto> catalogo;
    private final Stack<Producto> historial;
    private final Queue<Producto> listaDeseos;
    private final List<Producto> carrito;
    private final Map<String, String> usuariosRegistrados;

    
