package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/token")
public class TokenRedirectServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(TokenRedirectServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "tokenRedirect.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String previous = req.getHeader("referer");
        dataModel.put("previous", previous);
        Optional<String> msgOptional = Optional.of(req.getParameter("msg"));

        if (msgOptional.isPresent()) {
            dataModel.put("msg", msgOptional.get());
        }else{
            dataModel.put("msg", "Brak wiadomości.");
        }

        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for main page error");
        }

    }
}
