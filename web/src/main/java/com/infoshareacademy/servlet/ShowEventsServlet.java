package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/show-events")
public class ShowEventsServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(ShowEventsServlet.class.getName());
    String action;

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private EventViewService eventViewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action = req.getParameter("action");

        STDLOG.info("Requested action: {}", action);

        if (action == null || action.isEmpty()) {
            resp.getWriter().write("Empty action parameter.");
            return;
        }

        if (action.equals("showAll")) {
            showAll(req, resp);
        } else if (action.equals("search")) {

            String phrase = req.getParameter("phrase");
            String cleanPhrase = phrase.replaceAll("%", "");

            if (cleanPhrase.length() < 3) {
                resp.sendRedirect("/show-events?action=showAll&page=1");
            }

            searchByPhrase(req, resp);
        }
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action = req.getParameter("action");

        Template template = templateProvider.getTemplate(getServletContext(), "showEvents.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("role", contextHolder.getRole());
        Integer actPage = Integer.parseInt(req.getParameter("page"));
        Integer listSize = eventViewService.getAllEventsCount();
        Integer numberOfPages = (listSize % 20 != 0) ? listSize / 20 + 1 : listSize / 20;

        List<EventView> listEvents = eventViewService.prepareEventsToShow((actPage - 1) * 20);
        req.setCharacterEncoding("UTF-8");

        if ((actPage < 1 || actPage > numberOfPages) && listSize != 0) {
            resp.sendRedirect("/show-events?action=showAll&page=1");
        } else if (listSize == 0) {
            emptyDataBase(req, resp);
            return;
        }

        StringBuilder actionAppender = new StringBuilder();
        actionAppender.append("action=");
        actionAppender.append(action);
        actionAppender.append("&");

        String emailFav;
        Optional<String> emailOpt = Optional.ofNullable(contextHolder.getEmail());
        if (emailOpt.isPresent() && !emailOpt.isEmpty()) {
            emailFav = "\"" + emailOpt.get() + "\"";
        } else {
            emailFav = "\"placeholder\"";
        }

        dataModel.put("events", listEvents);
        dataModel.put("action", actionAppender.toString());
        dataModel.put("actPage", actPage);
        dataModel.put("numberOfPages", numberOfPages);
        dataModel.put("numberOfEvents", listSize);
        dataModel.put("name", "events");
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("emailFav", emailFav);

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for Show All Events page error");
        }
    }

    private void searchByPhrase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "showEvents.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("role", contextHolder.getRole());

        action = req.getParameter("action");

        Integer eveInd;
        Optional<String> eveIndOpt = Optional.ofNullable(req.getParameter("eve"));
        if (eveIndOpt.isPresent() && !eveIndOpt.isEmpty()) {
            eveInd = Integer.parseInt(eveIndOpt.get());
        } else {
            eveInd = 1;
        }

        Integer orgInd;
        Optional<String> orgIndOpt = Optional.ofNullable(req.getParameter("org"));
        if (orgIndOpt.isPresent() && !orgIndOpt.isEmpty()) {
            orgInd = Integer.parseInt(orgIndOpt.get());
        } else {
            orgInd = 1;
        }

        Integer dateInd;
        Optional<String> dateIndOpt = Optional.ofNullable(req.getParameter("date"));
        if (dateIndOpt.isPresent() && !dateIndOpt.isEmpty()) {
            dateInd = Integer.parseInt(dateIndOpt.get());
        } else {
            dateInd = 0;
        }

        String startDateStr;
        LocalDateTime start;
        Optional<String> startDateStrOpt = Optional.ofNullable(req.getParameter("start"));
        if (startDateStrOpt.isPresent() && !startDateStrOpt.isEmpty()) {
            startDateStr = startDateStrOpt.get();
            String conRdyStart = startDateStr.concat(" 00:00:00");
            start = stringToDate(conRdyStart);
        } else {
            startDateStr = (LocalDateTime.now().getYear() - 1) + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
            start = LocalDateTime.now().minusYears(1L);
        }

        String endDateStr;
        LocalDateTime end;
        Optional<String> endDateStrOpt = Optional.ofNullable(req.getParameter("end"));
        if (endDateStrOpt.isPresent() && !endDateStrOpt.isEmpty()) {
            endDateStr = endDateStrOpt.get();
            String conRdyEnd = endDateStr.concat(" 23:59:59");
            end = stringToDate(conRdyEnd);
        } else {
            endDateStr = (LocalDateTime.now().getYear() + 2) + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
            end = LocalDateTime.now().plusYears(2L);
        }

        Integer actPage = Integer.parseInt(req.getParameter("page"));

        String phrase = req.getParameter("phrase");
        String cleanPhrase = phrase.replaceAll("%", "");


        Integer listSize = eventViewService.listSize(cleanPhrase, eveInd, orgInd, dateInd, start, end);
        Integer numberOfPages = (listSize % 20 != 0) ? listSize / 20 + 1 : listSize / 20;
        List<EventView> listEvents = eventViewService.listEvents((actPage - 1) * 20, cleanPhrase, eveInd, orgInd, dateInd, start, end);
        req.setCharacterEncoding("UTF-8");

        StringBuilder redirect = new StringBuilder();
        redirect.append("/show-events?action=search&page=1&phrase=");
        redirect.append(cleanPhrase);

        StringBuilder actionAppender = new StringBuilder();
        actionAppender.append("action=");
        actionAppender.append(action);
        actionAppender.append("&");
        actionAppender.append("eve=");
        actionAppender.append(eveInd);
        actionAppender.append("&");
        actionAppender.append("org=");
        actionAppender.append(orgInd);
        actionAppender.append("&");
        actionAppender.append("date=");
        actionAppender.append(eveInd);
        actionAppender.append("&");
        actionAppender.append("start=");
        actionAppender.append(startDateStr);
        actionAppender.append("&");
        actionAppender.append("end=");
        actionAppender.append(endDateStr);
        actionAppender.append("&");

        if ((actPage < 1 || actPage > numberOfPages) && listSize > 0) {
            resp.sendRedirect(redirect.toString());
        } else if (listSize == 0) {
            noResultsFound(req, resp);
            return;
        }

        String emailFav;
        Optional<String> emailOpt = Optional.ofNullable(contextHolder.getEmail());
        if (emailOpt.isPresent() && !emailOpt.isEmpty()) {
            emailFav = "\"" + emailOpt.get() + "\"";
        } else {
            emailFav = "\"placeholder\"";
        }

        StringBuilder actionPlusPhrase = new StringBuilder();
        actionPlusPhrase.append(actionAppender.toString());
        actionPlusPhrase.append("&phrase=");
        actionPlusPhrase.append(cleanPhrase);
        actionPlusPhrase.append("&");

        dataModel.put("events", listEvents);
        dataModel.put("action", actionPlusPhrase.toString());
        dataModel.put("actPage", actPage);
        dataModel.put("numberOfPages", numberOfPages);
        dataModel.put("numberOfEvents", listSize);
        dataModel.put("name", "events");
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("emailFav", emailFav);

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for Show Search Results page error");
        }
    }

    private LocalDateTime stringToDate(String in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(in, formatter);
    }

    private void noResultsFound(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "noResultsFound.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("role", contextHolder.getRole());
        req.setCharacterEncoding("UTF-8");
        String previous = req.getHeader("referer");

        String phrase = req.getParameter("phrase");
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("phrase", phrase);
        dataModel.put("previous", previous);

        resp.setContentType("text/html; charset=UTF-8");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for Show Search Results page error");
        }
    }

    private void emptyDataBase(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "emptyDataBase.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("role", contextHolder.getRole());
        req.setCharacterEncoding("UTF-8");
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);

        resp.setContentType("text/html; charset=UTF-8");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for Show Search Results page error");
        }
    }
}
