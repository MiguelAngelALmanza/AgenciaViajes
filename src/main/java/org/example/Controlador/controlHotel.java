package org.example.Controlador;

import org.example.Database.ConexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class controlHotel extends JFrame {
    private JTable tablaHoteles;
    private JComboBox<String> comboBox1;
    private Map<String, Integer> mapaPaises = new HashMap<>();
    private JButton btnVerDetalle;
    private JButton btnReservar;

    public controlHotel() {
        setTitle("Hoteles disponibles");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFiltros = new JPanel();
        panelFiltros.add(new JLabel("País:"));
        comboBox1 = new JComboBox<>();
        comboBox1.addItem("Todos los países");
        panelFiltros.add(comboBox1);
        add(panelFiltros, BorderLayout.NORTH);

        tablaHoteles = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHoteles);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnVerDetalle = new JButton("Ver detalle");
        btnReservar = new JButton("Reservar hotel");

        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnReservar);
        add(panelBotones, BorderLayout.SOUTH);


        comboBox1.addActionListener(e -> {
            String paisSeleccionado = (String) comboBox1.getSelectedItem();
            cargarHoteles(paisSeleccionado);
        });

        btnVerDetalle.addActionListener(e -> mostrarDetalleHotelSeleccionado());
        btnReservar.addActionListener(e -> reservarHotelSeleccionado());

        cargarPaises();
        cargarHoteles("Todos los países");

        tablaHoteles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Selección de fila
                int fila = tablaHoteles.getSelectedRow();
                if (fila != -1) {
                    String nombre = tablaHoteles.getValueAt(fila, 0).toString();
                    String ciudad = tablaHoteles.getValueAt(fila, 1).toString();
                    double precio = Double.parseDouble(tablaHoteles.getValueAt(fila, 2).toString());
                    double puntuacion = Double.parseDouble(tablaHoteles.getValueAt(fila, 3).toString());


                    btnVerDetalle.setEnabled(true);
                    btnReservar.setEnabled(true);
                }
            }
        });

        btnVerDetalle.setEnabled(false);
        btnReservar.setEnabled(false);
    }

    private void cargarPaises() {
        try {
            Connection conn = ConexionDB.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM pais ORDER BY nombre");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                comboBox1.addItem(nombre);
                mapaPaises.put(nombre, id);
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar países: " + e.getMessage());
        }
    }

    private void cargarHoteles(String paisFiltro) {
        try {
            Connection conn = ConexionDB.conectar();

            String sql = "SELECT h.nombre, h.ciudad, h.precio_noche, h.puntuacion " +
                    "FROM hotel h JOIN pais p ON h.id_pais = p.id";

            PreparedStatement stmt;

            if (!"Todos los países".equals(paisFiltro)) {
                sql += " WHERE p.nombre = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, paisFiltro);
            } else {
                stmt = conn.prepareStatement(sql);
            }

            ResultSet rs = stmt.executeQuery();

            String[] columnas = {"Nombre", "Ciudad", "Precio por noche", "Puntuación"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            while (rs.next()) {
                Object[] fila = {
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getDouble("precio_noche"),
                        rs.getDouble("puntuacion")
                };
                modelo.addRow(fila);
            }

            tablaHoteles.setModel(modelo);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar hoteles: " + e.getMessage());
        }
    }

    private void mostrarDetalleHotelSeleccionado() {
        int fila = tablaHoteles.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un hotel para ver los detalles.");
            return;
        }

        String nombre = tablaHoteles.getValueAt(fila, 0).toString();
        String ciudad = tablaHoteles.getValueAt(fila, 1).toString();
        double precio = Double.parseDouble(tablaHoteles.getValueAt(fila, 2).toString());
        double puntuacion = Double.parseDouble(tablaHoteles.getValueAt(fila, 3).toString());

        String detalle = "Nombre: " + nombre +
                "\nCiudad: " + ciudad +
                "\nPrecio por noche: " + precio +
                "\nPuntuación: " + puntuacion;

        JOptionPane.showMessageDialog(this, detalle, "Detalle del Hotel", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reservarHotelSeleccionado() {
        int fila = tablaHoteles.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un hotel para reservar.");
            return;
        }

        String nombre = tablaHoteles.getValueAt(fila, 0).toString();
        String ciudad = tablaHoteles.getValueAt(fila, 1).toString();
        double precio = Double.parseDouble(tablaHoteles.getValueAt(fila, 2).toString());
        double puntuacion = Double.parseDouble(tablaHoteles.getValueAt(fila, 3).toString());

        mostrarFormularioReserva(nombre, ciudad, precio, puntuacion);
    }

    private void mostrarFormularioReserva(String nombre, String ciudad, double precio, double puntuacion) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Hotel:"));
        panel.add(new JLabel(nombre));

        panel.add(new JLabel("Ciudad:"));
        panel.add(new JLabel(ciudad));

        panel.add(new JLabel("Fecha entrada (YYYY-MM-DD):"));
        JTextField txtEntrada = new JTextField();
        panel.add(txtEntrada);

        panel.add(new JLabel("Fecha salida (YYYY-MM-DD):"));
        JTextField txtSalida = new JTextField();
        panel.add(txtSalida);

        panel.add(new JLabel("ID Usuario (tu ID):"));
        JTextField txtIdUsuario = new JTextField();
        panel.add(txtIdUsuario);

        int opcion = JOptionPane.showConfirmDialog(this, panel, "Reservar Hotel", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            String fechaEntrada = txtEntrada.getText();
            String fechaSalida = txtSalida.getText();
            String idUsuarioStr = txtIdUsuario.getText();

            if (fechaEntrada.isEmpty() || fechaSalida.isEmpty() || idUsuarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes ingresar todas las fechas y el ID de usuario.");
            } else {
                try {
                    int idUsuario = Integer.parseInt(idUsuarioStr);


                    if (!fechaEntrada.matches("\\d{4}-\\d{2}-\\d{2}") || !fechaSalida.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        JOptionPane.showMessageDialog(this, "Las fechas deben tener el formato YYYY-MM-DD.");
                        return;
                    }

                    // Mostrar pantalla de confirmación antes de guardar la reserva
                    mostrarConfirmacionReserva(nombre, ciudad, fechaEntrada, fechaSalida, precio, idUsuario);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El ID de usuario debe ser un número válido.");
                }
            }
        }
    }

    private void mostrarConfirmacionReserva(String nombre, String ciudad, String fechaEntrada, String fechaSalida, double precio, int idUsuario) {
        long diasEstancia = calcularDuracionEstancia(fechaEntrada, fechaSalida);
        double montoTotal = diasEstancia * precio;

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Hotel:"));
        panel.add(new JLabel(nombre));

        panel.add(new JLabel("Ciudad:"));
        panel.add(new JLabel(ciudad));

        panel.add(new JLabel("Fecha entrada:"));
        panel.add(new JLabel(fechaEntrada));

        panel.add(new JLabel("Fecha salida:"));
        panel.add(new JLabel(fechaSalida));

        panel.add(new JLabel("Duración (días):"));
        panel.add(new JLabel(String.valueOf(diasEstancia)));

        panel.add(new JLabel("Monto total:"));
        panel.add(new JLabel("$" + montoTotal));

        int opcion = JOptionPane.showConfirmDialog(this, panel, "Confirmación de Reserva", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            guardarReserva(idUsuario, montoTotal);
        }
    }


    private long calcularDuracionEstancia(String fechaEntrada, String fechaSalida) {
        try {
            Date entrada = Date.valueOf(fechaEntrada);
            Date salida = Date.valueOf(fechaSalida);
            long diffInMillies = Math.abs(salida.getTime() - entrada.getTime());
            return diffInMillies / (24 * 60 * 60 * 1000);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error en las fechas.");
            return 0;
        }
    }

    private void guardarReserva(int idUsuario, double montoTotal) {
        try {
            Connection conn = ConexionDB.conectar();


            String sqlReserva = "INSERT INTO reserva (idUsuario, tipoReserva, fechaReserva, estadoReserva) VALUES (?, ?, CURRENT_DATE, ?)";
            PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);
            stmtReserva.setInt(1, idUsuario);
            stmtReserva.setString(2, "Hotel");
            stmtReserva.setString(3, "Confirmada");

            int filasAfectadas = stmtReserva.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar la reserva.");
            }

            ResultSet generatedKeys = stmtReserva.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idReserva = generatedKeys.getInt(1);

                String sqlPago = "INSERT INTO pago (idReserva, metodoPago, monto, estadoPago) VALUES (?, ?, ?, ?)";
                PreparedStatement stmtPago = conn.prepareStatement(sqlPago);
                stmtPago.setInt(1, idReserva);
                stmtPago.setString(2, "Tarjeta");
                stmtPago.setDouble(3, montoTotal);
                stmtPago.setString(4, "Pagado");

                stmtPago.executeUpdate();

                JOptionPane.showMessageDialog(this, "Reserva y pago registrados correctamente.");
            } else {
                throw new SQLException("No se pudo obtener el ID de la reserva.");
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la reserva: " + e.getMessage());
        }
    }

}







