package br.univali.simulator.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class SimulationLog {
    private int day;
    private int weightOnDayStart;
    private int weightOnDayEnd;
    private int trucksArrived;
    private int trucksDeparted;
    private int suppliesDelivered;
    private int suppliesReceived;
    private int suppliesOnHold;
    private int suppliesOnStorages;
}
