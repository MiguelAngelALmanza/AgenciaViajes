package org.example.Controlador;

import org.example.Modelo.Paquetebasico;
import org.example.Modelo.Usuario;
import org.example.Vista.CatalogoVista;
import org.example.Vista.PaqueteVista;
import org.example.Vista.RegistroVista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroControlador {
    private RegistroVista vista;

    public RegistroControlador(RegistroVista vista) {
        this.vista = vista;

        vista.agregarListenerRegistro(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    private void registrar() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String correo = vista.getCorreo();
        String contraseña = vista.getContraseña();
        String verificar = vista.getVerificarContraseña();

        if (!contraseña.equals(verificar)) {
            vista.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        List<String> preferencias = new ArrayList<>();
        if (vista.prefMontañas()) preferencias.add("Montañas");
        if (vista.prefPlayas()) preferencias.add("Playas");
        if (vista.prefAventura()) preferencias.add("Aventura");

        Usuario nuevo = new Usuario(nombre, apellido, correo, contraseña, preferencias);


        vista.mostrarMensaje("¡Cuenta creada con éxito!\nBienvenido/a " + nombre);
        vista.dispose(); // Cierra la ventana de registro
        vista.mostrarMensaje("¡Cuenta creada con éxito!");
        vista.dispose(); // Cerramos la ventana de registro


        List<Paquetebasico> paquetes = List.of(
                new Paquetebasico("Montañas de Bolivia", 350.0, 4, List.of("Guía", "Comidas", "Transporte")),
                new Paquetebasico("Playas del Caribe", 750.0, 7, List.of("Hotel 5*", "Comidas", "Tour acuático")),
                new Paquetebasico("Aventura en la Selva", 499.0, 5, List.of("Rafting", "Caminatas", "Camping"))
        );


        PaqueteVista paquetesVista = new PaqueteVista(paquetes);
        new PaquetebControlador(paquetesVista, paquetes);
        paquetesVista.setVisible(true);

        CatalogoVista catalogoVista = new CatalogoVista();
        new CatalogoControlador(catalogoVista);
        catalogoVista.setVisible(true);

    }

}
