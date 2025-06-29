package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioRepository repo;
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        repo = mock(UsuarioRepository.class);
        service = new UsuarioService(repo);
    }

    @Test
    void testListar() {
        List<Usuario> listaMock = Arrays.asList(
                new Usuario(1L, "Ana", "ana@mail.com", "ADMIN"),
                new Usuario(2L, "Luis", "luis@mail.com", "Usuario")
        );
        when(repo.findAll()).thenReturn(listaMock);

        List<Usuario> resultado = service.listar();

        assertEquals(2, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        Usuario usuario = new Usuario(1L, "Pedro", "pedro@mail.com", "GERENTE");
        when(repo.save(usuario)).thenReturn(usuario);

        Usuario resultado = service.guardar(usuario);

        assertEquals("Pedro", resultado.getNombre());
        verify(repo).save(usuario);
    }

    @Test
    void testBuscarExistente() {
        Usuario usuario = new Usuario(1L, "Sofía", "sofia@mail.com", "Usuario");
        when(repo.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscar(1L);

        assertNotNull(resultado);
        assertEquals("Sofía", resultado.getNombre());
        verify(repo).findById(1L);
    }

    @Test
    void testBuscarNoExistente() {
        when(repo.findById(999L)).thenReturn(Optional.empty());

        Usuario resultado = service.buscar(999L);

        assertNull(resultado);
        verify(repo).findById(999L);
    }
//test
    @Test
    void testEliminar() {
        long id = 3L;

        service.eliminar(id);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(repo).deleteById(captor.capture());

        assertEquals(id, captor.getValue());
    }
}
