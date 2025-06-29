package com.perfulandia.carritoservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId; // ID del producto en el microservicio de productos
    private String nombreProducto;
    private Double precioUnitario;
    private Integer cantidad;
}