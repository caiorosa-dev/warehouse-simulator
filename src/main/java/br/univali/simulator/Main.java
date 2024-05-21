package br.univali.simulator;

import br.univali.simulator.domain.Simulation;
import br.univali.simulator.domain.simulation.SimulationConfig;

public class Main {
    public static void main(String[] args) {
        SimulationConfig config = new SimulationConfig();
        Simulation simulation = new Simulation(config);

        simulation.run();
    }
}