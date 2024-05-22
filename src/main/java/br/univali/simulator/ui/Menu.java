package br.univali.simulator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.univali.simulator.domain.Simulation;
import br.univali.simulator.domain.simulation.SimulationConfig;

public class Menu {

    private JFrame frame;

    public void showMenu() {
        frame = new JFrame("Welcome to SOS-RS Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("SOS-RS Simulator", SwingConstants.CENTER);
        frame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton startButton = new JButton("Start simulation");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationConfig config = questionsSimulation();
                if (config != null) {
                    Simulation simulation = new Simulation(config);
                    String result = simulation.run();
                    showSimulationResults(result);
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\tExiting simulator...");
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public SimulationConfig questionsSimulation() {
        SimulationConfig config = new SimulationConfig();

        int option = JOptionPane.showConfirmDialog(frame, "Do you want to configure the simulation?", "Configuration", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                config.setInitialWarehouseStock(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the initial quantity of stock in the warehouse:")));
                config.setDailySuppliesArriving(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the quantity of supplies arriving daily:")));
                config.setDailyTrucks(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the number of trucks arriving daily:")));
                config.setTruckCapacity(Float.parseFloat(JOptionPane.showInputDialog(frame, "Report the truck capacity:")));
                config.setWeightOfSupplies(Float.parseFloat(JOptionPane.showInputDialog(frame, "Report the weight of the supplies:")));
                config.setStorageCapacityOfSupplies(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the quantity of supplies that can be stacked in each pile:")));
                config.setAmountOfStoragesOnWarehouse(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the number of batteries stored in the warehouse:")));
                config.setHoursToLoadTruck(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the number of hours to load a truck:")));
                config.setStartHourOfDay(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the start time of the day:")));
                config.setEndHourOfDay(Integer.parseInt(JOptionPane.showInputDialog(frame, "Report the end time of the day:")));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            System.out.println("Default configuration used.");
        }

        return config;
    }

    private void showSimulationResults(String result) {
        JFrame resultFrame = new JFrame("Simulation Results");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(500, 400);

        JTextArea resultArea = new JTextArea(result);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        resultFrame.add(scrollPane, BorderLayout.CENTER);

        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }
}