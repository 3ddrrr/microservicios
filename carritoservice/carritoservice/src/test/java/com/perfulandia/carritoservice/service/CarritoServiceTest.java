package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarritoServiceTest {

    private CarritoRepository repo;
    private RestTemplate restTemplate;
    private CarritoService service;

    @BeforeEach
    void setUp() {
        repo = mock(CarritoRepository.class);
        restTemplate = mock(RestTemplate.class);
        service = new CarritoService(repo, restTemplate);
    }

    @Test
    void testCrearCarrito() {
        Carrito carrito = Carrito.builder()
                .usuarioId(1L)
                .items(Collections.emptyList())
                .total(0.0)
                .build();

        when(repo.save(any(Carrito.class))).thenReturn(carrito);

        Carrito resultado = service.crearCarrito(carrito);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUsuarioId());
        verify(repo, times(1)).save(carrito);
    }

    @Test
    void testObtenerCarritoPorUsuario() {
        Carrito carrito = Carrito.builder()
                .id(1L)
                .usuarioId(1L)
                .items(Collections.emptyList())
                .total(0.0)
                .build();

        when(repo.findByUsuarioId(1L)).thenReturn(carrito);

        Optional<Carrito> resultado = service.obtenerCarritoPorUsuario(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getUsuarioId());
        verify(repo, times(1)).findByUsuarioId(1L);
    }


        @Test
        void testAgregarProducto() {
            // Preparar datos
            Long usuarioId = 1L;
            ItemCarrito item = ItemCarrito.builder()
                    .productoId(101L)
                    .nombreProducto("Perfume")
                    .precioUnitario(10.000)
                    .cantidad(2)
                    .build();

            Carrito carritoExistente = Carrito.builder()
                    .id(1L)
                    .usuarioId(usuarioId)
                    .items(new ArrayList<>())  //Lista vacía inicializada
                    .total(0.0)
                    .build();

            // Simular el repositorio
            when(repo.findByUsuarioId(usuarioId)).thenReturn(carritoExistente);
            when(repo.save(any(Carrito.class))).thenAnswer(i -> i.getArgument(0));

            //Ejecuta el método
            Carrito actualizado = service.agregarProducto(usuarioId, item);

            //verificar resultados
            assertNotNull(actualizado);
            assertEquals(1, actualizado.getItems().size());
            assertEquals(20000.0, actualizado.getTotal());
            assertEquals("Perfume", actualizado.getItems().get(0).getNombreProducto());

            // Verificar que se guardó
            verify(repo).save(any(Carrito.class));
        }


    @Test
    void testEliminarCarrito() {
        doNothing().when(repo).deleteById(1L);

        service.eliminarCarrito(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}
