package view;

import controller.StoreController;
import model.NodoDoble;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ListaDeseosView extends JFrame {

    private final StoreController controller;
    private NodoDoble actual;

    private JLabel nombreLabel;
    private JLabel precioLabel;
    private JLabel tallaLabel;

    public ListaDeseosView(StoreController controller) {
        this.controller = controller;
        this.actual = controller.getListaDeseosNodo(); 

        setTitle("Lista de Deseos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        crearContenido();
        setVisible(true);
    }

    private void crearContenido() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(Color.WHITE);

        nombreLabel = new JLabel();
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        precioLabel = new JLabel();
        tallaLabel = new JLabel();

        infoPanel.add(nombreLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(precioLabel);
        infoPanel.add(tallaLabel);

        actualizarVista();

        JPanel btnPanel = new JPanel();
        JButton btnAnterior = new JButton("⬅ Anterior");
        JButton btnSiguiente = new JButton("Siguiente ➡");
        JButton btnAgregar = new JButton("Agregar al carrito");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        btnAnterior.addActionListener(e -> {
            actual = actual.getAnterior();
            actualizarVista();
        });

        btnSiguiente.addActionListener(e -> {
            actual = actual.getSiguiente();
            actualizarVista();
        });

        btnAgregar.addActionListener(e -> {
            controller.agregarAlCarrito(actual.getProducto());
            controller.eliminarDeListaDeseos(actual.getProducto());
            JOptionPane.showMessageDialog(this, "Agregado al carrito.");
            recargarVista();
        });

        btnEliminar.addActionListener(e -> {
            controller.eliminarDeListaDeseos(actual.getProducto());
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
            recargarVista();
        });

        btnVolver.addActionListener(e -> dispose());

        btnPanel.add(btnAnterior);
        btnPanel.add(btnSiguiente);
        btnPanel.add(btnAgregar);
        btnPanel.add(btnEliminar);
        btnPanel.add(btnVolver);

        add(infoPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void actualizarVista() {
        if (actual == null) {
            nombreLabel.setText("No hay productos en la lista.");
            precioLabel.setText("");
            tallaLabel.setText("");
        } else {
            Producto producto = actual.getProducto();
            nombreLabel.setText("👟 " + producto.getNombre());
            precioLabel.setText("💲 Precio: $" + producto.getPrecio());
            tallaLabel.setText("👞 Talla: " + producto.getTalla());
        }
    }

    private void recargarVista() {
        actual = controller.getListaDeseosNodo();
        if (actual == null) {
            JOptionPane.showMessageDialog(this, "Lista de deseos vacía.");
            dispose();
        } else {
            actualizarVista();
        }
    }
}
