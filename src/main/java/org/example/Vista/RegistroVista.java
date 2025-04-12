package org.example.Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistroVista extends JFrame {
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoCorreo;
    private JPasswordField campoContraseña;
    private JPasswordField campoVerificarContraseña;

    private JCheckBox checkMontañas;
    private JCheckBox checkPlayas;
    private JCheckBox checkAventura;

    private JButton botonRegistrar;

    public RegistroVista() {
        setTitle("Registro de Cuenta - Agencia de Viajes");
        setSize(400, 350);
        setLocationRelativeTo(null);

        campoNombre = new JTextField(20);
        campoApellido = new JTextField(20);
        campoCorreo = new JTextField(20);
        campoContraseña = new JPasswordField(20);
        campoVerificarContraseña = new JPasswordField(20);

        checkMontañas = new JCheckBox("Montañas");
        checkPlayas = new JCheckBox("Playas");
        checkAventura = new JCheckBox("Aventura");

        botonRegistrar = new JButton("Registrar");

        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(new JLabel("Verificar Contraseña:"));
        panel.add(campoVerificarContraseña);
        panel.add(new JLabel("Preferencias:"));
        panel.add(new JLabel(""));
        panel.add(checkMontañas);
        panel.add(checkPlayas);
        panel.add(checkAventura);
        panel.add(botonRegistrar);

        add(panel);
    }

    public String getNombre() { return campoNombre.getText(); }
    public String getApellido() { return campoApellido.getText(); }
    public String getCorreo() { return campoCorreo.getText(); }
    public String getContraseña() { return new String(campoContraseña.getPassword()); }
    public String getVerificarContraseña() { return new String(campoVerificarContraseña.getPassword()); }

    public boolean prefMontañas() { return checkMontañas.isSelected(); }
    public boolean prefPlayas() { return checkPlayas.isSelected(); }
    public boolean prefAventura() { return checkAventura.isSelected(); }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void agregarListenerRegistro(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
    }
}
