package com.asphanoris.asphanorisbeta.controller;

import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;
import com.asphanoris.asphanorisbeta.dto.OrderResponseDTO;
import com.asphanoris.asphanorisbeta.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }
    
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        // Pasas el ID directamente
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        // Pasas el ID directamente
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
    
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @GetMapping("/fee")
    public ResponseEntity<Double> calculateFee(
            @RequestParam Double distance, 
            @RequestParam(defaultValue = "1") Integer passengers) {
        return ResponseEntity.ok(orderService.calculateFee(distance, passengers));
    }
}