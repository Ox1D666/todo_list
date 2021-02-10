package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HiberUser implements Store<User> {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public User create(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean update(int id, User user) {
        user.setId(id);
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        User user = new User();
        user.setId(id);
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(user);
        result = session.contains(user);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<User> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        userCriteria.select(userRoot);
        List<User> result = session.createQuery(userCriteria).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public User findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        User result = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public User findUserByLogin(String login) {
        Session session = sf.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        userCriteria.select(userRoot);
        User result = session.createQuery(userCriteria).getResultList()
                .stream().filter(user -> user.getLogin().equals(login))
                .collect(Collectors.toList()).get(0);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
