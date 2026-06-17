package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import com.asphanoris.asphanorisbeta.dto.OrderRequestDTO;
import com.asphanoris.asphanorisbeta.dto.OrderResponseDTO;
import com.asphanoris.asphanorisbeta.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    
    @Autowired
    private IOrderRepository orderRepo;
    
    private OrderResponseDTO convertToDTO(RealTripOrder order) {
        log.debug("Mapeando orden ID: {} a DTO", order.getId());
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
        log.info("Creando orden para pasajero ID: {}, origen: {}, destino: {}", 
            orderDto.getPassengerId(), 
            orderDto.getOriginAddress(), 
            orderDto.getDestinationAddress());
        
        try {
            RealTripOrder order = new RealTripOrder();
            order.setPassengerId(orderDto.getPassengerId());
            order.setOriginAddress(orderDto.getOriginAddress());
            order.setDestinationAddress(orderDto.getDestinationAddress());
            order.setTripStartDate(orderDto.getTripStartDate());
            
            // Calcular tarifa base (simplificado)
            Double distance = 10.0; // Simulado
            Double calculatedTotal = calculateFee(distance, 1);
            order.setPrice(calculatedTotal);
            log.debug("Tarifa calculada: ${} para {} km", calculatedTotal, distance);
            
            RealTripOrder saved = orderRepo.addOrder(order);
            log.info("Orden creada exitosamente con ID: {} y total: ${}", saved.getId(), saved.getTotal());
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al crear orden para pasajero ID: {}", orderDto.getPassengerId(), e);
            throw e;
        }
    }
    
    public void cancelOrder(Long orderId) {
        log.warn("Cancelando orden ID: {}", orderId);
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            order.cancel();
            orderRepo.modifyOrder(order);
            log.info("Orden ID: {} cancelada exitosamente", orderId);
        } else {
            log.error("Orden no encontrada con ID: {}", orderId);
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }
    
    public void deleteOrder(Long orderId) {
        log.warn("Eliminando orden ID: {}", orderId);
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            orderRepo.deleteOrder(order);
            log.info("Orden ID: {} eliminada exitosamente", orderId);
        } else {
            log.error("Orden no encontrada con ID: {}", orderId);
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }
    
    public OrderResponseDTO getOrder(Long orderId) {
        log.debug("Buscando orden con ID: {}", orderId);
        RealTripOrder order = orderRepo.getOrder(orderId);
        if (order != null) {
            log.debug("Orden encontrada con estado: {}", order.getStatus());
            return convertToDTO(order);
        }
        log.warn("Orden no encontrada con ID: {}", orderId);
        throw new RuntimeException("Order not found with id: " + orderId);
    }
    
    public List<OrderResponseDTO> getAllOrders() {
        log.info("Obteniendo todas las órdenes");
        List<OrderResponseDTO> orders = orderRepo.getAllOrders().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        log.info("Total de órdenes obtenidas: {}", orders.size());
        return orders;
    }
    
    public Double calculateFee(Double distance, Integer peopleCount) {
        log.debug("Calculando tarifa para {} km y {} pasajeros", distance, peopleCount);
        double baseRate = 2.5; // $2.5 por km
        double peopleFactor = 1 + (peopleCount * 0.1);
        double total = distance * baseRate * peopleFactor;
        log.debug("Tarifa calculada: ${} (baseRate: {}, peopleFactor: {})", total, baseRate, peopleFactor);
        return total;
    }
}