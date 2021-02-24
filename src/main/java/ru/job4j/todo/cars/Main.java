package ru.job4j.todo.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Brand> brands = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

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

            brands = session.createQuery(
                    "select distinct c from Brand c join fetch c.cars"
            ).list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Car car :
                brands.get(0).getCars()) {
            System.out.println(car);
        }
    }
}
