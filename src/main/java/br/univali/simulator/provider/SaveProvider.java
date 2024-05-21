package br.univali.simulator.provider;

import br.univali.simulator.domain.simulation.SimulationSave;
import br.univali.simulator.utils.LinkedList;

public interface SaveProvider {
    LinkedList<SimulationSave> listAll();

    void delete(String id);

    void deleteAll();

    void create(SimulationSave save);
}
