package br.univali.simulator.domain.simulation;

import br.univali.simulator.utils.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data @AllArgsConstructor
public class SimulationSave {
    @Setter
    private int id;
    private final String name;
    private final SimulationConfig config;
    private final LinkedList<SimulationLog> logs;

    public SimulationSave(String name, SimulationConfig config, LinkedList<SimulationLog> logs) {
        this.id = 0;
        this.name = name;
        this.config = config;
        this.logs = logs;
    }
}
