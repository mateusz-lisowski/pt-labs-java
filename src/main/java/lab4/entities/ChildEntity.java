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

    // constructors, getters, setters
}
