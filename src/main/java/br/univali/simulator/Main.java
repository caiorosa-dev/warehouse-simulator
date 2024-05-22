package br.univali.simulator;

import br.univali.simulator.ui.Menu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.showMenu();
        });
    }
}