package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealDispute;

public class RejectedState extends DisputeState {
    public RejectedState(RealDispute dispute) {
        super(dispute);
    }
    
    @Override
    public void reject() {
        throw new IllegalStateException("Dispute already rejected");
    }
    
    @Override
    public void cancel() {
        throw new IllegalStateException("Cannot cancel rejected dispute");
    }
    
    @Override
    public void done() {
        throw new IllegalStateException("Cannot complete rejected dispute");
    }
    
    @Override
    public DisputeState getNextState() {
        return this;
    }
    
    @Override
    public String getState() {
        return "REJECTED";
    }
}