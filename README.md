# Asphanoris - Plataforma de Traslados al Aeropuerto

## Descripción
Plataforma especializada en traslados al aeropuerto con garantía de puntualidad y capacidad de equipaje.

## Tecnologías
- Java 17
- Spring Boot 4.1.0
- PostgreSQL
- Maven
- JPA/Hibernate
- Swagger/OpenAPI

## Patrones de Diseño Implementados
- **Proxy**: UserProxy, DisputeProxy (control de acceso)
- **State**: DriverState, OrderState, DisputeState (máquinas de estados)
- **Factory**: UserFactory, AdminFactory, DriverFactory, PassengerFactory
- **Strategy**: PaymentMethod (Cash/CreditCard)
- **Repository**: Interfaces + implementaciones PostgreSQL
- **Builder**: VehicleBuilder

## Estructura del Proyecto
