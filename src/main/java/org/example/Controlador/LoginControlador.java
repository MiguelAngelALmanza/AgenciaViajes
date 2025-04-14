package org.example.Controlador;

import org.example.Modelo.Paquetebasico;
import org.example.Modelo.UsuarioDAO;
import org.example.Vista.LoginVista;
import org.example.Vista.PaqueteVista;
import org.example.Vista.RegistroVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginControlador {
    private LoginVista vista;

    public LoginControlador(LoginVista vista) {
        this.vista = vista;

        vista.agregarListenerIngresar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = vista.getCorreo();
                String contraseña = vista.getContraseña();

                UsuarioDAO dao = new UsuarioDAO();
                boolean autenticado = dao.autenticar(correo, contraseña);

                if (autenticado) {
                    vista.mostrarMensaje("¡Inicio de sesión exitoso!");

                    int idUsuario = dao.obtenerIdUsuario(correo);
                    System.out.println("ID Usuario: " + idUsuario);

                    vista.dispose(); // cerrar la ventana de login

                    // Cargar paquetes (puedes ponerlo fijo o desde base de datos)
                    List<Paquetebasico> paquetes = List.of(
                            new Paquetebasico("Montañas de Bolivia", 350.0, 4, List.of("Guía", "Comidas", "Transporte")),
                            new Paquetebasico("Playas del Caribe", 750.0, 7, List.of("Hotel 5*", "Comidas", "Tour acuático")),
                            new Paquetebasico("Aventura en la Selva", 499.0, 5, List.of("Rafting", "Caminatas", "Camping"))
                    );

                    PaqueteVista paquetesVista = new PaqueteVista(paquetes);
                    new PaquetebControlador(paquetesVista, paquetes);
                    paquetesVista.setVisible(true);
                }
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
