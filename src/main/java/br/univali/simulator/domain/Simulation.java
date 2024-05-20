package br.univali.simulator.domain;

import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.domain.simulation.SimulationLog;
import br.univali.simulator.utils.LinkedList;

public class Simulation {
    SimulationConfig config;
    LinkedList<SimulationLog> logs;

    int currentDay;
    int currentHour;

    void processHour() {
        currentHour++;
    }

    void processDay() {
        currentDay++;
    }

    void run() {

    }
}
