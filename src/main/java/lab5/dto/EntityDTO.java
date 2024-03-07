package lab5.dto;


public class EntityDTO {
    private String id;
    private String name;

    public EntityDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
