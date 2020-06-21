package com.infoshareacademy.service;

import com.infoshareacademy.domain.ReqMapDTO;
import com.infoshareacademy.util.StringToLocalDateTime;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;


@Stateless
public class HttpReqMapperBean {

    public ReqMapDTO map(HttpServletRequest req) {

        ReqMapDTO reqMapDTO = new ReqMapDTO();

        reqMapDTO.setName(req.getParameter("name"));
        reqMapDTO.setDescLong(req.getParameter("descLong"));

        reqMapDTO.setStartDate(StringToLocalDateTime.process(req.getParameter("startDate").concat(" ").concat(req.getParameter("startTime")).concat(":00")));
        reqMapDTO.setEndDate(StringToLocalDateTime.process(req.getParameter("endDate").concat(" ").concat(req.getParameter("endTime")).concat(":00")));

        reqMapDTO.setUrl(req.getParameter("url"));

        reqMapDTO.setTypeOfTicket(req.getParameter("typeOfTicket"));

        if (req.getParameter("typeOfTicket").equals("tickets")) {
            reqMapDTO.setReducedTicket(req.getParameter("reducedTicket"));
            reqMapDTO.setNormalTicket(req.getParameter("normalTicket"));
        }

        reqMapDTO.setTicketAmount((long) Integer.valueOf(req.getParameter("numberOfTickets")));

        reqMapDTO.setOrganizerDesignation(req.getParameter("organizersDesignation"));

        reqMapDTO.setName(req.getParameter("category"));

        reqMapDTO.setPlaceName(req.getParameter("placeName"));
        reqMapDTO.setPlaceSubname(req.getParameter("placeSubname"));

        reqMapDTO.setCity(req.getParameter("city"));
        reqMapDTO.setStreet(req.getParameter("street"));
        reqMapDTO.setZipCode(req.getParameter("zipCode"));

        return reqMapDTO;
    }

}
