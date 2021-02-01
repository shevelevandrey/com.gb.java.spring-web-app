package com.gb;

import com.gb.storage.User;
import com.gb.storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/*")
public class UsersHttpServlet extends HttpServlet {

    private UserStorage userStorage;

    @Override
    public void init() throws ServletException {
        userStorage = (UserStorage) getServletContext().getAttribute("userStorage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println(
                "<table>" +
                "  <tr>\n" +
                "    <th>Идентификатор</th>\n" +
                "    <th>Имя пользователя</th>\n" +
                "  </tr>");

        for (User user: userStorage.findAll()) {
            resp.getWriter().println(String.format(
                    "  <tr style=\"text-align: left;\">\n" +
                    "    <th style=\"text-align: center;\">%s</th>\n" +
                    "    <th>%s</th>\n" +
                    "  </tr>", user.getId(), user.getName()));
        }

        resp.getWriter().println("</table>");

    }
}
