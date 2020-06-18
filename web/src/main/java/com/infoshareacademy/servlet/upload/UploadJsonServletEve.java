package com.infoshareacademy.servlet.upload;

import com.infoshareacademy.service.FileUploadBean;
import com.infoshareacademy.service.PersistServiceBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/upload-eve")
public class UploadJsonServletEve extends HttpServlet {
    @Inject
    private PersistServiceBean persistServiceBean;

    @Inject
    private FileUploadBean fileUploadBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        persistServiceBean.event(fileUploadBean.uploadFile(req.getPart("json")));
        resp.sendRedirect("/upload-rest");
    }
}
