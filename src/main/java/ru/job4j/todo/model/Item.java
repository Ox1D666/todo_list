package ru.job4j.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Column(name = "create_date")
    private Timestamp create;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @SuppressWarnings("JpaAttributeTypeInspection")
    private User user;

    public Item(String description, Timestamp create) {
        this.description = description;
        this.create = create;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
