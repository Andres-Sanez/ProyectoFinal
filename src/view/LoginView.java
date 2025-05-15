package view;

import controller.StoreController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private StoreController controller;

    public LoginView(StoreController controller) {
        this.controller = controller;
        setTitle("Login - Jordan Store");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        initComponents();
        
        setVisible(true);
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 240, 200, 45);
        loginButton.setBackground(new Color(255, 70, 70));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        add(loginButton);

        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(100, 300, 200, 40);
        signupButton.setContentAreaFilled(false);
        signupButton.setForeground(Color.GRAY);
        signupButton.setBorder(null);
        signupButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(signupButton);

        // Login logic
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
                } else {
                    boolean success = controller.login(email, password);
                    if (success) {
                        dispose();
                        new CatalogoView(controller);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                    }
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistroView(controller);
            }
        });
    }
}
