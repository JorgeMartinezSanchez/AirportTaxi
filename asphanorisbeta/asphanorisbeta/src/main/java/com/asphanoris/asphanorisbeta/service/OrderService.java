package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;
import com.asphanoris.asphanorisbeta.dto.OrderResponseDTO;
import com.asphanoris.asphanorisbeta.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private IOrderRepository orderRepo;
    
    private OrderResponseDTO convertToDTO(RealTripOrder order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setPassengerId(order.getPassengerId());
        dto.setDriverId(order.getDriverId());
        dto.setState(order.getStatus());
        dto.setTripStartDate(order.getTripStartDate());
        dto.setTripEndDate(order.getTripEndDate());
        dto.setTotal(order.getTotal());
        dto.setOrderCreationDate(order.getOrderCreationDate());
        return dto;
    }
    
    public OrderResponseDTO createOrder(OrderRequestDTO orderDto) {
        RealTripOrder order = new RealTripOrder();
        order.setPassengerId(orderDto.getPassengerId());
        order.setOriginAddress(orderDto.getOriginAddress());
        order.setDestinationAddress(orderDto.getDestinationAddress());
        order.setTripStartDate(orderDto.getTripStartDate());
        
        // Calcular tarifa base (simplificado)
        Double distance = 10.0; // Simulado - en realidad vendría de un servicio de mapas
        Double calculatedTotal = calculateFee(distance, 1); // 1 pasajero por defecto
        order.setPrice(calculatedTotal);
        
        RealTripOrder saved = orderRepo.addOrder(order);
        return convertToDTO(saved);
    }
    
    public void cancelOrder(Long orderId) {
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            order.cancel();
            orderRepo.modifyOrder(order);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }
    
    public void deleteOrder(Long orderId) {
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            orderRepo.deleteOrder(order);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }
    
    public OrderResponseDTO getOrder(Long orderId) {
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            return convertToDTO(order);
        }
        throw new RuntimeException("Order not found with id: " + orderId);
    }
    
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepo.getAllOrders().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public Double calculateFee(Double distance, Integer peopleCount) {
        double baseRate = 2.5; // $2.5 por km
        double peopleFactor = 1 + (peopleCount * 0.1);
        return distance * baseRate * peopleFactor;
    }
}