package com.infoshareacademy.servlet;

import com.infoshareacademy.service.OrganizerViewService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-organizer")
public class NewOrganizerServlet extends HttpServlet {
    @EJB
    OrganizerViewService organizerViewService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        organizerViewService.save(req.getParameter("designation"));
    }
}
