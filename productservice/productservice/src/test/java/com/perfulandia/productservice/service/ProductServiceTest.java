package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
        producto1 = new Producto(1L, "Perfume A", 12000.0, 10);
        producto2 = new Producto(2L, "Perfume B", 15000.0, 5);
    }

    @Test
    void testListarProductos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> lista = productoService.listar();

        assertEquals(2, lista.size());
        verify(productoRepository).findAll();
    }

    @Test
    void testGuardarProducto() {
        when(productoRepository.save(producto1)).thenReturn(producto1);

        Producto resultado = productoService.guardar(producto1);

        assertNotNull(resultado);
        assertEquals("Perfume A", resultado.getNombre());
        assertEquals(10, resultado.getStock());
        verify(productoRepository).save(producto1);
    }

    @Test
    void testBuscarPorId_existente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto1));

        Producto resultado = productoService.bucarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Perfume A", resultado.getNombre());
        verify(productoRepository).findById(1L);
    }

    @Test
    void testBuscarPorId_noExistente() {
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        Producto resultado = productoService.bucarPorId(999L);

        assertNull(resultado);
        verify(productoRepository).findById(999L);
    }

    @Test
    void testEliminarProducto() {
        productoService.eliminar(1L);
        verify(productoRepository).deleteById(1L);
    }
}
