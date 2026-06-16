package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.RealDispute;
import com.asphanoris.asphanorisbeta.dto.DisputeRequestDTO;
import com.asphanoris.asphanorisbeta.dto.DisputeResponseDTO;
import com.asphanoris.asphanorisbeta.repository.IDisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisputeService {
    
    @Autowired
    private IDisputeRepository disputeRepo;
    
    private DisputeResponseDTO convertToDTO(RealDispute dispute) {
        DisputeResponseDTO dto = new DisputeResponseDTO();
        dto.setId(dispute.getId());
        dto.setOrderId(dispute.getOrderId());
        dto.setInitiatorId(dispute.getInitiatorId());
        dto.setTitle(dispute.getTitle());
        dto.setDescription(dispute.getDescription());
        dto.setState(dispute.getState().getState());
        return dto;
    }
    
    public DisputeResponseDTO createDispute(DisputeRequestDTO disputeDto) {
        RealDispute dispute = new RealDispute();
        dispute.setOrderId(disputeDto.getOrderId());
        dispute.setInitiatorId(disputeDto.getInitiatorId());
        dispute.setTitle(disputeDto.getTitle());
        dispute.setDescription(disputeDto.getDescription());
        
        RealDispute saved = disputeRepo.addDispute(dispute);
        return convertToDTO(saved);
    }
    
    public void rejectDispute(Long disputeId) {
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            dispute.reject();
            disputeRepo.modifyDispute(dispute);
        } else {
            throw new RuntimeException("Dispute not found with id: " + disputeId);
        }
    }
    
    public DisputeResponseDTO confirmDispute(Long disputeId) {
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            dispute.done();
            RealDispute updated = disputeRepo.modifyDispute(dispute);
            return convertToDTO(updated);
        }
        throw new RuntimeException("Dispute not found with id: " + disputeId);
    }
    
    public DisputeResponseDTO getDispute(Long disputeId) {
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            return convertToDTO(dispute);
        }
        throw new RuntimeException("Dispute not found with id: " + disputeId);
    }
    
    public List<DisputeResponseDTO> getAllDisputes() {
        return disputeRepo.getAllDisputes().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public List<DisputeResponseDTO> getDisputesByOrder(Long orderId) {
        return disputeRepo.getAllDisputes().stream()
            .filter(d -> d.getOrderId().equals(orderId))
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
}