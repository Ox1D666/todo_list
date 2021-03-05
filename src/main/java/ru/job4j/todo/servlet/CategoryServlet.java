package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.HiberCat;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HiberCat hiberCat = new HiberCat();
        List<Category> categories = hiberCat.findAll();
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(categories));
        out.flush();
        out.close();
    }
}
