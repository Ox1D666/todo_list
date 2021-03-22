package ru.job4j.todo.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HiberItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
//            HiberItem store = new HiberItem();
//            Category cat1 = new Category(1);
//            Category cat2 = new Category(2);
//            User user = new User(1);
//            Timestamp create = new Timestamp(System.currentTimeMillis());
//            Item item = new Item();
//            item.setDescription("222");
//            item.setUser(user);
//            item.setCreate(create);
//            item.addCategories(cat1);
//            item.addCategories(cat2);
//            session.save(item);

//            Car mark2 = new Car("Mark2");
//            Car corolla = new Car("Corolla");
//            Car a1 = new Car("A1");
//            session.save(mark2);
//            session.save(corolla);
//            session.save(a1);
//
//            Brand toyota = new Brand("Toyota");
//            toyota.addCar(session.get(Car.class, 1));
//            toyota.addCar(session.get(Car.class, 2));
//            session.save(toyota);
//
//            Brand audi = new Brand("Audi");
//            audi.addCar(session.get(Car.class, 3));
//            session.save(audi);

//            brands = session.createQuery(
//                    "select distinct c from Brand c join fetch c.cars"
//            ).list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
//        for (Car car :
//                brands.get(0).getCars()) {
//            System.out.println(car);
//        }
    }
}
