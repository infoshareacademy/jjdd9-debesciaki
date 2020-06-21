package com.infoshareacademy.servlet.upload;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.ClientUploadService;
import com.infoshareacademy.servlet.LoginServlet;
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


@WebServlet("/upload-rest")
public class UploadJsonFromAPIServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(UploadJsonFromAPIServlet.class.getName());

    @Inject
    private ClientUploadService clientUploadService;


    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "upload.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("role", contextHolder.getRole());

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for login page error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        clientUploadService.upload();
        resp.sendRedirect("/upload-rest");
    }
}
