package org.example;
import org.example.Vista.RegistroVista;
import org.example.Controlador.UsuarioControlador;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RegistroVista vista = new RegistroVista();
        new UsuarioControlador(vista);
        vista.setVisible(true);
        int elmer = 1;
    }
}