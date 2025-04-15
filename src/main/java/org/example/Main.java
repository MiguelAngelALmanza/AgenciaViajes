package org.example;

import org.example.Controlador.controlHotel;
import org.example.Vista.LoginVista;
import org.example.Controlador.LoginControlador;

public class Main {
    public static void main(String[] args) {
        LoginVista vistaLogin = new LoginVista();
        new LoginControlador(vistaLogin);
        vistaLogin.setVisible(true);

    }
}