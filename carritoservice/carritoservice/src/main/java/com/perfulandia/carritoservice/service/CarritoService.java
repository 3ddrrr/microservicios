package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CarritoService {
    private final CarritoRepository repository;
    private final RestTemplate restTemplate;

    public CarritoService(CarritoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Carrito crearCarrito(Carrito carrito) {
        calcularTotal(carrito);
        return repository.save(carrito);
    }

    public Optional<Carrito> obtenerCarritoPorUsuario(Long usuarioId) {
        return Optional.ofNullable(repository.findByUsuarioId(usuarioId));
    }

    public Carrito agregarProducto(Long usuarioId, ItemCarrito item) {
        Carrito carrito = repository.findByUsuarioId(usuarioId);

        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUsuarioId(usuarioId);
        }

        // Aquí podrías validar el producto con el microservicio de productos
        // Producto producto = restTemplate.getForObject("http://productservice/api/productos/" + item.getProductoId(), Producto.class);

        carrito.getItems().add(item);
        calcularTotal(carrito);
        return repository.save(carrito);
    }

    public void eliminarCarrito(Long id) {
        repository.deleteById(id);
    }

    private void calcularTotal(Carrito carrito) {
        double total = carrito.getItems().stream()
                .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
        carrito.setTotal(total);
    }
}