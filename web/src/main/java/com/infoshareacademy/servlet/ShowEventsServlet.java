package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.EventViewService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/show-events")
public class ShowEventsServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(LoginServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @EJB
    EventViewService eventViewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");

        STDLOG.info("Requested action: {}", action);

        if (action == null || action.isEmpty()) {
            resp.getWriter().write("Empty action parameter.");
            return;
        }

        if (action.equals("showAll")) {
            showAll(req, resp);
        } else if (action.equals("searchByPhrase")) {
            searchByPhrase(req, resp);
        } /*else if (action.equals("delete")) {
            deleteCourse(req, resp);
        } else {
            resp.getWriter().write("Unknown action.");
        }*/

    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "showEvents.ftlh");
        Map<String, Object> dataModel = new HashMap<>();
        Integer actPage = Integer.parseInt(req.getParameter("page"));
        Integer listSize = eventViewService.listSize();
        Integer numberOfPages = (listSize % 20 != 0) ? listSize / 20 + 1 : listSize / 20;
        List<EventView> listEvents = eventViewService.prepareEventsToShow((actPage - 1) * 20);
        req.setCharacterEncoding("UTF-8");

        if (actPage < 1 || actPage > numberOfPages) {
            resp.sendRedirect("/show-events?action=showAll&page=1");
        }

        dataModel.put("events", listEvents);
        dataModel.put("actPage", actPage);
        dataModel.put("numberOfPages", numberOfPages);
        dataModel.put("numberOfEvents", listSize);

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for main page error");
        }
    }
    private void searchByPhrase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "showEvents.ftlh");
        Map<String, Object> dataModel = new HashMap<>();

        Integer actPage = Integer.parseInt(req.getParameter("page"));
        String phrase = req.getParameter("phrase");

        Integer listSize = eventViewService.listSize();
        Integer numberOfPages = (listSize % 20 != 0) ? listSize / 20 + 1 : listSize / 20;
        List<EventView> listEvents = eventViewService.prepareEventsToShow((actPage - 1) * 20);
        req.setCharacterEncoding("UTF-8");

        if (actPage < 1 || actPage > numberOfPages) {
            resp.sendRedirect("/show-events?page=1");
        }

        dataModel.put("events", listEvents);
        dataModel.put("actPage", actPage);
        dataModel.put("numberOfPages", numberOfPages);
        dataModel.put("numberOfEvents", listSize);

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