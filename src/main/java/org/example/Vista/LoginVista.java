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
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        campoCorreo = new JTextField(20);
        campoContraseña = new JPasswordField(20);
        botonIngresar = new JButton("Ingresar");
        botonCrearCuenta = new JButton("Crear Cuenta");

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(botonIngresar);
        panel.add(botonCrearCuenta);

        add(panel);
    }

    public String getCorreo() { return campoCorreo.getText(); }
    public String getContraseña() { return new String(campoContraseña.getPassword()); }
    public void mostrarMensaje(String mensaje) { JOptionPane.showMessageDialog(this, mensaje); }

    public void agregarListenerIngresar(ActionListener listener) {
        botonIngresar.addActionListener(listener);
    }

    public void agregarListenerCrearCuenta(ActionListener listener) {
        botonCrearCuenta.addActionListener(listener);
    }
}
