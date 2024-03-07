package lab4.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parent_entity")
public class ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildEntity> children = new ArrayList<>();

    public ParentEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setName(String name) {
        this.name = name;
    }

}
