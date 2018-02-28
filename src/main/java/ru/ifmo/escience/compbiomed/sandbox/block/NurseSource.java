package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;

public class NurseSource extends PedSource {

    public void inject(final int num) {
        for (int i = 0; i < num; ++i) {
            final Nurse nurse = new Nurse(i);
            // TODO: The onCreate() call should be added here.
            this.peds().add(nurse);
        }
    }

}
