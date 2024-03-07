package repository;

import lab5.repository.EntityRepository;
import lab5.model.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class EntityRepositoryJUnitTest {
    private EntityRepository repository;

    @BeforeEach
    void setUp() {
        repository = new EntityRepository();
    }

    @Test
    void saveEntity_Success() {
        Entity entity = new Entity("1", "Entity 1");
        repository.save(entity);
        Optional<Entity> retrievedEntity = repository.findById("1");
        assertTrue(retrievedEntity.isPresent());
        assertEquals(entity, retrievedEntity.get());
    }

    @Test
    void saveEntity_Fail_DuplicateId() {
        Entity entity1 = new Entity("1", "Entity 1");
        Entity entity2 = new Entity("1", "Entity 2");
        repository.save(entity1);
        assertThrows(IllegalArgumentException.class, () -> repository.save(entity2));
    }

    @Test
    void findEntityById_Exists() {
        Entity entity = new Entity("1", "Entity 1");
        repository.save(entity);
        String result = repository.findById("1").map(Entity::getName).orElse(null);
        assertNotNull(result);
        assertEquals(entity.getName(), result);
    }

    @Test
    void findEntityById_NotExists() {
        assertFalse(repository.findById("1").isPresent());
    }

    @Test
    void deleteEntityById_Success() {
        Entity entity = new Entity("1", "Entity 1");
        repository.save(entity);
        repository.deleteById("1");
        assertFalse(repository.findById("1").isPresent());
    }

    @Test
    void deleteEntityById_NotExists() {
        assertThrows(IllegalArgumentException.class, () -> repository.deleteById("1"));
    }
}
