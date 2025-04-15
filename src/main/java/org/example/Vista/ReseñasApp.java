package org.example.Vista;

import org.example.Database.DatabaseHelper;
import org.example.Modelo.Reseña;

import javax.swing.*;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFiltros = new JPanel();
        comboFiltro = new JComboBox<>(new String[]{"Más recientes", "Mejor valoradas"});
        JButton btnFiltrar = new JButton("Filtrar");
        panelFiltros.add(new JLabel("Ordenar por:"));
        panelFiltros.add(comboFiltro);
        panelFiltros.add(btnFiltrar);


        panelReseñas = new JPanel();
        panelReseñas.setLayout(new BoxLayout(panelReseñas, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panelReseñas);


        btnFiltrar.addActionListener(e -> cargarReseñas());

        add(panelFiltros, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        cargarReseñas();
    }

    private void cargarReseñas() {
        panelReseñas.removeAll();
        String filtro = comboFiltro.getSelectedItem().equals("Mejor valoradas") ? "top" : "recent";

        List<Reseña> reseñas = DatabaseHelper.getReseñas(idPaqueteActual, filtro);

        if (reseñas.isEmpty()) {
            panelReseñas.add(new JLabel("No hay reseñas para este paquete"));
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Reseña r : reseñas) {
                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createEtchedBorder());


                JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panelSuperior.add(new JLabel("★".repeat(r.getRating())));
                panelSuperior.add(new JLabel("Usuario #" + r.getIdUsuario()));
                panelSuperior.add(new JLabel(r.getFecha().format(dtf)));


                JTextArea comentario = new JTextArea(r.getComentario());
                comentario.setLineWrap(true);
                comentario.setEditable(false);


                JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton btnUtil = new JButton("Útil (" + r.getUtil() + ")");
                btnUtil.addActionListener(e -> {
                    if (DatabaseHelper.marcarReseñaUtil(r.getId())) {
                        // Actualizar el contador localmente
                        r.setUtil(r.getUtil() + 1);
                        btnUtil.setText("Útil (" + r.getUtil() + ")");
                        btnUtil.setEnabled(false);
                    }
                });
                panelInferior.add(btnUtil);

                card.add(panelSuperior, BorderLayout.NORTH);
                card.add(new JScrollPane(comentario), BorderLayout.CENTER);
                card.add(panelInferior, BorderLayout.SOUTH);

                panelReseñas.add(card);
                panelReseñas.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        panelReseñas.revalidate();
        panelReseñas.repaint();
    }

    /*public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new ReseñasApp(1).setVisible(true));
    }*/
}