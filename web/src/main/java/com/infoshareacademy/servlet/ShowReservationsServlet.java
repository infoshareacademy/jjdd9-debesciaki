package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.view.EventReservationView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.ReservationViewService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/show-reservations")
public class ShowReservationsServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(LoginServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private ReservationViewService reservationViewService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "showReserved.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();


        String email;
        String emailQuery;
        Optional<String> emailOpt = Optional.ofNullable(contextHolder.getEmail());
        if (emailOpt.isPresent() && !emailOpt.isEmpty()) {
            email = "\"" + emailOpt.get() + "\"";
            emailQuery = emailOpt.get();
        } else {
            email = "\"placeholder\"";
            emailQuery = "placeholder";
        }

        Integer actPage = Integer.parseInt(req.getParameter("page"));
        Integer listSize = reservationViewService.getReservationCount(emailQuery);
        Integer numberOfPages = (listSize % 20 != 0) ? listSize / 20 + 1 : listSize / 20;

        List<EventReservationView> listEvents = reservationViewService.prepareReservedList((actPage - 1) * 20, emailQuery);

        dataModel.put("role", contextHolder.getRole());
        dataModel.put("events", listEvents);
        dataModel.put("actPage", actPage);
        dataModel.put("numberOfPages", numberOfPages);
        dataModel.put("numberOfEvents", listSize);
        dataModel.put("email", email);
        dataModel.put("name", "reservations");
        dataModel.put("action", "");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for Show All Events page error");
        }
    }

}
