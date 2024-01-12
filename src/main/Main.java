package main;

import gui.VentanaInicioSesion;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    	
    	SwingUtilities.invokeLater(() -> {
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
            ventanaInicioSesion.setVisible(true);
        });
    	
    }
}
