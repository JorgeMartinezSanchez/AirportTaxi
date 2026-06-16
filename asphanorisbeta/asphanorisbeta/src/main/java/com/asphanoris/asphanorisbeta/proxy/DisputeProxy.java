package com.asphanoris.asphanorisbeta.proxy;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.DisputeRequestDTO;
import com.asphanoris.asphanorisbeta.dto.DisputeResponseDTO;
import com.asphanoris.asphanorisbeta.service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DisputeProxy {
    
    @Autowired
    private DisputeService disputeService;
    
    private User currentUser;
    
    private boolean isPassenger() {
        return currentUser != null && currentUser.getRole().toString().equals("PASSENGER");
    }
    
    private boolean isAdmin() {
        return currentUser != null && currentUser.getRole().toString().equals("ADMIN");
    }
    
    public DisputeResponseDTO createDispute(DisputeRequestDTO disputeDto) {
        if (!isPassenger()) {
            throw new SecurityException("Only passengers can create disputes");
        }
        return disputeService.createDispute(disputeDto);
    }
    
    public void rejectDispute(Long disputeId) {
        if (!isAdmin()) {
            throw new SecurityException("Only admins can reject disputes");
        }
        disputeService.rejectDispute(disputeId);
    }
    
    public DisputeResponseDTO confirmDispute(Long disputeId) {
        if (!isAdmin()) {
            throw new SecurityException("Only admins can confirm dispute resolution");
        }
        return disputeService.confirmDispute(disputeId);
    }
    
    public DisputeResponseDTO getDispute(Long disputeId) {
        if (!isAdmin() && !isPassenger()) {
            throw new SecurityException("Not authorized to view disputes");
        }
        return disputeService.getDispute(disputeId);
    }
    
    public List<DisputeResponseDTO> getAllDisputes() {
        if (!isAdmin()) {
            throw new SecurityException("Only admins can view all disputes");
        }
        return disputeService.getAllDisputes();
    }
    
    public List<DisputeResponseDTO> getDisputesByOrder(Long orderId) {
        if (!isAdmin()) {
            throw new SecurityException("Only admins can view disputes by order");
        }
        return disputeService.getDisputesByOrder(orderId);
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}