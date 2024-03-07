package lab4;

import lab4.entities.ChildEntity;
import lab4.entities.ParentEntity;
import lab4.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Adding first parent entity

        ParentEntity parentEntity = new ParentEntity("Parent 1");
        ChildEntity childEntity1 = new ChildEntity("Child 1", parentEntity);
        ChildEntity childEntity2 = new ChildEntity("Child 2", parentEntity);

        parentEntity.getChildren().add(childEntity1);
        parentEntity.getChildren().add(childEntity2);

        entityManager.persist(parentEntity);
        entityManager.getTransaction().commit();

        // Adding second parent entity

        entityManager.getTransaction().begin();

        ParentEntity parentEntity2 = new ParentEntity("Parent 2");
        ChildEntity childEntity3 = new ChildEntity("Child 3", parentEntity2);

        parentEntity2.getChildren().add(childEntity3);

        entityManager.persist(parentEntity2);
        entityManager.getTransaction().commit();

        // Deleting parent 1 entity

        entityManager.getTransaction().begin();

        ParentEntity parentToDelete = entityManager.find(ParentEntity.class, 1L);
        if (parentToDelete != null) {
            entityManager.remove(parentToDelete);
        }

        entityManager.getTransaction().commit();

        // Printing out all parent entities

        List<ParentEntity> parents = entityManager.createQuery("SELECT p FROM ParentEntity p", ParentEntity.class).getResultList();
        System.out.println("All parent entities:");
        for (ParentEntity parent : parents) {
            System.out.println(parent.getName());
            for (ChildEntity child : parent.getChildren()) {
                System.out.println(" - " + child.getName());
            }
        }

        // Another example query

        List<ChildEntity> childrenWithParentId1 = entityManager.createQuery("SELECT c FROM ChildEntity c WHERE c.parent.id = 1", ChildEntity.class).getResultList();
        System.out.println("Children with parent id 1:");
        for (ChildEntity child : childrenWithParentId1) {
            System.out.println(child.getName());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}

