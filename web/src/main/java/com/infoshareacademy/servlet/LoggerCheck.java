package com.infoshareacademy.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class LoggerCheck extends HttpServlet {
    private static final Logger STDODG = LoggerFactory.getLogger(LoggerCheck.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        STDODG.info("Server info ");
        STDODG.error("Server error ");
        STDODG.debug("Server debug ");
        STDODG.warn("Server warn ");
        STDODG.trace("Server trace ");


        PrintWriter writer = resp.getWriter();
        writer.println("SZALOMKA");
    }
}