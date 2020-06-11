package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.UserViewService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users-list")
public class ShowUsersServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(ShowUsersServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @EJB
    UserViewService userViewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "usersList.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("users", userViewService.prepareUsersToShow());
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for users page error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "usersList.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String roleToChange = req.getParameter("role");
        String emailToFind = req.getParameter("email");

        userViewService.changeRole(emailToFind,roleToChange);

        dataModel.put("users", userViewService.prepareUsersToShow());
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getEmail());

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for users page error");
        }
    }
}
