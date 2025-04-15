package org.example.Vista;

import org.example.Database.DatabaseHelper;
import org.example.Modelo.Reseña;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReseñasApp extends JFrame {
    private JPanel panelReseñas;
    private JComboBox<String> comboFiltro;
    private int idPaqueteActual;

    public ReseñasApp(int idPaquete) {
        this.idPaqueteActual = idPaquete;
        setTitle("Reseñas del Paquete " + idPaquete);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Colores modo oscuro
        Color bgDark = new Color(30, 30, 30);
        Color cardDark = new Color(44, 44, 44);
        Color textLight = Color.WHITE;
        Color accentColor = new Color(100, 149, 237); // Cornflower blue

        UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 13));
        UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 13));

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBackground(bgDark);
        panelFiltros.setBorder(new EmptyBorder(10, 10, 10, 10));

        comboFiltro = new JComboBox<>(new String[]{"Más recientes", "Mejor valoradas"});
        comboFiltro.setBackground(cardDark);
        comboFiltro.setForeground(textLight);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBackground(accentColor);
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);

        panelFiltros.add(new JLabel("Ordenar por:") {{
            setForeground(textLight);
        }});
        panelFiltros.add(comboFiltro);
        panelFiltros.add(btnFiltrar);

        // Panel de reseñas
        panelReseñas = new JPanel();
        panelReseñas.setLayout(new BoxLayout(panelReseñas, BoxLayout.Y_AXIS));
        panelReseñas.setBackground(bgDark);

        JScrollPane scroll = new JScrollPane(panelReseñas);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(bgDark);

        btnFiltrar.addActionListener(e -> cargarReseñas());

        add(panelFiltros, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        cargarReseñas();
    }

    private void cargarReseñas() {
        panelReseñas.removeAll();

        Color cardDark = new Color(44, 44, 44);
        Color textLight = Color.WHITE;
        Color accentColor = new Color(100, 149, 237);
        Color avatarColor = new Color(200, 200, 200);

        String filtro = comboFiltro.getSelectedItem().equals("Mejor valoradas") ? "top" : "recent";
        List<Reseña> reseñas = DatabaseHelper.getReseñas(idPaqueteActual, filtro);

        if (reseñas.isEmpty()) {
            JLabel noReseñas = new JLabel("No hay reseñas para este paquete");
            noReseñas.setForeground(textLight);
            noReseñas.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelReseñas.add(Box.createVerticalGlue());
            panelReseñas.add(noReseñas);
            panelReseñas.add(Box.createVerticalGlue());
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Reseña r : reseñas) {
                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(cardDark);
                card.setBorder(new EmptyBorder(10, 10, 10, 10));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

                // Panel superior con avatar
                JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panelSuperior.setOpaque(false);

                // Avatar circular con iniciales
                JLabel avatar = new JLabel("U" + r.getIdUsuario(), SwingConstants.CENTER);
                avatar.setPreferredSize(new Dimension(40, 40));
                avatar.setOpaque(true);
                avatar.setBackground(avatarColor);
                avatar.setForeground(Color.DARK_GRAY);
                avatar.setFont(new Font("SansSerif", Font.BOLD, 13));
                avatar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                avatar.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel avatarWrapper = new JPanel();
                avatarWrapper.setLayout(new BorderLayout());
                avatarWrapper.setOpaque(false);
                avatarWrapper.setPreferredSize(new Dimension(50, 50));
                avatarWrapper.add(avatar, BorderLayout.CENTER);

                avatar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                avatar.setHorizontalAlignment(SwingConstants.CENTER);
                avatar.setVerticalAlignment(SwingConstants.CENTER);

                panelSuperior.add(avatarWrapper);

                JLabel stars = new JLabel("★".repeat(r.getRating()));
                stars.setForeground(new Color(255, 215, 0)); // Gold
                JLabel fecha = new JLabel(" | " + r.getFecha().format(dtf));
                fecha.setForeground(Color.GRAY);
                JLabel nombre = new JLabel("Usuario #" + r.getIdUsuario());
                nombre.setForeground(textLight);

                panelSuperior.add(Box.createHorizontalStrut(10));
                panelSuperior.add(stars);
                panelSuperior.add(Box.createHorizontalStrut(10));
                panelSuperior.add(nombre);
                panelSuperior.add(fecha);

                // Comentario
                JTextArea comentario = new JTextArea(r.getComentario());
                comentario.setWrapStyleWord(true);
                comentario.setLineWrap(true);
                comentario.setEditable(false);
                comentario.setOpaque(false);
                comentario.setForeground(textLight);
                comentario.setBorder(new EmptyBorder(5, 0, 5, 0));

                // Panel inferior
                JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                panelInferior.setOpaque(false);
                JButton btnUtil = new JButton("👍 Útil (" + r.getUtil() + ")");
                btnUtil.setBackground(accentColor.darker());
                btnUtil.setForeground(Color.WHITE);
                btnUtil.setFocusPainted(false);
                btnUtil.addActionListener(e -> {
                    if (DatabaseHelper.marcarReseñaUtil(r.getId())) {
                        r.setUtil(r.getUtil() + 1);
                        btnUtil.setText("👍 Útil (" + r.getUtil() + ")");
                        btnUtil.setEnabled(false);
                    }
                });
                panelInferior.add(btnUtil);

                card.add(panelSuperior, BorderLayout.NORTH);
                card.add(comentario, BorderLayout.CENTER);
                card.add(panelInferior, BorderLayout.SOUTH);

                panelReseñas.add(card);
                panelReseñas.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        panelReseñas.revalidate();
        panelReseñas.repaint();
    }
}
