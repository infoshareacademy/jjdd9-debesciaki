package com.infoshareacademy.service;

import com.infoshareacademy.domain.ReqMapEventDTO;
import com.infoshareacademy.util.StringToLocalDateTime;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;


@Stateless
public class HttpReqMapperBean {

    public ReqMapEventDTO map(HttpServletRequest req) {

        ReqMapEventDTO reqMapEventDTO = new ReqMapEventDTO();
        reqMapEventDTO.setId(Long.valueOf(req.getParameter("id")));
        reqMapEventDTO.setName(req.getParameter("name"));
        reqMapEventDTO.setDescLong(req.getParameter("descLong"));

        reqMapEventDTO.setStartDate(StringToLocalDateTime.process(req.getParameter("startDate").concat(" ").concat(req.getParameter("startTime")).concat(":00")));
        reqMapEventDTO.setEndDate(StringToLocalDateTime.process(req.getParameter("endDate").concat(" ").concat(req.getParameter("endTime")).concat(":00")));

        reqMapEventDTO.setUrl(req.getParameter("url"));

        reqMapEventDTO.setTypeOfTicket(req.getParameter("typeOfTicket"));

        if (req.getParameter("typeOfTicket").equals("tickets")) {
            reqMapEventDTO.setReducedTicket(req.getParameter("reducedTicket"));
            reqMapEventDTO.setNormalTicket(req.getParameter("normalTicket"));
        }

        reqMapEventDTO.setTicketAmount((long) Integer.valueOf(req.getParameter("numberOfTickets")));

        reqMapEventDTO.setOrganizerDesignation(req.getParameter("organizersDesignation"));

        reqMapEventDTO.setName(req.getParameter("category"));

        reqMapEventDTO.setPlaceName(req.getParameter("placeName"));
        reqMapEventDTO.setPlaceSubname(req.getParameter("placeSubname"));

        reqMapEventDTO.setCity(req.getParameter("city"));
        reqMapEventDTO.setStreet(req.getParameter("street"));
        reqMapEventDTO.setZipCode(req.getParameter("zipCode"));

        return reqMapEventDTO;
    }

}
