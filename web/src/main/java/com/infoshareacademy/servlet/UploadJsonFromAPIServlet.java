package com.infoshareacademy.servlet;

import com.infoshareacademy.service.ClientUploadService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/upload-rest")
public class UploadJsonFromAPIServlet extends HttpServlet {

    @Inject
    ClientUploadService clientUploadService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        clientUploadService.upload();
        resp.sendRedirect("/index");
    }
}
