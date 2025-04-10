package org.example.Controlador;

import org.example.Modelo.Usuario;
import org.example.Vista.RegistroVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioControlador {
    private RegistroVista vista;

    public UsuarioControlador(RegistroVista vista) {
        this.vista = vista;

        this.vista.agregarListenerRegistro(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = vista.getNombre();
        String correo = vista.getCorreo();
        String contraseña = vista.getContraseña();

        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, correo, contraseña);
        // Aquí podrías guardar el usuario en base de datos o archivo.
        vista.mostrarMensaje("Usuario registrado con éxito:\n" + nuevoUsuario.getNombre());
    }
}

