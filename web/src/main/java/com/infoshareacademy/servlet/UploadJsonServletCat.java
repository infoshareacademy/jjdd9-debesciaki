package com.infoshareacademy.servlet;

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
@WebServlet("/upload-cat")
public class UploadJsonServletCat extends HttpServlet {
    @Inject
    PersistServiceBean persistServiceBean;

    @Inject
    FileUploadBean fileUploadBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        persistServiceBean.category(fileUploadBean.uploadFile(req.getPart("json")));
    }
}
