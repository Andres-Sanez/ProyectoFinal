package view;

import controller.StoreController;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogoView extends JFrame {
    private final StoreController controller;

    public CatalogoView(StoreController controller) {
        this.controller = controller;

        setTitle("Catálogo - Jordan Store");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        crearEncabezado();
        crearCatalogo();
        crearBarraNavegacion();

        setVisible(true);
    }

    private void crearEncabezado() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Air Jordan");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        topPanel.add(title, BorderLayout.WEST);

        JTextField searchField = new JTextField("Buscar...");
        searchField.setPreferredSize(new Dimension(200, 30));
        topPanel.add(searchField, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void crearCatalogo() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Producto> productos = controller.getProductos();

        for (Producto producto : productos) {
            gridPanel.add(crearTarjetaProducto(producto));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaProducto(Producto producto) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(250, 300));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(producto.getRutaImagen()));
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(imageLabel);
        } catch (NullPointerException e) {
            JLabel imageLabel = new JLabel("Imagen no encontrada");
            imageLabel.setForeground(Color.RED);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(imageLabel);
        }

        JLabel nameLabel = new JLabel(producto.getNombre());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);

        JLabel priceLabel = new JLabel("$" + producto.getPrecio());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);
        
        JLabel tallaLabel = new JLabel("Talla: " + producto.getTalla());
        tallaLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        tallaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(tallaLabel);

        JButton addToCart = new JButton("Agregar al carrito");
        addToCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCart.setBackground(new Color(255, 70, 70));
        addToCart.setForeground(Color.WHITE);
        addToCart.setFont(new Font("Arial", Font.BOLD, 14));
        addToCart.setFocusPainted(false);
        addToCart.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        addToCart.addActionListener(e -> {
            controller.agregarAlCarrito(producto);
            JOptionPane.showMessageDialog(this, "Producto agregado al carrito.");
        });

        JButton addToWishlist = new JButton("Agregar a deseos");
        addToWishlist.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToWishlist.setFont(new Font("Arial", Font.PLAIN, 13));
        addToWishlist.setFocusPainted(false);
        addToWishlist.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        addToWishlist.addActionListener(e -> {
            controller.agregarAListaDeseos(producto);
            JOptionPane.showMessageDialog(this, "Producto agregado a la lista de deseos.");
        });

        card.add(Box.createVerticalStrut(10));
        card.add(addToCart);
        card.add(Box.createVerticalStrut(5));
        card.add(addToWishlist);

        return card;
    }

    private void crearBarraNavegacion() {
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton verCarrito = new JButton("🛒 Ver Carrito");
        verCarrito.setFont(new Font("Arial", Font.BOLD, 14));
        verCarrito.addActionListener(e -> new CarritoView(controller));

        JButton verDeseos = new JButton("❤️ Ver Lista de Deseos");
        verDeseos.setFont(new Font("Arial", Font.BOLD, 14));
        verDeseos.addActionListener(e -> new ListaDeseosView(controller));

        JButton verHistorial = new JButton("📦 Historial de Compras");
        verHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        verHistorial.addActionListener(e -> new HistorialView(controller));

        navPanel.add(verCarrito);
        navPanel.add(verDeseos);
        navPanel.add(verHistorial);

        add(navPanel, BorderLayout.SOUTH);
    }
}
