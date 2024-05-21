package br.univali.simulator.adapter;

import br.univali.simulator.domain.Simulation;
import br.univali.simulator.domain.simulation.SimulationConfig;
import br.univali.simulator.domain.simulation.SimulationLog;
import br.univali.simulator.domain.simulation.SimulationSave;
import br.univali.simulator.utils.LinkedList;

public class SimulationSaveAdapter {
    public static SimulationSave serialize(Simulation simulation, String saveName) {
        SimulationConfig config = simulation.getConfig();
        LinkedList<SimulationLog> logs = simulation.getLogs();

        return new SimulationSave(saveName, config, logs);
    }
}
