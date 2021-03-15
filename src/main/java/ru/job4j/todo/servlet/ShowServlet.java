package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HiberCat;
import ru.job4j.todo.store.HiberItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        HiberItem store = new HiberItem();
        List<Item> items = new ArrayList<>(store.findAll());
        if (req.getParameter("done") != null) {
            switch (req.getParameter("done")) {
                case "all" -> items = store.findAll();
                case "true" -> items = store.findByStatus(true);
                case "false" -> items = store.findByStatus(false);
            }
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(items));
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HiberItem itemStore = new HiberItem();
        HiberCat catStore = new HiberCat();
        Gson gson = new Gson();
        Parameters parameters = gson.fromJson(req.getReader().readLine(), Parameters.class);
        Timestamp create = new Timestamp(System.currentTimeMillis());
        List<Category> categories = new ArrayList<>();
        for (var el :
                parameters.getcIDs()) {
            categories.add(catStore.findById(Integer.parseInt(el)));
        }
        if (parameters.getDesc() != null) {
            itemStore.create(new Item(parameters.getDesc(),
                    create,
                    new User(Integer.parseInt(parameters.getUser_id())),
                    categories));
        }
    }

    class Parameters {
        private String user_id;
        private String desc;
        private String[] cIDs;

        public Parameters(String id, String desc, String[] cIDs) {
            this.user_id = id;
            this.desc = desc;
            this.cIDs = cIDs;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String[] getcIDs() {
            return cIDs;
        }

        public void setcIDs(String[] cIDs) {
            this.cIDs = cIDs;
        }
    }
}
