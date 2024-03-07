package repository;

import lab5.controller.EntityController;
import lab5.dto.EntityDTO;
import lab5.model.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import lab5.repository.EntityRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EntityRepositoryMockitoTest {
    private EntityController controller;

    @Mock
    private EntityRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEntity_Success() {
        EntityDTO dto = new EntityDTO("1", "Entity 1");
        when(repository.save(any())).thenReturn("done");
        String result = controller.saveEntity(dto);
        assertEquals("done", result);
        verify(repository, times(1)).save(any());
    }

    @Test
    void saveEntity_Fail() {
        EntityDTO dto = new EntityDTO("1", "Entity 1");
        when(repository.save(any())).thenReturn("bad request");
        String result = controller.saveEntity(dto);
        assertEquals("bad request", result);
        verify(repository, times(1)).save(any());
    }

    @Test
    void findEntityById_Exists() {
        when(repository.findById("1")).thenReturn(Optional.of(new Entity("1", "Entity 1")));
        String result = controller.findEntityById("1");
        assertEquals("1: Entity 1", result);
        verify(repository, times(1)).findById("1");
    }

    @Test
    void findEntityById_NotExists() {
        when(repository.findById("1")).thenReturn(Optional.empty());
        String result = controller.findEntityById("1");
        assertEquals("not found", result);
        verify(repository, times(1)).findById("1");
    }

    @Test
    void deleteEntityById_Success() {
        when(repository.deleteById("1")).thenReturn("done");
        String result = controller.deleteEntityById("1");
        assertEquals("done", result);
        verify(repository, times(1)).deleteById("1");
    }

    @Test
    void deleteEntityById_NotExists() {
        when(repository.deleteById("1")).thenReturn("not found");
        String result = controller.deleteEntityById("1");
        assertEquals("not found", result);
        verify(repository, times(1)).deleteById("1");
    }
}
