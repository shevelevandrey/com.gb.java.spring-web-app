package com.gb;

import com.gb.storage.User;
import com.gb.storage.UserStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserStorage userStorage = new UserStorage();

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("userStorage", userStorage);

        userStorage.add(new User("Толстой, Лев Николаевич"));
        userStorage.add(new User("Пушкин, Александр Сергеевич"));
        userStorage.add(new User("Есенин, Сергей Александрович"));
        userStorage.add(new User("Достоевский, Фёдор Михайлович"));
        userStorage.add(new User("Лермонтов, Михаил Юрьевич"));
        userStorage.add(new User("Маршак, Самуил Яковлевич"));
    }
}
