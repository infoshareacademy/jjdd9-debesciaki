package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.freemarker.TemplateProvider;
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

@WebServlet("/edit-event")
public class EditEventServlet extends HttpServlet {
    private static final Logger STDLOG = LoggerFactory.getLogger(EditEventServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @EJB
    EventViewService eventViewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "editEventForm.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());

        Long eventIdToShow = Long.parseLong(req.getParameter("event"));
        EventView event = eventViewService.prepareSingleEvent(eventIdToShow);
        dataModel.put("event", event);

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            template.process(dataModel, pw);
        } catch (TemplateException e) {
            STDLOG.error("Template for edit event page error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        req.setCharacterEncoding("UTF-8");
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());

        EventView changedEvent = new EventView();
        changedEvent.setId(Long.valueOf(req.getParameter("id")));
        changedEvent.setName(req.getParameter("name"));
        changedEvent.setOrganizerName(req.getParameter("organizer"));
        changedEvent.setCategoryName(req.getParameter("category"));
        changedEvent.setPlaceName(req.getParameter("placeName"));
        changedEvent.setPlaceSubname(req.getParameter("placeSubname"));
        if (changedEvent.getPlaceSubname().equals("brak")) {
            changedEvent.setPlaceSubname(null);
        }
        changedEvent.setWebsite(req.getParameter("url"));
        changedEvent.setStartDate(req.getParameter("startDate"));
        changedEvent.setEndDate(req.getParameter("endDate"));
        changedEvent.setTicket(req.getParameter("typeOfTicket"));
        changedEvent.setNumberOfTickets(Integer.valueOf(req.getParameter("numberOfTickets")));

        if(changedEvent.getTicket().equals("tickets")) {
            changedEvent.setMinTicketPrice(Integer.valueOf(req.getParameter("reducedTicket")));
            changedEvent.setMaxTicketPrice(Integer.valueOf(req.getParameter("normalTicket")));
        }

        changedEvent.setDescLong(req.getParameter("descLong"));

        eventViewService.update(changedEvent);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/show-events?action=showAll&page=1");
    }
}
