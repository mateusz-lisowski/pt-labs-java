package lab4.entities;

import javax.persistence.*;

@Entity
@Table(name = "child_entity")
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;

    public ChildEntity(String name, ParentEntity parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

}
