package com.asphanoris.asphanorisbeta.controller;

import com.asphanoris.asphanorisbeta.dto.DisputeRequestDTO;
import com.asphanoris.asphanorisbeta.dto.DisputeResponseDTO;
import com.asphanoris.asphanorisbeta.proxy.DisputeProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disputes")
public class DisputeController {
    
    private final DisputeProxy disputeProxy;
    
    // ✅ Inyección por constructor
    public DisputeController(DisputeProxy disputeProxy) {
        this.disputeProxy = disputeProxy;
    }
    
    @PostMapping
    public ResponseEntity<DisputeResponseDTO> createDispute(@RequestBody DisputeRequestDTO disputeDto) {
        return ResponseEntity.ok(disputeProxy.createDispute(disputeDto));
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectDispute(@PathVariable Long id) {
        disputeProxy.rejectDispute(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/confirm")
    public ResponseEntity<DisputeResponseDTO> confirmDispute(@PathVariable Long id) {
        return ResponseEntity.ok(disputeProxy.confirmDispute(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DisputeResponseDTO> getDispute(@PathVariable Long id) {
        return ResponseEntity.ok(disputeProxy.getDispute(id));
    }
    
    @GetMapping
    public ResponseEntity<List<DisputeResponseDTO>> getAllDisputes() {
        return ResponseEntity.ok(disputeProxy.getAllDisputes());
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DisputeResponseDTO>> getDisputesByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(disputeProxy.getDisputesByOrder(orderId));
    }
}