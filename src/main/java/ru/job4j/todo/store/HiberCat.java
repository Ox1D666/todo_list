package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        List<Category> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("from Category c").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public Category findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Category result = session.get(Category.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
