package view;

import controller.StoreController;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class HistorialView extends JFrame {
    private final StoreController controller;

    public HistorialView(StoreController controller) {
        this.controller = controller;

        setTitle("Historial de Compras - Jordan Store");
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

        JLabel title = new JLabel("Historial de Compras");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(title, BorderLayout.WEST);

        JButton volverBtn = new JButton("Volver al catálogo");
        volverBtn.addActionListener(e -> dispose());
        topPanel.add(volverBtn, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void crearContenido() {
        Stack<Producto> historial = controller.getHistorialCompras();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        if (historial.isEmpty()) {
            JLabel emptyLabel = new JLabel("Aún no has realizado compras.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(emptyLabel, BorderLayout.CENTER);
        } else {
            for (Producto producto : historial) {
                JPanel card = crearTarjeta(producto);
                contentPanel.add(card);
                contentPanel.add(Box.createVerticalStrut(10));
            }
        }
    }

    private JPanel crearTarjeta(Producto producto) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ImageIcon icon = new ImageIcon(producto.getRutaImagen());
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(imageLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(producto.getNombre());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("$" + producto.getPrecio());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(priceLabel);
        
        JLabel tallaLabel = new JLabel("Talla: " + producto.getTalla());
        tallaLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        infoPanel.add(tallaLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }
}
