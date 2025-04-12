package org.example.Controlador;


import org.example.Vista.LoginVista;
import org.example.Vista.RegistroVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginControlador {
    private LoginVista vista;

    public LoginControlador(LoginVista vista) {
        this.vista = vista;

        vista.agregarListenerIngresar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = vista.getCorreo();
                String contraseña = vista.getContraseña();
                vista.mostrarMensaje("Intentando ingresar con:\nCorreo: " + correo);
                // Aquí iría la lógica real de autenticación
            }
        });

        vista.agregarListenerCrearCuenta(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroVista registroVista = new RegistroVista();
                new RegistroControlador(registroVista);
                registroVista.setVisible(true);
            }
        });
    }
}

