package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.service.CarritoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return service.crearCarrito(carrito);
    }

    @GetMapping("/usuario/{usuarioId}")
    public Optional<Carrito> obtenerCarritoPorUsuario(@PathVariable Long usuarioId) {
        return service.obtenerCarritoPorUsuario(usuarioId);
    }

    @PostMapping("/{usuarioId}/items")
    public Carrito agregarProducto(@PathVariable Long usuarioId, @RequestBody ItemCarrito item) {
        return service.agregarProducto(usuarioId, item);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrito(@PathVariable Long id) {
        service.eliminarCarrito(id);
    }
}