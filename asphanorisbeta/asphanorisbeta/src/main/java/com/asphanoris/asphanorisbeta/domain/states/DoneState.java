package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealDispute;

public class DoneState extends DisputeState {
    public DoneState(RealDispute dispute) {
        super(dispute);
    }
    
    @Override
    public void reject() {
        throw new IllegalStateException("Dispute already resolved");
    }
    
    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel resolved dispute");
    }
    
    @Override
    public void done() {
        throw new IllegalStateException("Dispute already resolved");
    }
    
    @Override
    public DisputeState getNextState() {
        return this;
    }
    
    @Override
    public String getState() {
        return "DONE";
    }
}