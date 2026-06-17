package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.RealDispute;
import com.asphanoris.asphanorisbeta.dto.DisputeRequestDTO;
import com.asphanoris.asphanorisbeta.dto.DisputeResponseDTO;
import com.asphanoris.asphanorisbeta.repository.IDisputeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DisputeService {
    
    @Autowired
    private IDisputeRepository disputeRepo;
    
    private DisputeResponseDTO convertToDTO(RealDispute dispute) {
        log.debug("Mapeando disputa ID: {} a DTO", dispute.getId());
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
        log.info("Creando nueva disputa para orden ID: {}, iniciada por usuario ID: {}", 
            disputeDto.getOrderId(), 
            disputeDto.getInitiatorId());
        log.debug("Título: {}, Descripción: {}", disputeDto.getTitle(), disputeDto.getDescription());
        
        try {
            RealDispute dispute = new RealDispute();
            dispute.setOrderId(disputeDto.getOrderId());
            dispute.setInitiatorId(disputeDto.getInitiatorId());
            dispute.setTitle(disputeDto.getTitle());
            dispute.setDescription(disputeDto.getDescription());
            
            RealDispute saved = disputeRepo.addDispute(dispute);
            log.info("Disputa creada exitosamente con ID: {} y estado: {}", 
                saved.getId(), 
                saved.getState().getState());
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al crear disputa para orden ID: {}", disputeDto.getOrderId(), e);
            throw e;
        }
    }
    
    public void rejectDispute(Long disputeId) {
        log.warn("Rechazando disputa ID: {}", disputeId);
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            log.debug("Estado actual de la disputa: {}", dispute.getState().getState());
            dispute.reject();
            disputeRepo.modifyDispute(dispute);
            log.info("Disputa ID: {} rechazada exitosamente. Nuevo estado: {}", 
                disputeId, 
                dispute.getState().getState());
        } else {
            log.error("Disputa no encontrada con ID: {}", disputeId);
            throw new RuntimeException("Dispute not found with id: " + disputeId);
        }
    }
    
    public DisputeResponseDTO confirmDispute(Long disputeId) {
        log.info("Confirmando resolución de disputa ID: {}", disputeId);
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            log.debug("Estado actual de la disputa: {}", dispute.getState().getState());
            dispute.done();
            RealDispute updated = disputeRepo.modifyDispute(dispute);
            log.info("Disputa ID: {} resuelta exitosamente. Nuevo estado: {}", 
                disputeId, 
                updated.getState().getState());
            return convertToDTO(updated);
        }
        log.error("Disputa no encontrada con ID: {}", disputeId);
        throw new RuntimeException("Dispute not found with id: " + disputeId);
    }
    
    public DisputeResponseDTO getDispute(Long disputeId) {
        log.debug("Buscando disputa con ID: {}", disputeId);
        RealDispute dispute = disputeRepo.getDispute(disputeId);
        if (dispute != null) {
            log.debug("Disputa encontrada con estado: {}", dispute.getState().getState());
            return convertToDTO(dispute);
        }
        log.warn("Disputa no encontrada con ID: {}", disputeId);
        throw new RuntimeException("Dispute not found with id: " + disputeId);
    }
    
    public List<DisputeResponseDTO> getAllDisputes() {
        log.info("Obteniendo todas las disputas");
        List<DisputeResponseDTO> disputes = disputeRepo.getAllDisputes().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        log.info("Total de disputas obtenidas: {}", disputes.size());
        return disputes;
    }
    
    public List<DisputeResponseDTO> getDisputesByOrder(Long orderId) {
        log.info("Buscando disputas para orden ID: {}", orderId);
        List<DisputeResponseDTO> disputes = disputeRepo.getAllDisputes().stream()
            .filter(d -> d.getOrderId().equals(orderId))
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        log.info("Disputas encontradas para orden {}: {}", orderId, disputes.size());
        return disputes;
    }
}