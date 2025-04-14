package org.example.Controlador;

import org.example.Modelo.Paquetebasico;
import org.example.Vista.ComparacionVista;
import org.example.Vista.PaqueteVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PaquetebControlador {
    private PaqueteVista vista;
    private List<Paquetebasico> paquetes;

    public PaquetebControlador(PaqueteVista vista, List<Paquetebasico> paquetes) {
        this.vista = vista;
        this.paquetes = paquetes;

        vista.getBotonComparar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compararSeleccionados();
            }
        });
    }

    private void compararSeleccionados() {
        JCheckBox[] checkboxes = vista.getCheckboxes();
        List<Paquetebasico> seleccionados = new ArrayList<>();

        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].isSelected()) {
                seleccionados.add(paquetes.get(i));
            }
        }

        if (seleccionados.size() < 2) {
            JOptionPane.showMessageDialog(vista, "Seleccione al menos dos paquetes para comparar.");
            return;
        }

        ComparacionVista comparacion = new ComparacionVista(seleccionados);
        comparacion.setVisible(true);
    }
}
