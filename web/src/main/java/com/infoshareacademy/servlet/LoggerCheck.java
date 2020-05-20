package com.infoshareacademy.servlet;

import com.infoshareacademy.entity.User;
import com.infoshareacademy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class LoggerCheck extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerCheck.class.getName());
    @Inject
    UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        LOGGER.info("Server info ");
        LOGGER.error("Server error ");
        LOGGER.debug("Server debug ");
        LOGGER.warn("Server warn ");
        LOGGER.trace("Server trace ");
        userService.saveUser();

        PrintWriter writer = resp.getWriter();
        writer.println("SZALOMKA");
    }
}