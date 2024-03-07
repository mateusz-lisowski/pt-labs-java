package lab5.repository;

import lab5.model.Entity;

import java.util.HashMap;
import java.util.Optional;


public class EntityRepository {
    private final HashMap<String, Entity> entities = new HashMap<>();

    public String save(Entity entity) {
        if (entities.containsKey(entity.getId())) {
            throw new IllegalArgumentException("Entity with id " + entity.getId() + " already exists");
        }
        entities.put(entity.getId(), entity);
        return "done";
    }

    public Optional<Entity> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    public String deleteById(String id) {
        if (!entities.containsKey(id)) {
            throw new IllegalArgumentException("Entity with id " + id + " not found");
        }
        entities.remove(id);
        return "done";
    }
}
