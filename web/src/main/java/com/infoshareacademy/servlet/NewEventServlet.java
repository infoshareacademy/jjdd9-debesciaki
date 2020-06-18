package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.OrganizerViewService;
import com.infoshareacademy.service.event.EventViewService;
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
import java.util.Map;

@WebServlet("/add-event")
public class NewEventServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(NewEventServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @EJB
    EventViewService eventViewService;

    @EJB
    OrganizerViewService organizerViewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "newEventForm.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());
        dataModel.put("organizers", organizerViewService.prepareOrganizersToShow());

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for add event page error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());

        EventView newEvent = new EventView();
        newEvent.setName(req.getParameter("name"));
        newEvent.setOrganizerName(req.getParameter("organizersDesignation"));
        newEvent.setCategoryName(req.getParameter("category"));
        newEvent.setPlaceName(req.getParameter("place"));
        newEvent.setWebsite(req.getParameter("url"));
        newEvent.setStartDate(req.getParameter("startDate"));
        newEvent.setEndDate(req.getParameter("endDate"));
        newEvent.setTicket(req.getParameter("typeOfTicket"));
        newEvent.setNumberOfTickets(Integer.valueOf(req.getParameter("numberOfTickets")));

        if(newEvent.getTicket().equals("tickets")) {
            newEvent.setMinTicketPrice(Integer.valueOf(req.getParameter("reducedTicket")));
            newEvent.setMaxTicketPrice(Integer.valueOf(req.getParameter("normalTicket")));
        }

        newEvent.setDescLong(req.getParameter("descLong"));

        eventViewService.newEvent(newEvent);
        resp.sendRedirect("show-events?action=showAll&page=1");
    }
}
