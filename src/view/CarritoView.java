package view;

import controller.StoreController;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarritoView extends JFrame {
    private final StoreController controller;

    public CarritoView(StoreController controller) {
        this.controller = controller;

        setTitle("Carrito de Compras");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        crearEncabezado();
        crearContenido();
        setVisible(true);
    }

    private void crearEncabezado() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("🛒 Tu carrito");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(title, BorderLayout.WEST);

        JButton volverBtn = new JButton("Volver al catálogo");
        volverBtn.addActionListener(e -> dispose());
        topPanel.add(volverBtn, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void crearContenido() {
        List<Producto> carrito = controller.getCarrito();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        if (carrito.isEmpty()) {
            JLabel emptyLabel = new JLabel("Tu carrito está vacío.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(emptyLabel, BorderLayout.CENTER);
        } else {
            double subtotal = 0;
            for (Producto producto : carrito) {
                JPanel card = crearTarjetaCarrito(producto);
                contentPanel.add(card);
                contentPanel.add(Box.createVerticalStrut(10));
                subtotal += producto.getPrecio();
            }

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JLabel totalLabel = new JLabel("Subtotal: $" + subtotal);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
            bottomPanel.add(totalLabel, BorderLayout.WEST);

            JButton finalizarBtn = new JButton("Finalizar compra");
            finalizarBtn.setFont(new Font("Arial", Font.BOLD, 14));
            finalizarBtn.addActionListener(e -> {
                controller.finalizarCompra();
                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
                dispose();
                new CarritoView(controller);
            });

            bottomPanel.add(finalizarBtn, BorderLayout.EAST);
            add(bottomPanel, BorderLayout.SOUTH);
        }
    }

    private JPanel crearTarjetaCarrito(Producto producto) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(producto.getNombre());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel priceLabel = new JLabel("$" + producto.getPrecio());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel tallaLabel = new JLabel("Talla: " + producto.getTalla());
        tallaLabel.setFont(new Font("Arial", Font.PLAIN, 13));


        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.addActionListener(e -> {
            controller.eliminarDelCarrito(producto);
            dispose();
            new CarritoView(controller);
        });

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(tallaLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(eliminarBtn);

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }
}
