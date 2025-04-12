package org.example.Controlador;

import org.example.Modelo.Usuario;
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

        // Aquí podrías guardar el usuario
        vista.mostrarMensaje("¡Cuenta creada con éxito!\nBienvenido/a " + nombre);
        vista.dispose(); // Cierra la ventana de registro
    }
}
