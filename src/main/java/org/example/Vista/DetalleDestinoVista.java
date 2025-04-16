package org.example.Vista;

import org.example.Modelo.Destino;

import javax.swing.*;
import java.awt.*;

public class DetalleDestinoVista extends JFrame {

    public DetalleDestinoVista(Destino destino) {
        setTitle("Detalles de " + destino.getCiudad());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Cargar imagen de fondo
        ImageIcon fondoIcono = new ImageIcon(getClass().getClassLoader().getResource(destino.getRutaImagen()));
        JLabel fondo = new JLabel(new ImageIcon(fondoIcono.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH)));
        fondo.setLayout(new BorderLayout());

        // Panel transparente para el texto
        JPanel panelInfo = new JPanel();
        panelInfo.setOpaque(false);
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(destino.getCiudad() + " - " + destino.getPais());
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblDescripcion = new JLabel("<html><p>" + destino.getDescripcion() + "</p></html>");
        lblDescripcion.setForeground(Color.WHITE);

        JLabel lblInfoTuristica = new JLabel("<html><p><b>Info turística:</b> " + destino.getInfoTuristica() + "</p></html>");
        lblInfoTuristica.setForeground(Color.WHITE);

        JLabel lblPrecio = new JLabel("Precio: $" + destino.getPrecio());
        lblPrecio.setForeground(Color.WHITE);

        panelInfo.add(lblTitulo);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblDescripcion);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblInfoTuristica);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblPrecio);

        fondo.add(panelInfo, BorderLayout.CENTER);
        setContentPane(fondo);
    }
}



