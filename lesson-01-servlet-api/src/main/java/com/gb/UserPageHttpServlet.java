package com.gb;

import com.gb.storage.User;
import com.gb.storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserPageHttpServlet extends HttpServlet {

    private UserStorage userStorage;

    @Override
    public void init() throws ServletException {
        userStorage = (UserStorage) getServletContext().getAttribute("userStorage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long userId;

        try {
            userId = Long.valueOf(req.getParameter("id"));
        } catch (Exception e) {
            resp.getWriter().println("<h1>Введён некорректный идентификатор пользователя!</h1>");
            resp.sendError(500);
            return;
        }

        User user = userStorage.findById(userId);

        if (user != null) {
            resp.getWriter().println(String.format("<h1>Страница пользователя: %s.</h1>", user.getName()));

            resp.getWriter().println(String.format(
                    "<table> " +
                    "  <tr style=\"text-align: left;\"> " +
                    "    <th>Идентификатор:</th> " +
                    "    <th>%s</th> " +
                    "  </tr> " +
                    "  <tr style=\"text-align: left;\"> " +
                    "    <th>Имя пользователя:</th> " +
                    "    <th>%s</th> " +
                    "  </tr> " +
                    "</table>", user.getId(), user.getName()));
        } else {
            resp.getWriter().println(String.format("<h1>Пользователь с идентификатором: %s - не найден!</h1>", userId));
            resp.sendError(404);
        }

    }
}
