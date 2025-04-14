import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormularioComidas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormularioComidas::crearFormulario);

    }

    public static void crearFormulario() {
        JFrame frame = new JFrame("Formulario de Comidas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel("Seleccione las comidas que desea (clic derecho para ver imagen e ingredientes):");

        JCheckBox comida1 = new JCheckBox("Salteñas");
        JCheckBox comida2 = new JCheckBox("Silpancho");
        JCheckBox comida3 = new JCheckBox("Sopa de maní");
        JCheckBox comida4 = new JCheckBox("Chicharrón");


        // Ingredientes + imagen (puedes cambiar la ruta de imagen local o de internet)
        agregarInfoComida(comida1, "Carne, papa, ají colorado, huevo, pasas, aceituna, masa de trigo", "org/example/Modelo/saltenia.jpg");
        agregarInfoComida(comida2, "Carne empanizada, arroz, papa, huevo frito, ensalada", "org/example/Modelo/silpancho.jpg");
        agregarInfoComida(comida3, "Maní, carne, papa, fideo, zanahoria", "org/example/Modelo/sopademani.jpg");
        agregarInfoComida(comida4, "Carne de cerdo, mote, llajwa", "org/example/Modelo/chicharron.jpg");

        panel.add(menuLabel);
        panel.add(comida1);
        panel.add(comida2);
        panel.add(comida3);
        panel.add(comida4);

        // Alergias
        JLabel alergiaLabel = new JLabel("¿Tiene alguna alergia?");
        JRadioButton siAlergia = new JRadioButton("Sí");
        JRadioButton noAlergia = new JRadioButton("No");
        ButtonGroup grupoAlergia = new ButtonGroup();
        grupoAlergia.add(siAlergia);
        grupoAlergia.add(noAlergia);

        JPanel alergiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        alergiaPanel.add(alergiaLabel);
        alergiaPanel.add(siAlergia);
        alergiaPanel.add(noAlergia);

        panel.add(alergiaPanel);

        JLabel ingAlergLabel = new JLabel("Enumere los ingredientes alérgicos (separados por comas):");
        JTextField ingredientesAlergia = new JTextField(30);
        ingAlergLabel.setVisible(false);
        ingredientesAlergia.setVisible(false);

        panel.add(ingAlergLabel);
        panel.add(ingredientesAlergia);

        siAlergia.addActionListener(e -> {
            ingAlergLabel.setVisible(true);
            ingredientesAlergia.setVisible(true);
        });

        noAlergia.addActionListener(e -> {
            ingAlergLabel.setVisible(false);
            ingredientesAlergia.setVisible(false);
        });

        JButton enviarBtn = new JButton("Enviar");
        enviarBtn.addActionListener(e -> {
            StringBuilder seleccionadas = new StringBuilder();
            if (comida1.isSelected()) seleccionadas.append("Salteñas, ");
            if (comida2.isSelected()) seleccionadas.append("Silpancho, ");
            if (comida3.isSelected()) seleccionadas.append("Sopa de maní, ");
            if (comida4.isSelected()) seleccionadas.append("Chicharrón, ");

            String mensaje = "Comidas seleccionadas: ";
            if (seleccionadas.length() > 0) {
                mensaje += seleccionadas.substring(0, seleccionadas.length() - 2);
            } else {
                mensaje += "Ninguna";
            }

            if (siAlergia.isSelected()) {
                mensaje += "\nIngredientes alérgicos: " + ingredientesAlergia.getText();
            } else if (noAlergia.isSelected()) {
                mensaje += "\nNo tiene alergias.";
            }

            JOptionPane.showMessageDialog(frame, mensaje);
        });

        panel.add(enviarBtn);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void agregarInfoComida(JCheckBox checkbox, String ingredientes, String nombreImagen) {
        checkbox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Cargar imagen (debe estar en la misma carpeta del proyecto)
                    ImageIcon imagen = null;
                    try {
                        imagen = new ImageIcon(nombreImagen);
                        Image img = imagen.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                        imagen = new ImageIcon(img);
                    } catch (Exception ex) {
                        imagen = null;
                    }

                    // Panel principal horizontal
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                    panel.setPreferredSize(new Dimension(400, 180));

                    // Ingredientes
                    JLabel texto = new JLabel("<html><b>Ingredientes:</b><br>" + ingredientes + "</html>");
                    texto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    // Añadir texto al panel
                    panel.add(texto);

                    // Si hay imagen, añadir con espacio
                    if (imagen != null) {
                        JLabel imgLabel = new JLabel(imagen);
                        imgLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        panel.add(imgLabel);
                    }

                    // Mostrar el panel
                    JOptionPane.showMessageDialog(null, panel, "Información de " + checkbox.getText(), JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

}
