package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;

import java.util.ArrayList;
import java.util.List;

public class PedSource {

    private List<? super Pedestrian> peds = new ArrayList<>();

    public List<? super Pedestrian> peds() {
        return peds;
    }
}
