package com.asphanoris.asphanorisbeta.domain.states;

import com.asphanoris.asphanorisbeta.domain.RealDispute;

public class InProcessState extends DisputeState {
    public InProcessState(RealDispute dispute) {
        super(dispute);
    }
    
    @Override
    public void reject() {
        System.out.println("Dispute rejected");
    }
    
    @Override
    public void cancel() {
        System.out.println("Dispute cancelled");
    }
    
    @Override
    public void done() {
        System.out.println("Dispute resolved");
    }
    
    @Override
    public DisputeState getNextState() {
        return new DoneState(dispute);
    }
    
    @Override
    public String getState() {
        return "IN_PROCESS";
    }
}