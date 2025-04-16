package org.example.Vista;

import org.example.Controlador.CatalogoControlador;
import org.example.Modelo.Destino;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogoVista extends JFrame {

    private DefaultListModel<Destino> modeloLista;
    private JList<Destino> listaDestinos;
    private JTextField campoBusqueda;
    private JComboBox<String> comboCategoria;
    private JButton botonRecargar;
    private JButton botonVerDetalles;
    private JButton botonVerReseñas;

    public CatalogoVista() {
        setTitle("Catálogo de Destinos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Cambiar fondo de la ventana a negro
        getContentPane().setBackground(Color.BLACK);

        modeloLista = new DefaultListModel<>();
        listaDestinos = new JList<>(modeloLista);
        listaDestinos.setCellRenderer(new DestinoRenderer());

        // Cambiar el fondo de la lista de destinos a negro
        listaDestinos.setBackground(Color.BLACK);
        listaDestinos.setForeground(Color.WHITE);  // Cambiar texto a blanco para que sea visible

        campoBusqueda = new JTextField();
        comboCategoria = new JComboBox<>(new String[]{"Todos", "Playa", "Montaña", "Aventura"});

        botonRecargar = new JButton("Recargar Catálogo");
        botonVerDetalles = new JButton("Ver Detalles");
        botonVerReseñas = new JButton("Ver Reseñas");

        // Panel superior (buscador + filtro)
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(new JLabel("Buscar: "), BorderLayout.WEST);
        panelSuperior.add(campoBusqueda, BorderLayout.CENTER);
        panelSuperior.add(comboCategoria, BorderLayout.EAST);

        // Cambiar el fondo del panel superior
        panelSuperior.setBackground(Color.BLACK);
        panelSuperior.setForeground(Color.WHITE);

        // Panel inferior (botones)
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRecargar);
        panelBotones.add(botonVerDetalles);
        panelBotones.add(botonVerReseñas);

        // Cambiar fondo de los botones
        panelBotones.setBackground(Color.BLACK);
        panelBotones.setForeground(Color.WHITE);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaDestinos), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setControlador(CatalogoControlador controlador) {
        botonRecargar.addActionListener(e -> controlador.cargarDestinos());
        botonVerDetalles.addActionListener(e -> controlador.verDetallesSeleccionado());
        botonVerReseñas.addActionListener(e -> controlador.verReseñasSeleccionadas());
        campoBusqueda.addActionListener(e -> controlador.cargarDestinos());
        comboCategoria.addActionListener(e -> controlador.cargarDestinos());
    }

    public void mostrarDestinos(List<Destino> destinos) {
        modeloLista.clear();
        destinos.forEach(modeloLista::addElement);
    }

    public String getCampoBusquedaTexto() {
        return campoBusqueda.getText().trim().toLowerCase();
    }

    public String getCategoriaSeleccionada() {
        return (String) comboCategoria.getSelectedItem();
    }

    public Destino getDestinoSeleccionado() {
        return listaDestinos.getSelectedValue();
    }

    // Renderizador personalizado para mostrar imagen y datos
    private static class DestinoRenderer extends JPanel implements ListCellRenderer<Destino> {
        private JLabel lblImagen = new JLabel();
        private JLabel lblTexto = new JLabel();

        public DestinoRenderer() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(lblImagen, BorderLayout.WEST);
            add(lblTexto, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Destino> list, Destino value, int index, boolean isSelected, boolean cellHasFocus) {
            try {
                ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(value.getRutaImagen()));

                Image imagen = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imagen));
            } catch (Exception e) {
                lblImagen.setIcon(null);
            }

            lblTexto.setText("<html><b>" + value.getCiudad() + ", " + value.getPais() + "</b><br>Precio: $" + value.getPrecio() + "</html>");

            setBackground(isSelected ? new Color(184, 207, 229) : Color.BLACK);
            lblTexto.setForeground(isSelected ? Color.BLACK : Color.WHITE);  // Cambiar color del texto según selección

            return this;
        }
    }
}
