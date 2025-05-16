package view;

import controller.StoreController;

import javax.swing.*;
import java.awt.*;

public class RegistroView extends JFrame {
    private final StoreController controller;

    public RegistroView(StoreController controller) {
        this.controller = controller;

        setTitle("Registro - Jordan Store");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Sign up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(150, 50, 200, 30);
        add(titleLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(100, 120, 200, 40);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(emailField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 180, 200, 40);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 240, 200, 45);
        registerButton.setBackground(new Color(255, 70, 70));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(100, 300, 200, 40);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.GRAY);
        backButton.setBorder(null);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(backButton);

        registerButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor rellena todas las casillas.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (controller.register(email, password)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado correctamente! Ahora puedes iniciar sesion.");
                dispose();
                new LoginView(controller);
            } else {
                JOptionPane.showMessageDialog(this, "Este usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new LoginView(controller);
        });
    }
}
