package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.EditEventService;
import com.infoshareacademy.service.OrganizerViewService;
import com.infoshareacademy.service.event.EventViewService;
import com.infoshareacademy.service.HttpReqMapperBean;
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
    private TemplateProvider templateProvider;

    @Inject
    private EditEventService editEventService;

    @EJB
    private EventViewService eventViewService;

    @EJB
    private OrganizerViewService organizerViewService;

    @Inject
    private HttpReqMapperBean httpReqMapperBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "editEventForm.ftlh");
        ContextHolder contextHolder = new ContextHolder(req.getSession());
        Map<String, Object> dataModel = new HashMap<>();
        String previous = req.getHeader("referer");

        dataModel.put("previous", previous);
        dataModel.put("email", contextHolder.getEmail());
        dataModel.put("role", contextHolder.getRole());
        dataModel.put("organizers", organizerViewService.prepareOrganizersToShow());

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

        //editEventService.updateEvent(httpReqMapperBean.map(req));

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/show-events?action=showAll&page=1");
    }
}
