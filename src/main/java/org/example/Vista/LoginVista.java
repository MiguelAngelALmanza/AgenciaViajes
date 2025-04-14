package org.example.Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginVista extends JFrame {

    private JTextField campoCorreo;
    private JPasswordField campoContraseña;
    private JButton botonIngresar;
    private JButton botonCrearCuenta;

    public LoginVista() {
        setTitle("Agencia de Viajes - Iniciar Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("REGÍSTRATE");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelCorreo = new JLabel("Correo:");
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelCorreo.setForeground(Color.WHITE);
        labelContraseña.setForeground(Color.WHITE);

        campoCorreo = new JTextField(18);
        campoContraseña = new JPasswordField(18);
        botonIngresar = new JButton("Ingresar");
        botonCrearCuenta = new JButton("Crear Cuenta");

        botonIngresar.setBackground(new Color(0, 123, 255));
        botonIngresar.setForeground(Color.WHITE);
        botonCrearCuenta.setBackground(new Color(0, 123, 255));
        botonCrearCuenta.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8); // espaciado entre celdas


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(labelCorreo, gbc);

        gbc.gridx = 1;
        panel.add(campoCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelContraseña, gbc);

        gbc.gridx = 1;
        panel.add(campoContraseña, gbc);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.setBackground(new Color(30, 30, 30));
        botones.add(botonIngresar);
        botones.add(botonCrearCuenta);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(botones, gbc);

        add(panel);
    }

    public String getCorreo() {
        return campoCorreo.getText();
    }

    public String getContraseña() {
        return new String(campoContraseña.getPassword());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void agregarListenerIngresar(java.awt.event.ActionListener listener) {
        botonIngresar.addActionListener(listener);
    }

    public void agregarListenerCrearCuenta(java.awt.event.ActionListener listener) {
        botonCrearCuenta.addActionListener(listener);
    }
}

