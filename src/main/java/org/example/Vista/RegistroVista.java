package org.example.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistroVista extends JFrame {
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JPasswordField campoContraseña;
    private JButton botonRegistrar;

    public RegistroVista() {
        setTitle("Registro de Usuario");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        campoNombre = new JTextField(20);
        campoCorreo = new JTextField(20);
        campoContraseña = new JPasswordField(20);
        botonRegistrar = new JButton("Registrar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(new JLabel(""));
        panel.add(botonRegistrar);

        add(panel);
    }

    public String getNombre() {
        return campoNombre.getText();
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

    public void agregarListenerRegistro(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
    }
}

