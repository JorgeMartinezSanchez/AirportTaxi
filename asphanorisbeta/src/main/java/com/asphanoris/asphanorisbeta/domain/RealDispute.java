package com.asphanoris.asphanorisbeta.domain;

import com.asphanoris.asphanorisbeta.domain.states.DisputeState;
import com.asphanoris.asphanorisbeta.domain.states.InProcessState;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RealDispute {
    private Long id;
    private Long orderId;
    private Long initiatorId;
    private String title;
    private String description;
    private List<byte[]> photos = new ArrayList<>();
    private DisputeState state;
    
    public RealDispute() {
        this.state = new InProcessState(this);
    }
    
    public void reject() {
        this.state.reject();
        this.state = this.state.getNextState();
    }
    
    public void cancel() {
        this.state.cancel();
        this.state = this.state.getNextState();
    }
    
    public void done() {
        this.state.done();
        this.state = this.state.getNextState();
    }
}