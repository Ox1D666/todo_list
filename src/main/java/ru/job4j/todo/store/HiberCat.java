package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HiberCat implements Store<Category> {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public Category create(Category element) {
        return null;
    }

    @Override
    public boolean update(int id, Category element) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Category> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
//        CriteriaBuilder cb =  session.getCriteriaBuilder();
//        CriteriaQuery<Category> categoryCriteria = cb.createQuery(Category.class);
//        Root<Category> categoryRoot = categoryCriteria.from(Category.class);
//        categoryCriteria.select(categoryRoot);
//        List<Category> result = session.createQuery(categoryCriteria).getResultList();
        List<Category> result = session.createQuery(
                "select distinct c from Category c join fetch c.items"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Category findById(int id) {
        return null;
    }
}
