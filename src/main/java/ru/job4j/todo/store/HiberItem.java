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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HiberItem implements Store<Item> {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public Item create(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean update(int id, Item item) {
        item.setId(id);
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Item item = new Item();
        item.setId(id);
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(item);
        result = session.contains(item);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("select distinct c from Item c join fetch c.categories").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findByStatus(boolean status) {
        List<Item> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("select distinct c from Item c join fetch c.categories where c.done=false").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }
}
