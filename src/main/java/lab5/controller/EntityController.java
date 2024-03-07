package lab5.controller;

import lab5.dto.EntityDTO;
import lab5.model.Entity;
import lab5.repository.EntityRepository;

import java.util.Optional;


public class EntityController {
    private final EntityRepository repository;

    public EntityController(EntityRepository repository) {
        this.repository = repository;
    }

    public String saveEntity(EntityDTO dto) {
        Entity entity = new Entity(dto.getId(), dto.getName());

        try {
            repository.save(entity);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

    public String findEntityById(String id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        return optionalEntity.map(entity -> entity.getId() + ": " + entity.getName()).orElse("not found");
    }

    public String deleteEntityById(String id) {
        try {
            repository.deleteById(id);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }
}
