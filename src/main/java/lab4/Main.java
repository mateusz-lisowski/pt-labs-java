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

        // Testowe dane do bazy
        entityManager.getTransaction().begin();

        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setName("Parent 1");

        ChildEntity childEntity1 = new ChildEntity();
        childEntity1.setName("Child 1");
        childEntity1.setParent(parentEntity);

        ChildEntity childEntity2 = new ChildEntity();
        childEntity2.setName("Child 2");
        childEntity2.setParent(parentEntity);

        parentEntity.getChildren().add(childEntity1);
        parentEntity.getChildren().add(childEntity2);

        entityManager.persist(parentEntity);

        entityManager.getTransaction().commit();

        // Dodawanie nowych wpisów do bazy
        entityManager.getTransaction().begin();

        ParentEntity parentEntity2 = new ParentEntity();
        parentEntity2.setName("Parent 2");

        ChildEntity childEntity3 = new ChildEntity();
        childEntity3.setName("Child 3");
        childEntity3.setParent(parentEntity2);

        parentEntity2.getChildren().add(childEntity3);

        entityManager.persist(parentEntity2);

        entityManager.getTransaction().commit();

        // Usuwanie wpisów z bazy
        entityManager.getTransaction().begin();

        ParentEntity parentToDelete = entityManager.find(ParentEntity.class, 1L);
        if (parentToDelete != null) {
            entityManager.remove(parentToDelete);
        }

        entityManager.getTransaction().commit();

        // Wyświetlenie wszystkich wpisów z bazy
        List<ParentEntity> parents = entityManager.createQuery("SELECT p FROM ParentEntity p", ParentEntity.class).getResultList();
        System.out.println("All parent entities:");
        for (ParentEntity parent : parents) {
            System.out.println(parent.getName());
            for (ChildEntity child : parent.getChildren()) {
                System.out.println(" - " + child.getName());
            }
        }

        // Wynik zapytania zdefiniowanego przez prowadzącego
        List<ChildEntity> childrenWithParentId1 = entityManager.createQuery("SELECT c FROM ChildEntity c WHERE c.parent.id = 1", ChildEntity.class).getResultList();
        System.out.println("Children with parent id 1:");
        for (ChildEntity child : childrenWithParentId1) {
            System.out.println(child.getName());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}

