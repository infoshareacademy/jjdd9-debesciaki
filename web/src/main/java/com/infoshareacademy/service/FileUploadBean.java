package com.infoshareacademy.service;

import org.apache.commons.io.FileUtils;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@RequestScoped
public class FileUploadBean {

    public String getUploadImageFilesPath() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.properties"));
        return properties.getProperty("Upload.Path");
    }

    public File uploadFile(Part filePart) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName())
                .getFileName().toString();
        File file = new File(getUploadImageFilesPath() + fileName);
        Files.deleteIfExists(file.toPath());
        InputStream fileContent = filePart.getInputStream();
        FileUtils.copyInputStreamToFile(fileContent, file);
        fileContent.close();
        return file;
    }
}
