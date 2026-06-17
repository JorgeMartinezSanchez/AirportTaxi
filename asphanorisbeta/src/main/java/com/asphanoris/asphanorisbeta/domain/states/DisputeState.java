package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealDispute;

public abstract class DisputeState {
    protected RealDispute dispute;
    
    public DisputeState(RealDispute dispute) {
        this.dispute = dispute;
    }
    
    public abstract void reject();
    public abstract void cancel();
    public abstract void done();
    public abstract DisputeState getNextState();
    public abstract String getState();
}